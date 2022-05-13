package com.example.hcpmtrong.repository;



import com.example.hcpmtrong.entity.Roles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
	List<Roles> findAllByIdIn(List<Long> roleIds);
}
