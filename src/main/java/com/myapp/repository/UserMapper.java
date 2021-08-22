package com.myapp.repository;

import org.apache.ibatis.annotations.Mapper;

import com.myapp.domain.user.model.MUser;

@Mapper
public interface UserMapper {

	// ユーザー登録
	public int insertOne(MUser user);
}
