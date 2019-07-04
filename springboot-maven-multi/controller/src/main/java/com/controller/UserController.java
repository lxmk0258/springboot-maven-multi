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
    public @ResponseBody String login(@RequestParam(value = "id")int id,
                             @RequestParam(value = "password")String password) {

        if(dubboServiceImpl.login(id,password)){
            return "success";
        }else{
            return "fail";
        }

    }
}
