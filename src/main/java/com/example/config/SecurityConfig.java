package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		//セキュリティを適用しない
		web.ignoring()
				.antMatchers("/webjars/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/h2-console/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		//ログイン不要ページの設定
		http
				.authorizeRequests()
				.antMatchers("/login").permitAll() //直リンクOK
				.antMatchers("/user/signup").permitAll() //直リンクOK
				.antMatchers("/admin").hasAuthority("ROLE_ADMIN") //アドミン権限のみOK
				.anyRequest().authenticated(); //それ以外は直リンクNG

		//ログイン処理
		http
				.formLogin()
				.loginProcessingUrl("/login") //ログイン処理のパス
				.loginPage("/login") //ログインページの指定
				.failureUrl("/login?error") //ログイン失敗時の遷移先
				.usernameParameter("userId") //ログインページのユーザーID
				.passwordParameter("password") //ログインページのパスワード
				.defaultSuccessUrl("/user/list", true); //成功後の遷移先"

		//ログアウト処理
		http
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout");
	}

	//認証の設定
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder encoder = passwordEncoder();

		//ユーザーデータ認証
		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(encoder);

		/*//インメモリ認証（仮のIDとパスワードを用意してログインできるようにする）
		auth
				.inMemoryAuthentication()
				.withUser("user") //userを追加
				.password(encoder.encode("user"))
				.roles("GENERAL")
				.and()
				.withUser(encoder.encode("admin")) //adminを追加
				.password("admin")
				.roles("ADMIN");*/
	}
}
