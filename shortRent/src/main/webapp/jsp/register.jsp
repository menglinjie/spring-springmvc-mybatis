<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script>
        // 手机号码验证
        jQuery.validator.addMethod("isMobile", function(value, element) {
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请正确填写您的手机号码");

        $.validator.setDefaults({
            submitHandler: function () {
                alert("进行注册！");
            }
        });
        $().ready(function () {
            $("#commentForm").validate({
                rules:{
                    name:{
                        requied:true,
                        minlenth:2,
                        maxlenth:6
                    },
                    password:{
                        requied:true,
                        minlenth:2,
                        maxlenth:12
                    },
                    confirm_password: {
                        required: true,
                        minlenth:2,
                        maxlenth:12,
                        equalTo: "#password"
                    },
                    phone:{
                        required : true,
                        minlength : 11,
                        // 自定义方法：校验手机号在数据库中是否存在
                        // checkPhoneExist : true,
                        isMobile : true

                    },
                    agree:{
                        required : true
                    }
                },
                message:{
                    name:{
                        minlenth:"请输入2-6个字符",
                        maxlenth:"请输入2-6个字符"
                    },
                    password:{
                        minlenth:"请输入2-12个字符作为密码",
                        maxlenth:"请输入2-12个字符作为密码"
                    },
                    confirm_password:{
                        minlenth:"请输入2-12个字符作为密码",
                        maxlenth:"请输入2-12个字符作为密码",
                        equalTo:"两次密码不一致！"
                    },
                    phone:{
                        phone : {
                            required : "请输入手机号",
                            minlength : "确认手机不能小于11个字符",
                            isMobile : "请正确填写您的手机号码"
                        }
                    },
                    agree:{
                        required : "请同意短租网服务协议！"
                    }
                }


            })
        })


    </script>
    <style>
        .error {
            color: red;
        }
    </style>
    <style type="text/css">
        #head {
            width: 100%;
            height: 60px;
        }

        #content {
            height: 535px;
        }

        .chat {
            float: left;
            width: 322px;
            height: 83px;
            margin-left: 5px;
            background: url("../image/logo.png");
        }

        ul li {
            display: inline;
        }

        .link-r {
            float: right;
            margin-right: 45px;
        }

        a {
            color: #F75000;
            text-decoration: none;
            font-size: 18px;
        }

        .link-t {
            margin-left: 105px;
        }

        h2 {
            color: #F75000;
        }

        table {
            border-collapse: collapse;
            table-layout: fixed;
        }

        td {
            text-align: left;
        }

        #button {
            color: #FFFFFF;
            background-color: #5FBA00;
            cursor: pointer;
            float: left;
            width: 300px;
            height: 40px;
            border-color: #459A00;
            border-style: solid;
            border-width: 1px;
            font-family: 微软雅黑;
            font-size: 18px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div id="head">
    <a href="index.jsp" class="chat"></a>
    <!--<span class="chat"><img src="../image/logo.jpg"></span>-->
    <ul class="link-r">
        <li><a href="register.jsp">注册</a></li>
        :
        <li><a href="login.jsp">登录</a></li>
        :
        <li><a href="create.jsp">免费创建房屋</a></li>
    </ul>
</div>
<div id="content">
    <form action="/sr/user/rejister" id="commentForm">
        <table>
            <caption><h2>注册新账号</h2></caption>
            <tr>
                <td>用户名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="text" name="name" id="name" required minlength="2"/>&nbsp;&nbsp;&nbsp;&nbsp;<span
                            style="color:red;">*必填</span>
                </td>
            </tr>
            <tr>
                <%--<td><h5>至少需要输入两个字符</h5></td>--%>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;<input type="password" name="pass" id="password" class="password" minlength="2" maxlength="12" required/>&nbsp;&nbsp;&nbsp;&nbsp;<span
                            style="color:red;">*必填</span>
                </td>
            </tr>
            <tr>
                <%--<td><h5>2-12个字符</h5></td>--%>
            </tr>
            <tr>
                <td>确认密码：&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" id="confirm_password" name="confirm_password" required/>&nbsp;&nbsp;&nbsp;&nbsp;<span
                        style="color:red;">*必填</span></td>
            </tr>
            <tr>
                <%--<td><h5>重复输入一次密码</h5></td>--%>
            </tr>
            <tr>
                <td>手机号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="phone" id="phone"/>&nbsp;&nbsp;&nbsp;&nbsp;<span
                        style="color:red;">*必填</span></td>
            </tr>
            <tr>
                <%--<td><h5>请输入你的手机号</h5></td>--%>
            </tr>
            <tr>
                <td>
                    <input type="radio" id="agree"/>我同意<a href="">《短租网服务协议》</a>及<a href="">《隐私条约》</a>
                </td>
            </tr>
            <tr>
                <td><br>
                    <input type="submit" id="button" value="立即注册"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div>
    <ul class="link-t">
        <li><a href="adminLogin.jsp">管理员登录</a></li>
        :
        <li><a href="">关于轻松短租网</a></li>
        :
        <li><a href="">友情链接</a></li>
        :
        <li><a href="">全部城市</a></li>
        :
        <li><a href="">精彩专题</a></li>
        :
        <li><a href="">《短租网服务专题》</a></li>
        :
        <li><a href="">《隐私条款》</a></li>
        :
        <li><a href="">《房客条约》</a></li>
        :
        <li><a href="">《房东条约》</a></li>
        :
        <li><a href="">帮助</a></li>
    </ul>
</div>
</body>
</html>
