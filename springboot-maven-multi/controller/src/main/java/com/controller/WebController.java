package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.service.DubboService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author liuxin
 */
@Controller
public class WebController {

    @Reference
    private DubboService dubboServiceImpl;

    @RequestMapping("/hello")
    public String hello(){
        return "login";
    }
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password) {
        ModelAndView model ;
        String name = dubboServiceImpl.login(username, password);
        if(name != null){
            model = new ModelAndView("success");
            model.addObject("name",name);
        }else{
            model = new ModelAndView("login");
            model.addObject("status",0);
            model.addObject("username",username);
        }
        return model;
    }
}
