package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/um")
public class URLMappingController {
    //    @GetMapping("/g")
    @RequestMapping(value = "/g", method = RequestMethod.GET) // 作用在方法上，不再区分get/post请求
    @ResponseBody
    public String getMapping(@RequestParam("manager_name") String managerName) {
        System.out.println("managerName:" + managerName);
        return "This is get method";
    }

    @PostMapping("/p")
    @ResponseBody
    public String postMapping(String username, String password) {
        System.out.println(username + ":" + password);
        return "This is post method";
    }

    @PostMapping("/p1")
    @ResponseBody
    public String postMapping1(User user) {
        System.out.println(user.getUsername() + ":" + user.getPassword());
        return "This is post method";
    }
}
