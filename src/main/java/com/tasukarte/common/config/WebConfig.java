package com.tasukarte.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer{
	
	private final LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//LoginIntercetorで設定した、ログインしていなければログイン画面へ遷移する
		//決まりをどのURLへのアクセスで適応するのかを設定
		registry.addInterceptor(loginInterceptor)
			.addPathPatterns("/top");
	}
}
