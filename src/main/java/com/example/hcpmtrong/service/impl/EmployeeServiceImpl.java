package com.example.hcpmtrong.service.impl;



import com.example.hcpmtrong.converter.Converter;
import com.example.hcpmtrong.dto.EmployeeDTO;
import com.example.hcpmtrong.entity.Employee;
import com.example.hcpmtrong.repository.EmployeeRepository;
import com.example.hcpmtrong.service.EmployeeService;
import com.example.hcpmtrong.utils.FormUtil;
import com.example.hcpmtrong.utils.response.PageList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeDTO save(EmployeeDTO dto) {
		Employee employee = Converter.toModel(dto, Employee.class);
		employee = employeeRepository.save(employee);
		return Converter.toModel(employee, EmployeeDTO.class);
	}

	@Override
	public EmployeeDTO findById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		return Converter.toModel(employee, EmployeeDTO.class);
	}

	@Override
	public EmployeeDTO findByCode(String code) {
		Employee employee = employeeRepository.findEmployeeByEmployeeCode(code);
		return Converter.toModel(employee, EmployeeDTO.class);
	}

	@Override
	public EmployeeDTO update(EmployeeDTO dto, Long id) {
		Optional<Employee> employees = employeeRepository.findById(id)
			.map(employee -> {
				ModelMapper modelMapper = new ModelMapper();
				employee = modelMapper.map(dto, Employee.class);
				return employeeRepository.save(employee);
			});
		return Converter.toModel(employees, EmployeeDTO.class);
	}

	@Override
	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public PageList<EmployeeDTO> findAllPageable(Map<String, String> model) {
		Pageable pageable = FormUtil.toPageable(model);
		List<Employee> employees = employeeRepository.findAll(pageable).getContent();
		long count = employeeRepository.count();
		return PageList.<EmployeeDTO>builder()
			.list(Converter.toList(employees, EmployeeDTO.class))
			.currentPage(pageable.getPageNumber() + 1)
			.total(count)
			.pageSize(pageable.getPageSize())
			.success(true)
			.totalPage((int) Math.ceil((double) Integer.parseInt(Long.toString(count)) / pageable.getPageSize()))
			.build();
	}

	@Override
	public List<EmployeeDTO> findAll() {
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeDTO> dtos = new ArrayList<>();
		for (Employee item : employees) {
			dtos.add(Converter.toModel(employees, EmployeeDTO.class));
		}
		return dtos;
	}


}
