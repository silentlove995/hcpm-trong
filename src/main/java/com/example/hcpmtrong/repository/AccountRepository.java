package com.example.hcpmtrong.repository;


import com.example.hcpmtrong.entity.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByUsername(String username);
}
