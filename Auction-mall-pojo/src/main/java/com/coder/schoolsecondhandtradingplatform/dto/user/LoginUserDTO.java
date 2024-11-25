package com.coder.schoolsecondhandtradingplatform.dto.user;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/*
 * 登录参数DTO实体类
 * */
@Data
public class LoginUserDTO {
    @NotBlank(message = "密码不能为空")
    private String password;     // 用户密码
    @NotBlank(message = "邮箱不能为空")
    private String email;     // 用户名
}