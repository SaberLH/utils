package com.spring.serurity.learn.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lihu
 */
@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
@Configuration
@Import(SecurityProblemSupport.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ObjectMapper objectMapper;
    private final SecurityProblemSupport securityProblemSupport;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .requestMatchers(req -> req.mvcMatchers("/api/**", "/admin/**", "/authorize/**"))
//            .sessionManagement(sessionManagement -> sessionManagement
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(exceptionHandling -> exceptionHandling
                    .authenticationEntryPoint(securityProblemSupport)
                    .accessDeniedHandler(securityProblemSupport))
            .authorizeRequests(authorizeRequests -> authorizeRequests
                    .antMatchers("/authorize/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/api/**").hasRole("USER")
                    .anyRequest().authenticated())
//                .addFilterAt(restAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .csrf(csrf -> csrf.ignoringAntMatchers("/authorize/**", "/admin/**", "/api/**"))
//            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable);
    }

}
