package com.spring.serurity.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lihu
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;
    private String name;
    private List<String> roles;
}
