package cn.edu.zucc.login.common.logs;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @author crabxyj
 * @date 2020/6/1 13:53
 * service 层日志记录
 */
@Slf4j
@Aspect
@Component
public class ServiceLogAspect {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    private static final String AROUND_SERVICE = "Method：[{}.{}], Start：[{}], End：[{}], Total：[{}]";

    @Pointcut("execution(public * cn.edu.zucc.*.service..*.*(..))")
    public void serviceLog() {

    }

    @Around("serviceLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        log.info(AROUND_SERVICE,className,methodName,
                dateFormat.format(startTime),
                dateFormat.format(endTime),
                endTime-startTime);
        return result;
    }
}
