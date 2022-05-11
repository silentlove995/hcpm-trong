package com.example.hcpmtrong.dto;


import com.example.hcpmtrong.enums.Gender;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO extends AbstractDTO {

	private String employeeName;
	private String employeeCode;
	private String category;
	private String employeeAvatar;
	private String employeeAddress;
	private String employeePhone;
	private String employeeEmail;
	private Gender gender;
	private LocalDateTime dateOfBirth;
	private LocalDateTime joinDate;
	private String descriptions;
}
