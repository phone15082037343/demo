package com.demo.common.dto.req;

import lombok.Data;

@Data
public class UserLoginRequest {

	private String username;
	private String password;

}
