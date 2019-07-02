package com.controller;

import com.zk.BaseZookeeper;
import org.springframework.beans.factory.annotation.Autowired;
import com.zk.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private BaseZookeeper zookeeper;

    @GetMapping("/zk")
    public void list() throws Exception {
        zookeeper.connectZookeeper("192.168.1.37:2181");
        List<String> children = zookeeper.getChildren("/dbwolf_dev_qa");
        System.out.println(children);
        for(String c:children)
        {
            System.out.println(c+":");
            String value = zookeeper.getData("/dbwolf_dev_qa/"+c);
            System.out.println(value+":");
        }
        Test.say("zhangsan");
    }
}
