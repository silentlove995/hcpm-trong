package com.example.hcpmtrong.service;


import com.example.hcpmtrong.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {

	EmployeeDTO save(EmployeeDTO dto);

	EmployeeDTO findById(Long id);

	EmployeeDTO findByCode(String code);

	EmployeeDTO update(EmployeeDTO dto, Long id);

	void delete(Long id);

	List<EmployeeDTO> findAll();
}
