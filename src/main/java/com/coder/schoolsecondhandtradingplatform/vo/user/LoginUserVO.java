package com.coder.schoolsecondhandtradingplatform.vo.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserVO {
    private Long userId; // id
    private String password;     // 用户密码
    private String email;     // 邮箱
}
