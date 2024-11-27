package com.coder.schoolsecondhandtradingplatform.mapper;


import com.coder.schoolsecondhandtradingplatform.entity.User;
import com.coder.schoolsecondhandtradingplatform.vo.user.LoginUserVO;
import com.coder.schoolsecondhandtradingplatform.vo.user.RegisterUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from users where email = #{email} and password = #{password}")
    User login(LoginUserVO emp);

    void register(RegisterUserVO user);

    @Select("select * from users where email = #{email}")
    User selectUserByEmail(String email);
}
