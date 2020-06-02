package cn.edu.zucc.login.controller;

import cn.edu.zucc.login.common.exception.BusinessException;
import cn.edu.zucc.login.pojo.BeanAccount;
import cn.edu.zucc.login.service.AccountService;
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
@RequestMapping("/thirdlogin")
public class ThirdLoginController {
    @Resource(name = "accountServiceImpl")
    private AccountService service;

    private static Map<String,BeanAccount> ticketsMap = new ConcurrentHashMap<>();
    private static Map<Integer,String> idTicketMap = new ConcurrentHashMap<>();

    @RequestMapping("/login")
    public String login(@RequestParam String account, @RequestParam String password) throws BusinessException {
        BeanAccount one = new BeanAccount().setAccount(account).setPassword(password);
        one = service.login(one);

        String ticket = idTicketMap.get(one.getId());
        if (ticket==null){
            ticket = UUID.randomUUID().toString();
            ticketsMap.put(ticket,one);
            idTicketMap.put(one.getId(),ticket);
        }
        return ticket;
    }

    @PostMapping("/register")
    public String register(@RequestParam String account,@RequestParam String password) throws BusinessException {
        BeanAccount one = new BeanAccount().setAccount(account).setPassword(password);
        one = service.register(one);
        String ticket = UUID.randomUUID().toString();
        ticketsMap.put(ticket,one);
        return ticket;
    }

    @RequestMapping("/logout")
    public void logout(String token, String ticket){
        ticketsMap.remove(ticket);
    }

    @RequestMapping("/ticketLogin")
    public BeanAccount ticketLogin(String token, String ticket){
        return ticketsMap.get(ticket);
    }

}
