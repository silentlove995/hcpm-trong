package com.example.hcpmtrong.entity;

import com.example.hcpmtrong.enums.Status;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {

	@Column(name = "username", nullable = false, unique = true, length = 30)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;


}
