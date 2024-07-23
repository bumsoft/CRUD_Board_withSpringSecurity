package com.example.CRUD_Board_withSpringSecurity.SpringSecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//Role은 그룹화된 권한
//Authority는 세분화된 권한(Role만으로 제어가 어려운 복잡한 시스템에서 사용)
//Role:유저/관리자
//Authority:읽기권한 쓰기권한 등등

//hasAuthority: 특정 권한을 가진 사용자만 접근 가능.
//hasAnyAuthority: 지정된 여러 권한 중 하나를 가진 사용자만 접근 가능.
//authenticated: 로그인된 사용자만 접근 가능.
//permitAll: 모든 사용자 접근 가능.
//denyAll: 모든 사용자 접근 불가.
//hasRole: 특정 역할을 가진 사용자만 접근 가능 (ROLE_ 접두사 자동 추가).
//hasAnyRole: 지정된 여러 역할 중 하나를 가진 사용자만 접근 가능 (ROLE_ 접두사 자동 추가)

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests

                        .requestMatchers("post/new").authenticated()
                        .requestMatchers("post/{id}/comment/new").authenticated()


                        .requestMatchers("/onlylogin").authenticated()
                        //.requestMatchers("onlyadmin").hasAu
                        .anyRequest().permitAll())

                .formLogin((formLogin) -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/"))

                .logout((logout)->logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)) //세션삭제
        ;


        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        //return NoOpPasswordEncoder.getInstance(); //인코드 안할시 noop
        //new StandardPasswordEncoder();
        //BCryptPasswordEncoder;
        //ScryptPasswordEncoder;
        //Argaon2PasswordEncoder;
        return new BCryptPasswordEncoder(); //권장
    }

    @Bean // 서비스와 인코더를 이용해인증과 권한부여 프로세스를 처리함.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
