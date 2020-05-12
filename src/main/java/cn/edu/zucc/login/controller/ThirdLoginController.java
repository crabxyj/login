package cn.edu.zucc.login.controller;

import cn.edu.zucc.login.exception.BaseException;
import cn.edu.zucc.login.pojo.BeanAccount;
import cn.edu.zucc.login.service.AccountService;
import cn.edu.zucc.login.utils.BeanResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author crabxyj
 * @date 2020/5/12 16:11
 */
@RestController
@CrossOrigin
@RequestMapping("/thirdlogin")
public class ThirdLoginController {
    @Resource(name = "accountServiceImpl")
    private AccountService service;

    private static Map<String,BeanAccount> ticketsMap = new ConcurrentHashMap<>();

    @RequestMapping("/login")
    public BeanResult login(@RequestParam String account, @RequestParam String password){
        BeanAccount one = new BeanAccount().setAccount(account).setPassword(password);
        try {
            String ticket = UUID.randomUUID().toString();
            one = service.login(one);
            ticketsMap.put(ticket,one);
            return BeanResult.fromSuccess(ticket);
        } catch (BaseException e) {
            return BeanResult.fromException(e);
        }
    }

    @PostMapping("/register")
    public BeanResult register(@RequestParam String account,@RequestParam String password){
        BeanAccount one = new BeanAccount().setAccount(account).setPassword(password);
        try {
            one = service.register(one);
            String ticket = UUID.randomUUID().toString();
            ticketsMap.put(ticket,one);
            return BeanResult.fromSuccess(ticket);
        } catch (BaseException e) {
            return BeanResult.fromException(e);
        }
    }

    @RequestMapping("/logout")
    public BeanResult logout(String token, String ticket){
        ticketsMap.remove(ticket);
        return BeanResult.fromSuccess(null);
    }

    @RequestMapping("/ticketLogin")
    public BeanResult ticketLogin(String token, String ticket){
        BeanAccount one = ticketsMap.get(ticket);
        if (one!=null){
            return BeanResult.fromSuccess(one);
        }
        return BeanResult.fromMsg(-1,"当前账号未登录");
    }

}
