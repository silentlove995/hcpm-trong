package com.example.hcpmtrong.entity;


import com.example.hcpmtrong.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "customers")
public class Customer extends BaseEntity {

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "customer_phone")
	private String customerPhone;

	@Column(name = "customer_email")
	private String CustomerEmail;

	@Column(name = "brand")
	private String brand;

	@Column(name = "country")
	private String country;

	@Column(name = "language")
	private String language;

	@Column(name = "workplace")
	private String workplace;
}
