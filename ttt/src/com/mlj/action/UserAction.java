package com.mlj.action;

import com.mlj.modle.User;
import com.mlj.service.UserService;
import com.mlj.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAction extends ActionSupport {

    private String myemail;//对象形式接收数据
    private String pass;
    private String check;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMyemail() {
        return myemail;
    }

    public void setMyemail(String myemail) {
        this.myemail = myemail;
    }

    private UserService userService = new UserServiceImpl();

    public void show() {

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");

        boolean b = userService.show(myemail);
        System.out.println(myemail);
        if (b == true) {
            check = "";
            try {
                response.getWriter().print("");
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("可以注册");
        } else {
            check = "该邮箱已存在";
            try {
                response.getWriter().print(check);
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("该邮箱已存在");
        }
    }

    public String add() {

        User user = new User();
        user.setEmail(myemail);
        user.setPassword(pass);
        userService.add(user);
        return "success";
    }

}
