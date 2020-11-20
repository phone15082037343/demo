package com.demo.provider.controller;

import com.demo.common.dto.req.UserRequest;
import com.demo.common.dto.res.UserResponse;
import com.demo.common.utils.page.PageModel;
import com.demo.provider.entity.User;
import com.demo.provider.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public UserResponse save(@RequestBody UserRequest userRequest) {
		User user = new User();
		BeanUtils.copyProperties(userRequest, user);

		userRepository.save(user);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(user, userResponse);
		return userResponse;
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable(name = "id") String id) {
		userRepository.deleteById(id);
		return id;
	}

	@GetMapping("/{id}")
	public UserResponse findById(@PathVariable(name = "id") String id) {
		User user = userRepository.findById(id).orElse(null);
		if (user != null) {
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(user, userResponse);
			return userResponse;
		}
		return null;
	}

	@GetMapping
	public PageModel<UserResponse> find(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	                                    @RequestParam(name = "size", defaultValue = "10") int size) {
		Page<User> page = userRepository.findAll(PageRequest.of(pageNo - 1, size));
		PageModel<UserResponse> pm = new PageModel<>();
		pm.setPageNo(pageNo);
		pm.setSize(size);
		pm.setTotalCount((int) page.getTotalElements());
		pm.setTotalPage(page.getTotalPages());
		List<UserResponse> list = page.stream().map(user -> {
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(user, userResponse);
			return userResponse;
		}).collect(Collectors.toList());
		pm.setList(list);
		return pm;
	}

}
