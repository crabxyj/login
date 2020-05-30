package cn.edu.zucc.login.service;

import cn.edu.zucc.login.common.exception.BaseException;
import cn.edu.zucc.login.common.exception.BusinessException;
import cn.edu.zucc.login.pojo.BeanAccount;

/**
 * @author crabxyj
 * @date 2020/5/10 12:22
 */
public interface AccountService {
//    BeanAccount login(String account,String password);

    BeanAccount login(BeanAccount account) throws BusinessException;

    void logout(BeanAccount account);

    BeanAccount register(BeanAccount account) throws BusinessException;
}
