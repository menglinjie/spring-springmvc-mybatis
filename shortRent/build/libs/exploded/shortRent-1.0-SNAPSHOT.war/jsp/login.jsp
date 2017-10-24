<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>

    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script>
        $.validator.setDefaults({
            submitHandler: function () {
                alert("进行登录！");
            }
        });
        $().ready(function () {
            $("#Form").validate({
                rules: {
                    name: {
                        requied: true,
                        minlenth: 2,
                        maxlenth: 6
                    },
                    password: {
                        requied: true,
                        minlenth: 2,
                        maxlenth: 12
                    }
                },
                message: {
                    name: {
                        requied: "请输入用户名",
                        minlenth: "请输入2-6个字符",
                        maxlenth: "请输入2-6个字符"
                    },
                    password: {
                        requied: "请输入密码",
                        minlenth: "请输入2-12个字符作为密码",
                        maxlenth: "请输入2-12个字符作为密码"
                    }
                }


            })
        })


    </script>
    <style type="text/css">
        #head {
            width: 100%;
            height: 60px;
        }

        #content {
            height: 513px;
            margin-top: 22px;
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
    <ul class="link-r">
        <li><a href="register.jsp">注册</a></li>
        :
        <li><a href="login.jsp">登录</a></li>
        :
        <li><a href="create.jsp">免费创建房屋</a></li>
    </ul>
</div>
<div id="content">
    <form action="/sr/user/login" id="Form">
        <table>
            <caption><h2>登录到您的短租账户</h2></caption>
            <tr>
                <td>用户名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="text" name="name" id="name"/><br>
                </td>
            </tr>
            <tr>
                <td><br>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;<input type="password" name="pass" id="password"/>&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
            <tr>
                <%--<input type="radio" id="agree"/>我同意<a href="">《短租网服务协议》</a>及<a href="">《隐私条约》</a>--%>
            </tr>
            <tr>
                <td><br>
                    <span style="color:red;font-size: 16px">${requestScope.errlogin}</span><br>
                    <input type="submit" id="button" value="登录短租网"/>
                </td>
            </tr>
            <tr>
                <td align="center"><br>还没有短租网账户吗？<a href="register.jsp">立即注册</a></td>
            </tr>
        </table>
    </form>
</div>
<div>
    <ul class="link-t">
        <li><a href="">管理员登录</a></li>
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