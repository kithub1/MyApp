package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.user.model.MUser;

@Mapper
public interface UserMapper {

	// ユーザー登録
	public int insertOne(MUser user);

	//ユーザー全件取得
	public List<MUser> findMany(MUser user);

	//ユーザー1件取得
	public MUser findOne(String userId);

	//ユーザー1件更新
	public void updateOne(@Param("userId") String userId,
			@Param("password") String password,
			@Param("userName") String userName);

	//ユーザー１件削除
	public void deleteOne(@Param("userId") String userId);

	//ログインユーザー取得
	public MUser findLoginUser(String userId);
}
