package com.demo.common.utils.ajax;

import lombok.Data;

@Data
public class Result {

	private boolean success;
	private String message;
	private Object data;

}
