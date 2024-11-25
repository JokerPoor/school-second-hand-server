package com.coder.schoolsecondhandtradingplatform.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/*
* 校验工具函数
* */
public class ValidationUtils {

    // 校验错误信息处理
    public static String handleValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                if (error instanceof FieldError fieldError) {
                    errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append(" ");
                } else {
                    errorMessage.append(error.getDefaultMessage()).append(" ");
                }
            });
            return errorMessage.toString().trim();
        }
        return null;  // 没有错误
    }
}
