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
    public @ResponseBody String login(@RequestParam(value = "name")String name,
                             @RequestParam(value = "password")String password) throws Exception {

        if(dubboServiceImpl.login(name,password)){
            return "success";
        }else{
            return "fail";
        }

    }
}
