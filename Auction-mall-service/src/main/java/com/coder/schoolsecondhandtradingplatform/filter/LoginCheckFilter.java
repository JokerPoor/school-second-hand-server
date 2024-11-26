package com.coder.schoolsecondhandtradingplatform.filter;

import com.alibaba.fastjson.JSONObject;
import com.coder.schoolsecondhandtradingplatform.contants.TipMessage;
import com.coder.schoolsecondhandtradingplatform.context.BaseContext;
import com.coder.schoolsecondhandtradingplatform.utils.JwtUtils;
import com.coder.schoolsecondhandtradingplatform.utils.Result;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    public static void returnResult(HttpServletResponse response) throws IOException {
        Result responseResult = Result.error(TipMessage.NO_Login.getDescription());
        //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
        String json = JSONObject.toJSONString(responseResult);
        response.setContentType("application/json;charset=utf-8");
        //响应
        response.getWriter().write(json);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //前置：强制转换为http协议的请求对象、响应对象 （转换原因：要使用子类中特有方法）
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求路径：{}", url); //请求路径：http://localhost:8080/login

        //2.判断请求url中是否包含login和swagger的资源。如果包含，说明是登录操作，放行
        if(url.contains("/disclosed/") || url.contains("/doc.html") || url.contains("/webjars/") || url.contains("/v3/api-docs") || url.contains("/favicon.ico")){
            filterChain.doFilter(request, response);//放行请求
            return;//结束当前方法的执行
        }

        //3.获取请求头中的令牌（token）
        String token = request.getHeader("token");
        log.info("从请求头中获取的令牌：{}",token);


        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(token)){
            log.info("Token不存在");
            returnResult(response);
            return;
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Object userIdObj = claims.get("userId");
            BaseContext.setCurrentId(((Integer) userIdObj).longValue());
        }catch (Exception e){
            log.info("令牌解析失败!");
            returnResult(response);
            return;
        }


        //6.放行
        filterChain.doFilter(request, response);
    }
}