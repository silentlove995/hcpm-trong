package com.example.hcpmtrong.entity;

import com.example.hcpmtrong.enums.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@ManyToMany
	@JoinTable(
		name = "account_role",
		joinColumns = @JoinColumn(name = "role_id"),
		inverseJoinColumns = @JoinColumn(name = "account_id"))
	List<Roles> roles = new ArrayList<>();

}
