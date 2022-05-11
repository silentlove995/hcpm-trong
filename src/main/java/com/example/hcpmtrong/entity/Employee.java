package com.example.hcpmtrong.entity;



import com.example.hcpmtrong.enums.Gender;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "accounts")
public class Employee extends BaseEntity {

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "employee_code")
	private String employeeCode;

	@Column(name = "category")
	private String category;

	@Column(name = "employee_gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "employee_avatar")
	private String employeeAvatar;

	@Column(name = "employee_phone")
	private String employeePhone;

	@Column(name = "employee_address")
	private String employeeAddress;

	@Column(name = "employee_email")
	private String employeeEmail;

	@Column(name = "date_of_birth")
	private LocalDateTime dateOfBirth;

	@Column(name = "join_date")
	private LocalDateTime joinDate;

	@Column(name = "descriptions")
	private String descriptions;
}
