package com.demo.client.api;

import com.demo.client.conf.FeignConfiguration;
import com.demo.client.fallback.UserClientFallbackFactory;
import com.demo.common.dto.req.UserRequest;
import com.demo.common.dto.res.UserResponse;
import com.demo.common.utils.page.PageModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "demo-provider", path = "/user", configuration = FeignConfiguration.class, fallbackFactory = UserClientFallbackFactory.class)
public interface UserClient {

	@PostMapping
	UserResponse save(@RequestBody UserRequest userRequest);

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable(name = "id") String id);

	@GetMapping("/{id}")
	public UserResponse findById(@PathVariable(name = "id") String id);

	@GetMapping
	PageModel<UserResponse> find(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	                             @RequestParam(name = "size", defaultValue = "10") int size);

}
