package cn.edu.zucc.login.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * @author crabxyj
 * @date 2020/5/10 12:49
 */
@Getter
@AllArgsConstructor
@Accessors(chain = true)
public class BeanResult {
    private int code;
    private String msg;
    private Object r;

    public static BeanResult fromException(Exception e){
        return new BeanResult(-1,e.getMessage(),null);
    }

    public static BeanResult fromSuccess(Object r){
        return new BeanResult(0,null,r);
    }

    public static BeanResult fromMsg(int code,String msg){
        return new BeanResult(code,msg,null);
    }
}
