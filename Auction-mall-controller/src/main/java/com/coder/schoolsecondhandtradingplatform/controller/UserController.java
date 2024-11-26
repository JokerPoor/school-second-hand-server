package com.coder.schoolsecondhandtradingplatform.controller;


import com.coder.schoolsecondhandtradingplatform.contants.TipMessage;
import com.coder.schoolsecondhandtradingplatform.dto.user.LoginUserDTO;
import com.coder.schoolsecondhandtradingplatform.dto.user.RegisterUserDTO;
import com.coder.schoolsecondhandtradingplatform.entity.User;
import com.coder.schoolsecondhandtradingplatform.service.UserService;
import com.coder.schoolsecondhandtradingplatform.utils.JwtUtils;
import com.coder.schoolsecondhandtradingplatform.utils.Result;
import com.coder.schoolsecondhandtradingplatform.utils.ValidationUtils;
import com.coder.schoolsecondhandtradingplatform.vo.user.LoginUserVO;
import com.coder.schoolsecondhandtradingplatform.vo.user.RegisterUserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Tag(name = "UserController", description = "用户相关操作")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/disclosed/login")
    @Operation(summary = "用户登录", description = "登录系统")
    public Result login(@RequestBody @Valid LoginUserDTO userDTO, BindingResult bindingResult) {
        String validationError = ValidationUtils.handleValidationErrors(bindingResult);
        if (validationError != null) {
            return Result.error(validationError);
        }

        LoginUserVO loginUserVO = new LoginUserVO();

        BeanUtils.copyProperties(userDTO, loginUserVO);

        /*
        * md5加密
        * */
        loginUserVO.setPassword(DigestUtils.md5DigestAsHex(loginUserVO.getPassword().getBytes()));

        //调用业务层：登录功能
        User loginUserResult = userService.login(loginUserVO);

        //判断：登录用户是否存在
        if(loginUserResult != null){
            //自定义信息
            Map<String , Object> claims = new HashMap<>();
            claims.put("userId", loginUserResult.getUserId());
            claims.put("username",loginUserResult.getUsername());

            //使用JWT工具类，生成身份令牌
            String token = JwtUtils.generateJwt(claims);
            return Result.success(token);
        }
        return Result.error(TipMessage.Login_Error.getDescription());
    }

    @PostMapping("/disclosed/register")
    @Operation(summary = "用户注册", description = "注册系统使用账号")
    public Result register(@RequestBody @Valid RegisterUserDTO userDTO, BindingResult bindingResult) {

        String validationError = ValidationUtils.handleValidationErrors(bindingResult);
        if (validationError != null) {
            // 校验失败
            return Result.error(validationError);
        }

        User userByEmail = userService.selectUserByEmail(userDTO.getEmail());

        if(userByEmail != null){
            return Result.error(TipMessage.Email_Used.getDescription());
        }

        RegisterUserVO userVO = new RegisterUserVO();

        BeanUtils.copyProperties(userDTO,userVO);

        /*
        * md5加密
        * */
        userVO.setPassword(DigestUtils.md5DigestAsHex(userVO.getPassword().getBytes()));

        userVO.setUsername(UUID.randomUUID().toString().replace("-", ""));

        userService.register(userVO);

        if(userVO.getUserId() != null){
            return Result.success();
        }
        return Result.error(TipMessage.Register_Failed.getDescription());
    }
}
