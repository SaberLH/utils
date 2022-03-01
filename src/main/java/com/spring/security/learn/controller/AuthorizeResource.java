package com.spring.security.learn.controller;

import com.spring.security.learn.domain.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * @author lihu
 */
@RestController
@RequestMapping("/authorize")
public class AuthorizeResource {

    @PostMapping("/register")
    public UserDto register(@Valid @RequestBody UserDto userDto) {

        return userDto;
    }

    @GetMapping("/authentication")
    public Authentication getAuthentication() {
        System.out.println("getAuthorities:" + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        System.out.println("getCredentials:" + SecurityContextHolder.getContext().getAuthentication().getCredentials());
        System.out.println("getDetails:" + SecurityContextHolder.getContext().getAuthentication().getDetails());
        System.out.println("getPrincipal:" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/principal")
    public Principal getPrincipal(Principal principal) {

        return principal;
    }

}
