package com.demo.client.controller;

import com.demo.client.api.UserClient;
import com.demo.common.dto.req.UserRequest;
import com.demo.common.dto.res.UserResponse;
import com.demo.common.utils.ajax.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserClient userClient;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public Result save(@RequestBody UserRequest userRequest) {
		Result result = new Result();
		result.setData(userClient.save(userRequest));
		result.setSuccess(true);
		return result;
	}

	@DeleteMapping("/{id}")
	public Result deleteById(@PathVariable(name = "id") String id) {
		Result result = new Result();
		result.setData(userClient.deleteById(id));
		result.setSuccess(true);
		return result;
	}

	@GetMapping("/{id}")
	public Result findById(@PathVariable(name = "id") String id) {
		Result result = new Result();
		result.setData(userClient.findById(id));
		result.setSuccess(true);
		return result;
	}

	@GetMapping
	public Result find(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	                                    @RequestParam(name = "size", defaultValue = "10") int size) {
		Result result = new Result();
		result.setData(userClient.find(pageNo, size));
		result.setSuccess(true);
		return result;
	}

}
