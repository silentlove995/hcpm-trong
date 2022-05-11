package com.example.hcpmtrong.dto;

import com.example.hcpmtrong.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO extends AbstractDTO {

	private String customerName;
	private String customerPhone;
	private String customerEmail;
	private String brand;
	private String country;
	private String language;
	private String workplace;
}
