package com.example.hcpmtrong.utils.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageList<T> {

	private int currentPage;
	private int pageSize;
	private long total;
	private boolean success;
	private int totalPage;
	private List<T> list;
}
