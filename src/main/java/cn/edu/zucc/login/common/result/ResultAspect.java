package cn.edu.zucc.login.common.result;

import cn.edu.zucc.login.common.error.CommonErrors;
import cn.edu.zucc.login.common.exception.BaseException;
import cn.edu.zucc.login.common.exception.BusinessException;
import cn.edu.zucc.login.common.result.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @author crabxyj
 * @date 2020/5/30 14:13
 */
//@Aspect
//@Component
public class ResultAspect {

    /**
     * 定义返回结果增强切面
     */
    @Around("@annotation(cn.edu.zucc.login.common.result.ResultWrap)")
    public Object resultAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Object obj = null;
        try{
            obj = joinPoint.proceed();
        }catch (BusinessException e){
            return Result.wrapErrorResult(e);
        }catch (BaseException e){
            return Result.wrapErrorResult(CommonErrors.SYSTEM_ERROR);
        }
        return Result.wrapSuccessResult(obj);
    }

}
