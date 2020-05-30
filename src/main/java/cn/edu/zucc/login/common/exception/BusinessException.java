package cn.edu.zucc.login.common.exception;

import cn.edu.zucc.login.common.error.ServiceErrors;

/**
 * @author crabxyj
 * @date 2020/5/30 14:08
 */
public class BusinessException extends BaseException {

    public BusinessException(ServiceErrors errors) {
        super(errors);
    }

    public BusinessException(String msg) {
        super(2000,msg);
    }

}
