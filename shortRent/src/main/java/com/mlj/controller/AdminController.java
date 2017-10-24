package com.mlj.controller;

import com.mlj.modle.Admin;
import com.mlj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/get")
    public ModelAndView get(@RequestParam String account,
                            @RequestParam String pass,
                            HttpServletRequest request) {
        Admin admin = adminService.get(account, pass);
        if (admin!=null){
            request.getSession().setAttribute("admin",admin);
            return new ModelAndView("redirect:/house/getList?page=1&status=1", "admin", admin);
        }
        return new ModelAndView("adminLogin.jsp");
    }
}
