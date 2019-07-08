package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.service.DubboService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Reference
    private DubboService dubboServiceImpl;


    @GetMapping("/login")
    public @ResponseBody
    String login(@RequestParam(value = "username") String username,
                 @RequestParam(value = "password") String password) {

        if (dubboServiceImpl.login(username, password)) {
            return username + " login success";
        } else {
            return username + " login fail";
        }

    }
}
