package cn.edu.zucc.login.common.result;

import cn.edu.zucc.login.common.error.CommonErrors;
import cn.edu.zucc.login.common.exception.BaseException;
import cn.edu.zucc.login.common.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author crabxyj
 * @date 2020/5/30 15:52
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result businessException(BusinessException e){
        System.out.println(e.getMessage());
        return Result.wrapErrorResult(e);
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public Result baseException(){
        return Result.wrapErrorResult(CommonErrors.SYSTEM_ERROR);
    }

}
