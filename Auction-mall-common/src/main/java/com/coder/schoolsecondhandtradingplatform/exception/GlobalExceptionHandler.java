package com.coder.schoolsecondhandtradingplatform.exception;

import com.coder.schoolsecondhandtradingplatform.contants.TipMessage;
import com.coder.schoolsecondhandtradingplatform.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.regex.Pattern;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 捕获业务异常
    @ExceptionHandler(Exception.class) //指定能够处理的异常类型
    public Result ex(Exception e){
        e.printStackTrace(); // 打印堆栈中的异常信息
        String message = e.getMessage();
        // 捕获到异常之后，响应一个标准的Result
        return Result.error(message != null ? message : TipMessage.Response_Default_Error.getDescription());
    }

    /*
     * 捕获SQL异常
     * */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result exceptionHandle(DataIntegrityViolationException e){
        String errMessage = e.getMessage();
        // 检查错误信息中是否包含特定的违规信息
        if (errMessage.contains("doesn't have a default value")) {
            // 正则表达式匹配字段名
            String regex = "Field '([^']+)' doesn't have a default value";
            Pattern pattern = Pattern.compile(regex);
            String record = pattern.matcher(errMessage).group(1);
            String message = record + TipMessage.Record_Is_Empty.getDescription();
            return Result.error(message);
        } else {
            return Result.error(TipMessage.Response_Default_Error.getDescription());
        }
    }
}
