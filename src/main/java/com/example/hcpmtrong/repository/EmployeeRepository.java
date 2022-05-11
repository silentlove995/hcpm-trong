package com.example.hcpmtrong.repository;



import com.example.hcpmtrong.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findEmployeeByEmployeeCode(String code);
}
