package com.example.hcpmtrong.service;



import com.example.hcpmtrong.dto.RolesDTO;
import com.example.hcpmtrong.utils.response.PageList;
import java.util.Map;

public interface RoleService {

	RolesDTO save(RolesDTO dto);

	RolesDTO findById(Long id);

	RolesDTO update(RolesDTO dto, Long id);

	void delete(Long id);

	PageList<RolesDTO> findAll(Map<String, String> model);
}
