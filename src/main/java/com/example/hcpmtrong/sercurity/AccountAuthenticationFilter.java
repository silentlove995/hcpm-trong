package com.example.hcpmtrong.sercurity;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.hcpmtrong.entity.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
public class AccountAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public AccountAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(
		HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try{
			String requestData = request.getReader().lines().collect(Collectors.joining());
			Account model = new Gson().fromJson(requestData, Account.class);
			UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword());
			return authenticationManager.authenticate(authenticationToken);
		} catch (IOException e) {
			throw new RuntimeException("Login fail.");
		}
	}

	//sau khi login thanh cong thi tra ve thong tin token

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		org.springframework.security.core.userdetails.User user =
			(org.springframework.security.core.userdetails.User) authResult.getPrincipal();

		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		String access_token = JWT.create().withSubject(user.getUsername())
			//set exprired date
			.withExpiresAt(new Date(System.currentTimeMillis() + 2 * 3600 * 1000))
			.withIssuer(request.getRequestURL().toString())
			.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
			.sign(algorithm);

		String refresh_token = JWT.create().withSubject(user.getUsername())
			.withExpiresAt(new Date(System.currentTimeMillis() + 365 * 24 * 3600 * 1000))
			.withIssuer(request.getRequestURL().toString())
			.sign(algorithm);

		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);
		response.setContentType(APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}
}
