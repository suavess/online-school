package com.suave.base.exception.handler;

import com.suave.base.exception.MyException;
import com.suave.common.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Suave
 * @date 2021/1/25 3:02 下午
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResult error(Exception e) {
        e.printStackTrace();
        return CommonResult.error().message("执行了全局异常处理..");
    }

    @ExceptionHandler(ArithmeticException.class)
    public CommonResult error(ArithmeticException e) {
        e.printStackTrace();
        return CommonResult.error().message("执行了ArithmeticException异常处理..");
    }

    @ExceptionHandler(MyException.class)
    public CommonResult error(MyException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return CommonResult.error().code(e.getCode()).message(e.getMsg());
    }


}
