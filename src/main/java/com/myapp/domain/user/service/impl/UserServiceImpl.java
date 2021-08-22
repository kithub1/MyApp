package com.myapp.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.domain.user.model.MUser;
import com.myapp.domain.user.service.UserService;
import com.myapp.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Override
	public void signup(MUser user) {
		user.setDepartmentId(1); //部署
		user.setRole("ROLE_GENELAL"); //ロール
		mapper.insertOne(user);

	}

}
