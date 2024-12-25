package com.Wellcom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화 됨.

        // .antMatchers("/admin/**").hasRole("ADMIN")
        //  .anyRequest().authenticated();
        // 이렇게 하면 "/admin/**" 경로에 대한 요청은 ADMIN 권한이 있어야만 접근할 수 있고, 그 외의 모든 요청은 인증된 사용자만 접근할 수 있습니다.

        http.csrf().disable(); // 8080포트 통합이라서 csrf 비활성화
        http
            .cors() // CORS 허용 설정
            .and()
            .authorizeRequests()
            //.antMatchers("/static/images/**", "/static/css/**", "/static/js/**").permitAll() // 정적 리소스 접근 허용
            .antMatchers("/", "/signup/**", "/login/**", "/member/login/**", "/product/**", "/products/**", "/cart/**").permitAll() // 해당 URL을 제외하고 시큐리티가 가로챔
            .antMatchers("/asdf/**").authenticated()
        .and()
        .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/member/login")    // 로그인 요청 URL
            .defaultSuccessUrl("/");

    }

}
