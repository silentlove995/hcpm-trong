package com.example.hcpmtrong.service.impl;



import com.example.hcpmtrong.converter.Converter;
import com.example.hcpmtrong.dto.RolesDTO;
import com.example.hcpmtrong.entity.Roles;
import com.example.hcpmtrong.repository.RoleRepository;
import com.example.hcpmtrong.service.RoleService;
import com.example.hcpmtrong.utils.FormUtil;
import com.example.hcpmtrong.utils.response.PageList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RolesDTO save(RolesDTO dto) {
		Roles roles = Converter.toModel(dto, Roles.class);
		roles = roleRepository.save(roles);
		return Converter.toModel(roles, RolesDTO.class);
	}

	@Override
	public RolesDTO findById(Long id) {
		Optional<Roles> roles = roleRepository.findById(id);
		return Converter.toModel(roles, RolesDTO.class);
	}

	@Override
	public RolesDTO update(RolesDTO dto, Long id) {
		Optional<Roles> roles = roleRepository.findById(id)
			.map(role -> {
				role = Converter.toModel(dto, Roles.class);
				return roleRepository.save(role);
			});
		return Converter.toModel(roles, RolesDTO.class);
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
	}

	@Override
	public PageList<RolesDTO> findAll(Map<String, String> model) {
		Pageable pageable = FormUtil.toPageable(model);
		List<Roles> roles = roleRepository.findAll(pageable).getContent();
		long count = roleRepository.count();
		return PageList.<RolesDTO>builder()
			.list(Converter.toList(roles, RolesDTO.class))
			.currentPage(pageable.getPageNumber() + 1)
			.total(count)
			.pageSize(pageable.getPageSize())
			.success(true)
			.totalPage((int) Math.ceil((double) Integer.parseInt(Long.toString(count)) / pageable.getPageSize()))
			.build();
	}
}
