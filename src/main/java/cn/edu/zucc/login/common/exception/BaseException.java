package cn.edu.zucc.login.common.exception;

import cn.edu.zucc.login.common.error.ServiceErrors;

/**
 * @author crabxyj
 * @date 2020/5/30 14:37
 */
public class BaseException extends Exception{
    private Integer code;

    public BaseException(ServiceErrors errors) {
        super(errors.getMsg());
        code = errors.getCode();
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
