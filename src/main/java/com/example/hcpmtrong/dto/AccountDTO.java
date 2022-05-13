package com.example.hcpmtrong.dto;


import com.example.hcpmtrong.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO extends AbstractDTO {

	private String username;
	private String password;
	private Status status;
	private Long roleId;

}
