package com.example.hcpmtrong.controller;



import com.example.hcpmtrong.dto.EmployeeDTO;
import com.example.hcpmtrong.service.EmployeeService;
import com.example.hcpmtrong.utils.response.ResponseEntityBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/employee")
@AllArgsConstructor
public class EmployeeAPI {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		employeeDTO = employeeService.save(employeeDTO);
		return ResponseEntityBuilder.getBuilder()
			.setCode(200)
			.setMessage("CREATE CUSTOMER SUCCESS")
			.setDetails(employeeDTO)
			.build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDTO,
		@PathVariable Long id) {
		employeeDTO = employeeService.update(employeeDTO, id);
		return ResponseEntityBuilder.getBuilder()
			.setCode(200)
			.setMessage("UPDATE CUSTOMER SUCCESS")
			.setDetails(employeeDTO)
			.build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntityBuilder.getBuilder()
			.setCode(200)
			.setMessage("FIND CUSTOMER SUCCESS")
			.setDetails(employeeService.findById(id))
			.build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		employeeService.delete(id);
		return ResponseEntityBuilder.getBuilder()
			.setCode(200)
			.setMessage("Success")
			.build();
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntityBuilder.getBuilder()
			.setCode(200)
			.setMessage("Success")
			.setDetails(employeeService.findAll())
			.build();
	}
}
