package com.demo.client.controller;

import com.demo.common.dto.req.UserLoginRequest;
import com.demo.common.utils.ajax.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login/user")
@Slf4j
public class UserLoginController {

	@PostMapping("/login")
	public Result login(@RequestBody UserLoginRequest userLoginRequest) {
		log.info("用户登录: {}", userLoginRequest.getUsername());
		Result result = new Result();
		result.setData(userLoginRequest.getUsername());
		result.setSuccess(true);
		return result;
	}

	@PostMapping("/logout")
	public Result logout() {
		log.debug("logout");
		return new Result(true, "登出");
	}

	@GetMapping("/info")
	public Result info() {
		Result result = new Result();
		result.setSuccess(true);
		result.setData("admin");
		return result;
	}

}
