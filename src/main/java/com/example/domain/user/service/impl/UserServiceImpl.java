package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

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

	@Override
	public List<MUser> getUsers() {
		return mapper.findMany();
	}

}
