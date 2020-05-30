package cn.edu.zucc.login.common.result;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author crabxyj
 * @date 2020/5/30 14:59
 */
@ControllerAdvice
public class ResultWrapControllerAdvice implements ResponseBodyAdvice {
    /**
     * 判断类或方法上是否有注解
     */
    @Override
    public boolean supports(MethodParameter returnType, Class aClass) {
        // 方法上是否有注解
        ResultWrap methodAnnotation = returnType.getMethodAnnotation(ResultWrap.class);
        if(methodAnnotation!=null){
            return methodAnnotation.isWrap();
        }
        // 类上注解判断
        ResultWrap classAnnotation = returnType.getContainingClass().getAnnotation(ResultWrap.class);
        if(classAnnotation!=null){
            return classAnnotation.isWrap();
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 已包装的不做处理
        if(body instanceof Result){
            return body;
        }
        Result result = Result.wrapSuccessResult(body);
        // 处理controller返回为字符串时, 转换报异常的bug
        if(body instanceof String){
            return JSON.toJSONString(result);
        }
        return result;
    }
}
