package cn.edu.zucc.login.common.result;

import cn.edu.zucc.login.common.exception.BusinessException;
import cn.edu.zucc.login.common.error.ServiceErrors;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author crabxyj
 * @date 2020/5/30 13:55
 */
@Getter
@ToString
public class Result<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    private Result(T data,Integer code,String msg){
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result wrapSuccessResult(T data){
        return new Result<T>(data,0,null);
    }

    public static <T> Result wrapSuccessResult(String msg,T data){
        return new Result<T>(data,0,msg);
    }

    public static <T> Result wrapErrorResult(ServiceErrors error, Object... extendMsg){
        return new Result<T>(null,error.getCode(),String.format(error.getMsg(),extendMsg));
    }

    public static <T> Result wrapErrorResult(Integer code,String msg){
        return new Result<T>(null,code,msg);
    }

    public static <T> Result wrapErrorResult(BusinessException e){
        return new Result<T>(null,e.getCode(),e.getMessage());
    }
}
