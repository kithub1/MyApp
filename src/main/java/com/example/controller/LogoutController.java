/*package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;


SecurityConfig.javaにログアウト処理を書いたからこのコントローラーは必要ない

@Controller
@Slf4j
public class LogoutController {

	@PostMapping("/logout")
	public String postLogout() {
		log.info("ログアウト");
		return "redirect:/login";
	}
}
*/