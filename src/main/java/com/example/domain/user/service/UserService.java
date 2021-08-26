package com.example.domain.user.service;

import java.util.List;

import com.example.domain.user.model.MUser;

public interface UserService {

	//ユーザー登録
	public void signup(MUser user);

	//ユーザー全件取得
	public List<MUser> getUsers(MUser user);

	//ユーザー1件取得
	public MUser getUserOne(String userId);

	//ユーザー1件更新
	public void updateUserOne(String userId,
			String password,
			String userName);

	//ユーザー1件削除
	public void deleteUserOne(String userId);
}
