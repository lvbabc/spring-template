package com.rex.springtemplate.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rex.springtemplate.annotation.NotResponseBody;
import com.rex.springtemplate.exception.APIException;
import com.rex.springtemplate.vo.ResultVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.function.Predicate;

@RestControllerAdvice(basePackages = "com.rex.springtemplate.controller")
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
        Predicate<MethodParameter> alreadyReturn = x -> x.getParameterType().equals(ResultVO.class);
        // 如果方法上加了我们的自定义注解也没有必要进行额外的操作
        Predicate<MethodParameter> hasMethodAnnotation = x -> x.hasMethodAnnotation(NotResponseBody.class);

        return !(alreadyReturn.or(hasMethodAnnotation).test(returnType));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new ResultVO<>(body));
            } catch (JsonProcessingException e) {
                throw new APIException("返回String类型错误");
            }
        }

        return new ResultVO<>(body);
    }
}
