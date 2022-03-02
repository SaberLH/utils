package com.spring.security.learn.domain.dto;


import com.spring.security.learn.validation.annotation.PasswordMatches;
import com.spring.security.learn.validation.annotation.ValidEmail;
import com.spring.security.learn.validation.annotation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author lihu
 */
@PasswordMatches
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = -3057291386996958394L;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 50, message = "用户名长度必须在4到50个字符之间")
    private String username;

    @NotNull
    @ValidPassword
    private String password;

    @NotNull
    private String matchingPassword;

    @NotNull
    @ValidEmail
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 50, message = "姓名长度必须在4到50个字符之间")
    private String name;
}
