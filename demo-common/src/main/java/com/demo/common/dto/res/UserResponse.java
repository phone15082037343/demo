package com.demo.common.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {

	private String userId;
	private String name;
	private int sex;
	private int age;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birthday;

}
