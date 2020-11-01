package com.rex.springtemplate.advice;

import com.rex.springtemplate.annotation.ExceptionCode;
import com.rex.springtemplate.enums.ResultCode;
import com.rex.springtemplate.exception.APIException;
import com.rex.springtemplate.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(APIException.class)
    public ResultVO<String> apiExceptionHandler(APIException e) {
        log.error(e.getMsg());
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) //设置状态码为 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) throws NoSuchFieldException {
        // 从异常对象中拿到错误信息
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        // 参数的Class对象，等下好通过字段名称获取Field对象
        Class<?> parameterType = e.getParameter().getParameterType();
        // 拿到错误的字段名称
        String fieldName = Objects.requireNonNull(e.getBindingResult().getFieldError()).getField();
        Field field = parameterType.getDeclaredField(fieldName);
        // 获取Field对象上的自定义注解
        ExceptionCode annotation = field.getAnnotation(ExceptionCode.class);

        if (annotation != null) {
            return new ResultVO<>(annotation.code(), annotation.message(), defaultMessage);
        }

        log.error(defaultMessage);
        // 没有注解就提取错误提示信息进行返回统一错误码
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, defaultMessage);
    }

    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVO<String> parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        log.error(e.getMessage());
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, "请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVO<String> parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, "参数体不能为空");
    }
}
