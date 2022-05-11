package com.example.hcpmtrong.service;


import com.example.hcpmtrong.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {

	CustomerDTO save(CustomerDTO dto);

	CustomerDTO update(CustomerDTO dto, Long id);

	CustomerDTO findById(Long id);

	void delete(Long id);

	//	PageList<CustomerDTO> findAll(Map<String, String> model);
	List<CustomerDTO> findAll();

}
