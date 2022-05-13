package com.example.hcpmtrong.service;


import com.example.hcpmtrong.dto.CustomerDTO;
import com.example.hcpmtrong.utils.response.PageList;
import java.util.List;
import java.util.Map;

public interface CustomerService {

	CustomerDTO save(CustomerDTO dto);

	CustomerDTO update(CustomerDTO dto, Long id);

	CustomerDTO findById(Long id);

	void delete(Long id);

	PageList<CustomerDTO> findAllPageable(Map<String, String> model);
	List<CustomerDTO> findAll();

}
