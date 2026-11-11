package com.tasukarte.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {
    	
    	//permitAll():ログイン前でもアクセス可能
    	//authenticated():ログイン済みユーザーのみアクセス可能
    	//loginPage():ログイン画面のURL
    	//loginProcessingUrl():ログイン認証処理を受け付けるURL
    	//usernameParameter():職員IDを受け取るフィールド名
    	//passwordParameter():パスワードを受け取るフィールド名
    	//defaultSuccessUrl():ログイン成功後の遷移先
    	//failureUrl():ログイン失敗後の遷移先
    	//invalidateHttpSession():ログアウト時にセッション無効化
    	
        http
            // URLごとのアクセス制限
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login",
                    "/css/**",
                    "/js/**",
                    "/images/**"
                ).permitAll()
                .anyRequest().authenticated()
            )

            // ログイン設定
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("staffId")
                .passwordParameter("password")
                .defaultSuccessUrl("/top", true)
                .failureUrl("/login?error")
                .permitAll()
            )

            // ログアウト設定
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            );

        return http.build();
    }

    // 現段階のテスト用パスワード照合
    // 現在平文のため、職員情報登録作成時にハッシュに変更
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(
                    CharSequence rawPassword,
                    String encodedPassword) {

                return rawPassword != null
                        && rawPassword.toString().equals(encodedPassword);
            }
        };
    }
}