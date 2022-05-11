package com.example.hcpmtrong.enums;

import lombok.Getter;

@Getter
public enum Status {

	ACTIVE("Hoat dong"),
	INACTIVE("Khong hoat dong");

	private final String value;

	Status(String value) {
		this.value = value;
	}
}
