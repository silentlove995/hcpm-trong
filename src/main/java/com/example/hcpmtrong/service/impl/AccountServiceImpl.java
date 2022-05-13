package com.example.hcpmtrong.service.impl;



import static com.example.hcpmtrong.enums.Status.ACTIVE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.hcpmtrong.converter.Converter;
import com.example.hcpmtrong.dto.AccountDTO;
import com.example.hcpmtrong.entity.Account;
import com.example.hcpmtrong.repository.AccountRepository;
import com.example.hcpmtrong.repository.RoleRepository;
import com.example.hcpmtrong.service.AccountService;
import com.example.hcpmtrong.utils.CommonUtils;
import com.example.hcpmtrong.utils.FormUtil;
import com.example.hcpmtrong.utils.errors.CustomException;
import com.example.hcpmtrong.utils.response.PageList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public AccountDTO save(AccountDTO dto) throws IOException, WriterException {
		Account findUsername = accountRepository.findByUsername(dto.getUsername());
		if (!Objects.isNull(findUsername)){
			throw new CustomException("Username already exists", CommonUtils.putError("username", "ERR_403"));
		}
		Account account = Converter.toModel(dto, Account.class);
		account.setRoles(roleRepository.findAllByIdIn(Collections.singletonList(dto.getRoleId())));
		account.setPassword(passwordEncoder.encode(dto.getPassword()));
		account.setStatus(ACTIVE);
		account = accountRepository.save(account);
		return Converter.toModel(account, AccountDTO.class);
	}

	@Override
	public AccountDTO findById(Long id) {
		Optional<Account> account = accountRepository.findById(id);
		return Converter.toModel(account, AccountDTO.class);
	}

	@Override
	public AccountDTO update(AccountDTO dto, Long id) {
		Optional<Account> account = accountRepository.findById(id)
			.map(account1 -> {
				account1 = Converter.toModel(dto, Account.class);
				return accountRepository.save(account1);
			});
		return Converter.toModel(account, AccountDTO.class);
	}

	@Override
	public void delete(Long id) {
		accountRepository.deleteById(id);
	}

	@Override
	public PageList<AccountDTO> findAll(Map<String, String> model) {
		Pageable pageable = FormUtil.toPageable(model);
		List<Account> entities = accountRepository.findAll(pageable).getContent();
		long count = accountRepository.count();
		return PageList.<AccountDTO>builder()
			.list(Converter.toList(entities, AccountDTO.class))
			.currentPage(pageable.getPageNumber() + 1)
			.total(count)
			.pageSize(pageable.getPageSize())
			.success(true)
			.totalPage((int) Math.ceil((double) Integer.parseInt(Long.toString(count)) / pageable.getPageSize()))
			.build();
	}

	@Override
	public AccountDTO getAccount(String username) {
		Account account = accountRepository.findByUsername(username);
		return Converter.toModel(account, AccountDTO.class);
	}

	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
			try{
				String refresh_token = authorizationHeader.substring("Bearer".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				AccountService accountService = null;
				AccountDTO account = accountService.getAccount(username);
				List list = new ArrayList<>();
				list.add(roleRepository.findAllByIdIn(Collections.singletonList(account.getRoleId())));
				String access_token = JWT.create().withSubject(account.getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 24 * 3600 * 1000))
					.withIssuer(request.getRequestURL().toString())
					.withClaim("roles", list)
					.sign(algorithm);

				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			}catch (Exception exception){
				response.setHeader("error", exception.getMessage());
				response.setStatus(FORBIDDEN.value());
				//response.sendError(FORBIDDEN.value());
				Map<String, String> error = new HashMap<>();
				error.put("error_message", exception.getMessage());
				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		}else {
			throw new RuntimeException("Refresh token is missing.");
		}
	}
}
