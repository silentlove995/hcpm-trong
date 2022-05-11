package com.example.hcpmtrong.utils;

import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class FormUtil {

	public static Pageable toPageable(Map<String, String> model) {
		int page = 1;
		int size = 10;

		if (model.get("page") != null && !model.get("page").equals("")) {
			page = Integer.parseInt(model.get("page"));
		}
		if (model.get("size") != null && !model.get("size").equals("")) {
			size = Integer.parseInt(model.get("size"));
		}
		return PageRequest.of(page - 1, size);
	}
}
