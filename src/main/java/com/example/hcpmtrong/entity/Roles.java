package com.example.hcpmtrong.entity;

import com.example.hcpmtrong.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles extends BaseEntity {

	@Column(name = "roles")
	private String roles;

	@ManyToMany(mappedBy = "roles")
	List<Account> accounts = new ArrayList<>();
}
