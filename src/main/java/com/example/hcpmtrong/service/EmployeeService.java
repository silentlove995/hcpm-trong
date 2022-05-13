package com.example.hcpmtrong.service;


import com.example.hcpmtrong.dto.EmployeeDTO;
import com.example.hcpmtrong.utils.response.PageList;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

	EmployeeDTO save(EmployeeDTO dto);

	EmployeeDTO findById(Long id);

	EmployeeDTO findByCode(String code);

	EmployeeDTO update(EmployeeDTO dto, Long id);

	void delete(Long id);

	PageList<EmployeeDTO> findAllPageable(Map<String, String> model);
	List<EmployeeDTO> findAll();
}
