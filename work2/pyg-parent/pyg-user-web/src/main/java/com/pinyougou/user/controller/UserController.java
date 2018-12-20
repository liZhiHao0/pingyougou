package com.pinyougou.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.user.service.UserService;

import entity.PageResult;
import entity.Result;
import util.PhoneFormatCheckUtils;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/sendCode")
    public Result sendCode(String phone) {

        if (!PhoneFormatCheckUtils.isPhoneLegal(phone)) {
            return new Result(false, "手机格式不正确");
        }
        try {
            userService.createSmsCode(phone);
            return new Result(true, "验证码发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "验证码发送失败");
        }
    }
    /**
     * 增加
     *
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbUser user, String smscode) {
        //校验验证码是否正确
        boolean checkSmsCode = userService.checkSmsCode(user.getPhone(), smscode);
        if (!checkSmsCode) {
            return new Result(false, "验证码不正确！");
        }
        try {
            userService.add(user);
            return new Result(true, "注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

}
