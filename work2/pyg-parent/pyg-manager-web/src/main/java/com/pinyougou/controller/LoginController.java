package com.pinyougou.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/username")
    public Map<String,Object> username(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        HashMap<String, Object> map = new HashMap<>();
        map.put("loginName",name);
        return map;
    }
}
