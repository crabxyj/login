package cn.edu.zucc.login.service.impl;

import cn.edu.zucc.login.dao.AccountDao;
import cn.edu.zucc.login.exception.BaseException;
import cn.edu.zucc.login.pojo.BeanAccount;
import cn.edu.zucc.login.service.AccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author crabxyj
 * @date 2020/5/10 12:27
 */
@Service("accountServiceImpl")
public class AccountServiceImpl extends ServiceImpl<AccountDao, BeanAccount> implements AccountService {
    @Resource(name = "accountDao")
    private AccountDao dao;

    private Optional<BeanAccount> getByAccount(BeanAccount account) {
        QueryWrapper<BeanAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("account",account.getAccount());
        return Optional.ofNullable(dao.selectOne(wrapper));
    }

    @Override
    public BeanAccount login(@Valid BeanAccount account) throws BaseException{
        BeanAccount one = getByAccount(account)
                .orElseThrow(()->new BaseException("账号或密码错误"));

        if (!one.getPassword().equals(account.getPassword())){
            throw new BaseException("账号或密码错误");
        }
        return one;
    }

    @Override
    public void logout(BeanAccount account) {

    }

    @Override
    public BeanAccount register(@Valid BeanAccount account) throws BaseException {
        if (getByAccount(account).isPresent()){
            throw new BaseException("当前账号已存在");
        }
        dao.insert(account);
        return account;
    }
}
