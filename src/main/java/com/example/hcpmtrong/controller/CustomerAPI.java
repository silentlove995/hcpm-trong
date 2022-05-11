package com.example.hcpmtrong.controller;



import com.example.hcpmtrong.dto.CustomerDTO;
import com.example.hcpmtrong.service.CustomerService;
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
@RequestMapping("/api/admin/customer")
@AllArgsConstructor
public class CustomerAPI {

	@Autowired
	CustomerService customerService;

	@PostMapping
	public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
		customerDTO = customerService.save(customerDTO);
		return ResponseEntityBuilder.getBuilder()
			.setCode(200)
			.setMessage("CREATE CUSTOMER SUCCESS")
			.setDetails(customerDTO)
			.build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO,
		@PathVariable Long id) {
		customerDTO = customerService.update(customerDTO, id);
		return ResponseEntityBuilder.getBuilder()
			.setCode(200)
			.setMessage("UPDATE CUSTOMER SUCCESS")
			.setDetails(customerDTO)
			.build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntityBuilder.getBuilder()
			.setCode(200)
			.setMessage("FIND CUSTOMER SUCCESS")
			.setDetails(customerService.findById(id))
			.build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		customerService.delete(id);
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
			.setDetails(customerService.findAll())
			.build();
	}
}
