package cn.edu.zucc.login.controller;

import cn.edu.zucc.login.common.exception.BaseException;
import cn.edu.zucc.login.common.exception.BusinessException;
import cn.edu.zucc.login.common.result.ResultWrap;
import cn.edu.zucc.login.pojo.BeanAccount;
import cn.edu.zucc.login.service.AccountService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author crabxyj
 * @date 2020/5/10 12:05
 */
@RestController
@CrossOrigin
@RequestMapping("/account")
@ResultWrap
public class LoginController {
    @Resource(name = "accountServiceImpl")
    private AccountService service;

    @PostMapping("/login")
    public BeanAccount login(@RequestParam String account, @RequestParam String password) throws BaseException {
        BeanAccount one = new BeanAccount().setAccount(account).setPassword(password);
        return service.login(one);
    }

    @PostMapping("/register")
    public BeanAccount register(@RequestParam String account,@RequestParam String password) throws BusinessException {
        BeanAccount one = new BeanAccount().setAccount(account).setPassword(password);
        System.out.println(JSON.toJSONString(account));
        return service.register(one);
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String account,@RequestParam String password){

    }
}
