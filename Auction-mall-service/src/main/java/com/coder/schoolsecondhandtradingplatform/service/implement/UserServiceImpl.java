package com.coder.schoolsecondhandtradingplatform.service.implement;

import com.coder.schoolsecondhandtradingplatform.entity.User;
import com.coder.schoolsecondhandtradingplatform.mapper.UserMapper;
import com.coder.schoolsecondhandtradingplatform.service.UserService;
import com.coder.schoolsecondhandtradingplatform.vo.user.LoginUserVO;
import com.coder.schoolsecondhandtradingplatform.vo.user.RegisterUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(LoginUserVO user) {
        return userMapper.login(user);
    }

    @Override
    public void register(RegisterUserVO user) {
         userMapper.register(user);
    }

    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }
}
