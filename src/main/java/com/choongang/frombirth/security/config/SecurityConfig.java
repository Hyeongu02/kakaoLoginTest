package com.choongang.frombirth.security.config;

import com.choongang.frombirth.security.handler.CustomAccessDeniedHandler;
import com.choongang.frombirth.security.handler.CustomAuthenticationFailureHandler;
import com.choongang.frombirth.security.handler.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //시큐리티 설정파일을 시큐리티 필터에 등록
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;



    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //csrf토큰 사용x
        http.csrf().disable();

        http.authorizeRequests(authorize -> authorize
                .anyRequest().permitAll() // 모든 요청에 대해 허용
        );

        // 폼 로그인 설정 (로그인 기능을 허용하되, 인증을 강제하지 않음)
        http.formLogin()
                .loginPage("/common/login")
                .loginProcessingUrl("/common/loginForm")
                .successHandler(successHandler)
                .failureHandler(failureHandler);

        http.logout()
                .logoutUrl("/logout") // 로그아웃을 위한 URL
                .logoutSuccessUrl("/common/login") // 로그아웃 성공 후 리다이렉트할 URL
                .invalidateHttpSession(true) // 세션 무효화
                .deleteCookies("JSESSIONID"); // 쿠키 삭제

        http.exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler());

        return http.build();
    }
}