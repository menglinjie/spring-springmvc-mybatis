package com.friendfinder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tools.sendsms;
/**
 * Created by 12191 on 2017/5/13/ 0013.
 */
@RestController
@RequestMapping("/tools")
public class ToolsController {


    @RequestMapping(value = "/sms.action",method = RequestMethod.GET)
    public String send(String phone){
        return  sendsms.send(phone);
    }
}
