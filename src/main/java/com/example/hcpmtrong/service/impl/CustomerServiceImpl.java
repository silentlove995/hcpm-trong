package com.example.hcpmtrong.service.impl;



import com.example.hcpmtrong.converter.Converter;
import com.example.hcpmtrong.dto.CustomerDTO;
import com.example.hcpmtrong.entity.Customer;
import com.example.hcpmtrong.repository.CustomerRepository;
import com.example.hcpmtrong.service.CustomerService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {


	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public CustomerDTO save(CustomerDTO dto) {
		Customer customer = Converter.toModel(dto, Customer.class);
		customer = customerRepository.save(customer);
		return Converter.toModel(customer, CustomerDTO.class);
	}

	@Override
	public CustomerDTO update(CustomerDTO dto, Long id) {
		Optional<Customer> customers = customerRepository.findById(id)
			.map(customer -> {
				ModelMapper modelMapper = new ModelMapper();
				customer = modelMapper.map(dto, Customer.class);
				return customerRepository.save(customer);
			});
		return Converter.toModel(customers, CustomerDTO.class);
	}

	@Override
	public CustomerDTO findById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return Converter.toModel(customer, CustomerDTO.class);
	}

	@Override
	public void delete(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public List<CustomerDTO> findAll() {
		List<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> dtos = new ArrayList<>();
		for (Customer item : customers) {
			dtos.add(Converter.toModel(customers, CustomerDTO.class));
		}
		return dtos;
	}

//	@Override
//	public PageList<CustomerDTO> findAll(Map<String, String> model) {
//		Pageable pageable = FormUtil.toPageable(model);
//		List<Customer> customers = customerRepository.findAll(pageable).getContent();
//		long count = customerRepository.count();
//		return PageList.<CustomerDTO>builder()
//			.list(Converter.toList(customers, CustomerDTO.class))
//			.currentPage(pageable.getPageNumber() + 1)
//			.total(count)
//			.pageSize(pageable.getPageSize())
//			.success(true)
//			.totalPage((int) Math.ceil((double) Integer.parseInt(Long.toString(count)) / pageable.getPageSize()))
//			.build();
//	}


}
