package com.demo.common.utils.page;

import lombok.Data;

import java.util.List;

@Data
public class PageModel<T> {

	private int pageNo;
	private int size;
	private int totalCount;
	private int totalPage;
	private List<T> list;

}
