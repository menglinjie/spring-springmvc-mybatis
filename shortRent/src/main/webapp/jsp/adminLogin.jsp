<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员登录</title>
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
        <li><a href="${pageContext.request.contextPath}/jsp/adminLogin.jsp">管理员登录</a></li>
    </ul>
</div>
<div id="content">
    <form action="/sr/admin/get">
        <table>
            <caption><h2>登录到管理界面</h2></caption>
            <tr>
                <td>用户名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="text" name="account"/><br>
                </td>
            </tr>
            <tr>
                <td><br>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;<input type="password" name="pass"/>&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
            <tr>
                <td><a href=""><br>忘记密码？</a></td>
            </tr>
            <tr>
                <td><br>
                    <span style="color:red;font-size: 16px">${requestScope.errlogin}</span><br>
                    <input type="submit" id="button" value="登录管理界面"/>
                </td>
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