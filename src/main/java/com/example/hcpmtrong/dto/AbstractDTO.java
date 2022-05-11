package com.example.hcpmtrong.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractDTO {

	private Long id;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
}
