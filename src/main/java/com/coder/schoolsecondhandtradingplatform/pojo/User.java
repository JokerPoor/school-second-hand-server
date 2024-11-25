package com.coder.schoolsecondhandtradingplatform.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/*
* 用户实体类
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;          // 用户ID
    private String email;        // 用户邮箱
    private String password;     // 用户密码
    private String username;     // 用户名
    private String phone;        // 用户手机号
    private String avatar;       // 用户头像链接
    private String role;         // 用户角色（user 或 admin）
    private LocalDateTime createdAt; // 注册时间
}
