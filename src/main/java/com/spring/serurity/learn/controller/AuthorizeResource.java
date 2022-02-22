package com.spring.serurity.learn.controller;

import com.spring.serurity.learn.domain.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

}
