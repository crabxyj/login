package cn.edu.zucc.login.common.logs;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author crabxyj
 * @date 2020/6/1 11:23
 * controller 层日志记录
 */
@Slf4j
@Aspect
@Component
public class ControllerLogAspect {
//    private static final String LOG_TYPE = "controller";
    private static final String BEFORE_CONTROLLER = "请求信息： URL = [{}],\t" +
            "请求方式：[{}],\t" +
            "请求IP：[{}],\t" +
            "类方法：[{}.{}],\t" +
            "参数列表：[{}]";
    private static final String AFTER_CONTROLLER = "请求结果：[{}]";

    @Pointcut("execution(public * cn.edu.zucc.*.controller..*.*(..))")
    public void requestLog() {
    }

    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String url = request.getRequestURI();
        String method = request.getMethod();
        String ip = request.getRemoteAddr();
        String classMethod = signature.getDeclaringTypeName();
        String name = signature.getName();
        // 处理请求参数

        String[] paramNames = ((MethodSignature) signature).getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        int paramlength = null==paramNames ? 0 :paramNames.length;
        JSONObject params = new JSONObject();
        for(int i=0;i<paramlength;i++){
            params.put(paramNames[i],paramValues[i]);
        }
        log.info(BEFORE_CONTROLLER,url,method,ip,classMethod,name,params.toJSONString());
    }

    @AfterReturning(value = "requestLog()", returning = "obj")
    public void doAfterReturning(Object obj) {
        log.info(AFTER_CONTROLLER,JSONObject.toJSONString(obj));
    }
}
