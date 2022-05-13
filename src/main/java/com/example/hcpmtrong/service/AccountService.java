package com.example.hcpmtrong.service;



import com.example.hcpmtrong.dto.AccountDTO;
import com.example.hcpmtrong.utils.response.PageList;
import com.google.zxing.WriterException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountService {

	AccountDTO save(AccountDTO dto) throws IOException, WriterException;

	AccountDTO findById(Long id);

	AccountDTO update(AccountDTO dto, Long id);

	void delete(Long id);

	PageList<AccountDTO> findAll(Map<String, String> model);


	AccountDTO getAccount(String username);

	void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
