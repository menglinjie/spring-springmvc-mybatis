package com.mlj.controller;

import com.mlj.modle.User;
import com.mlj.service.UserService;
import com.mlj.util.Md5Util;
import com.mlj.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询用户
     *
     * @param page
     * @return
     */
    @RequestMapping("/getPagen")
    public ModelAndView getPagen(@RequestParam int page) {
        int pageSize = 5;
        PageBean pageBean = userService.getPagen(pageSize, page);
        ModelAndView modelAndView = new ModelAndView("user", "pagen", pageBean);
        return modelAndView;
    }

    /**
     * 查询所有用户数量
     *
     * @return
     */
    @RequestMapping("/getCount")
    public ModelAndView getCount() {
        int[] counts = userService.getCount();
        ModelAndView modelAndView = new ModelAndView("totleUser", "counts", counts);
        return modelAndView;
    }


    /**
     * 用户登录
     * 加密
     *
     * @param name
     * @param pass
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam String name,
                              @RequestParam String pass,
                              HttpServletRequest request) {
        Boolean b = true;
        User user = userService.getPass(name);//获取数据库加密后的用户
        try {
            b = Md5Util.validPasswd(pass, user.getPassword());//对比密码
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (b) {//密码与数据库密码相同
            request.getSession().setAttribute("user", user);//登陆成功，将用户放进session
            ModelAndView modelAndView = new ModelAndView("list", "successlogin", "恭喜你登录成功");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("login", "errlogin", "用户名或密码错误");
            return modelAndView;
        }
    }

    @RequestMapping("/rejister")
    public ModelAndView rejister(@RequestParam String name,
                                 @RequestParam String pass,
                                 @RequestParam String phone) {
        try {
            pass = Md5Util.getEncryptedPwd(pass);//密码进行MD5加盐加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setName(name);
        user.setPassword(pass);
        user.setPhone(phone);
        userService.saveOrUpdate(user);
        ModelAndView modelAndView = new ModelAndView("login", "successregister", "注册成功，请进行登录");
        return modelAndView;
    }

    @RequestMapping("/loginout")
    public ModelAndView loginout(HttpServletRequest request) {
        request.getSession().invalidate();//清空session
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    /**
     * 查询单个用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/get")
    public ModelAndView get(@RequestParam String id) {
        User user = userService.get(id);
        ModelAndView modelAndView = new ModelAndView("", "user", user);
        return modelAndView;
    }

    /**
     * 冻结/解冻/删除用户
     *
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatus")
    public ModelAndView updateStatus(@RequestParam String id,
                                     @RequestParam int status) {
        userService.updateStatus(id, status);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/getPagen?page=1");
        return modelAndView;
    }
}
