package com.demo.client.fallback;

import com.demo.client.api.UserClient;
import com.demo.common.dto.req.UserRequest;
import com.demo.common.dto.res.UserResponse;
import com.demo.common.utils.page.PageModel;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {

	@Override
	public UserClient create(Throwable throwable) {
		return new UserClient() {
			@Override
			public UserResponse save(UserRequest userRequest) {
				return null;
			}

			@Override
			public String deleteById(String id) {
				return id;
			}

			@Override
			public UserResponse findById(String id) {
				return null;
			}

			@Override
			public PageModel<UserResponse> find(int pageNo, int size) {
				return null;
			}
		};
	}

}
