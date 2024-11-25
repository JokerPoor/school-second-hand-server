package com.coder.schoolsecondhandtradingplatform.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserDTO {

    @NotBlank(message = "密码不能为空")
    private String password; // 用户密码

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不合规")
    private String email; // 邮箱
}
