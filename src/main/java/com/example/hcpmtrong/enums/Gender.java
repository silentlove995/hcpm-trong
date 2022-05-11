package com.example.hcpmtrong.enums;

import lombok.Getter;

@Getter
public enum Gender {
	MALE("Nam"),
	FEMALE("Nu"),
	OTHERS("Khac");

	private final String value;

	Gender(String value) {
		this.value = value;
	}

}
