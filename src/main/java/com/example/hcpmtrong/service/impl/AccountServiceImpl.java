package com.example.hcpmtrong.service.impl;



import com.example.hcpmtrong.dto.AccountDTO;
import com.example.hcpmtrong.service.AccountService;
import com.example.hcpmtrong.utils.response.PageList;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Override
	public AccountDTO save(AccountDTO dto) {
		return null;
	}

	@Override
	public AccountDTO findById(Long id) {
		return null;
	}

	@Override
	public AccountDTO update(AccountDTO dto, Long id) {
		return null;
	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public PageList<AccountDTO> findAll(Map<String, String> model) {
		return null;
	}

	@Override
	public AccountDTO getAccount(String username) {
		return null;
	}

	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

	}
}
