package com.coder.schoolsecondhandtradingplatform.service;

import com.coder.schoolsecondhandtradingplatform.entity.User;
import com.coder.schoolsecondhandtradingplatform.vo.user.LoginUserVO;
import com.coder.schoolsecondhandtradingplatform.vo.user.RegisterUserVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User login(LoginUserVO emp);

    void register(RegisterUserVO user);

    User selectUserByEmail(String email);
}
