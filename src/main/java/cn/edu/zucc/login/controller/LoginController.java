package cn.edu.zucc.login.controller;

import cn.edu.zucc.login.exception.BaseException;
import cn.edu.zucc.login.pojo.BeanAccount;
import cn.edu.zucc.login.service.AccountService;
import cn.edu.zucc.login.utils.BeanResult;
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
public class LoginController {
    @Resource(name = "accountServiceImpl")
    private AccountService service;

    @PostMapping("/login")
    public BeanResult login(@RequestParam String account,@RequestParam String password){
        BeanAccount one = new BeanAccount().setAccount(account).setPassword(password);
        System.out.println(JSON.toJSONString(account));
        try {
            return BeanResult.fromSuccess(service.login(one));
        } catch (BaseException e) {
            return BeanResult.fromException(e);
        }
    }

    @PostMapping("/register")
    public BeanResult register(@RequestParam String account,@RequestParam String password){
        BeanAccount one = new BeanAccount().setAccount(account).setPassword(password);
        System.out.println(JSON.toJSONString(account));
        try {
            return BeanResult.fromSuccess(service.register(one));
        } catch (BaseException e) {
            return BeanResult.fromException(e);
        }
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String account,@RequestParam String password){

    }
}
