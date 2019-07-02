package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.service.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Reference
    private DubboService dfaultDubboServiceImpl;


    @GetMapping("/login")
    public @ResponseBody String login(@RequestParam(value = "name")String name,
                             @RequestParam(value = "password")String password) throws Exception {

        if(dfaultDubboServiceImpl.login(name,password)){
            return "success";
        }else{
            return "fail";
        }

    }
}
