package shop.mtcoding.metablog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import shop.mtcoding.metablog.core.auth.MyUserDetails;
import shop.mtcoding.metablog.core.exception.ssr.Exception400;
import shop.mtcoding.metablog.core.util.Script;
import shop.mtcoding.metablog.model.user.User;

@Slf4j
@Configuration
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 1. CSRF 해제
        http.csrf().disable(); // postman 접근해야 함!! - CSR 할때!!

        // 2. frame option 해제 (시큐리티 h2-console 접속 허용을 위해)
        http.headers().frameOptions().disable();

        // 3. Form 로그인 설정
        http.formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .successHandler((req, resp, authentication) -> {
                    log.debug("디버그 : 로그인 성공");
                })
                .failureHandler((req, resp, ex) -> {
                    log.debug("디버그 : 로그인 실패 : " + exception.getMessage);
                 })
                .and()
                .logout()
                .logoutSuccessUrl("/");

        // 3. 인증, 권한 필터 설정
        http.authorizeRequests(
                authorize -> authorize.antMatchers("/api/s/**").authenticated()
                        .antMatchers("/s/**").authenticated()
                        .anyRequest().permitAll()
        );

        return http.build();
    }
}
