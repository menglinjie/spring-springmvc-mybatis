<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>待审核房屋</title>
    <style type="text/css">
        #cols {
            /*background: #f8f8f8;*/
            width: 100%;
            height: 560px;
        }

        #aside {
            margin-left: -240px;
            margin-top: 50px;
            width: 168px;
            height: 100%;
            float: left;
        }

        #content {
            margin-top: 50px;
            width: 850px;
            height: 100%;
            float: left;
        }

        #aside d1 dt {
            color: #666666;
            font-size: 18px;
            font-weight: bold;
            padding-left: 20px;
        }

        #head {
            width: 100%;
            height: 60px;
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
            float: left;
            height: 27px;
        }

        td {
            width: 150px;
            height: 28px;
        }

        /*.td-n{*/
        /*width: 150px;*/
        /*height: 90px;*/
        /*}*/
    </style>
</head>
<body>
<div id="head">
    <a href="index.jsp" class="chat"></a>
    <ul class="link-r">
        <li><a href="">管理员：</a></li>
        :
        <li><a href="/sr/user/loginout">退出</a></li>
    </ul>
</div>
<div id="cols">
    <div id="aside">
        <d1>
            <dt>
                房屋管理
            </dt>
            <dd>
                <a href="">所有房屋</a>
            </dd>
            <dd>
                <a href="/sr/house/getList?page=1&status=1">审核房屋</a>
            </dd>
        </d1>
        <d1>
            <dt>
                用户管理
            </dt>
            <dd>
                <a href="/sr/user/getPagen?page=1">所有用户</a>
            </dd>
        </d1>
        <d1>
            <dt>
                查询统计
            </dt>
            <dd>
                <a href="/sr/house/getTotleCount">房屋统计</a>
            </dd>
            <dd>
                <a href="/sr/user/getCount">用户统计</a>
            </dd>
        </d1>
    </div>
    <div id="content">
        <span style="color: #F75000;font-size: 20px">用户管理</span>
        <table border="2">
            <tr>
                <td colspan="6">
                    现在是第${requestScope.pagen.currentPage}页，
                    共${requestScope.pagen.totalPage}页
                    共${requestScope.pagen.allRow}条记录
                    <a href="/sr/user/getPagen?page=1">首页</a> <a
                        href="/sr/user/getPagen?page=${requestScope.pagen.currentPage-1}">上一页</a> <a
                        href="/sr/user/getPagen?page=${requestScope.pagen.currentPage+1}">下一页</a> <a
                        href="/sr/user/getPagen?page=${requestScope.pagen.totalPage}">末页</a>
                </td>
            </tr>
            <tr>
                <td>用户名</td>
                <td>手机号</td>
                <td>密码</td>
                <td>状态</td>
                <td colspan="2">操作</td>
            </tr>
            <c:forEach items="${requestScope.pagen.list}" var="user">
                <tr>
                    <td class="td-n" style="width: 250px; height: 40px;">${user.name}</td>
                    <td class="td-n" style="width: 250px; height: 40px;">${user.phone}</td>
                    <td class="td-n" style="width: 250px; height: 40px;">${user.password}</td>
                    <td class="td-n" style="width: 250px; height: 40px;color: red">
                        <c:choose>
                            <c:when test="${user.status eq 1}">
                                未冻结
                            </c:when>
                            <c:when test="${user.status eq 0}">
                                冻结
                            </c:when>

                            <c:when test="${user.status eq -1}">
                                已删除
                            </c:when>
                        </c:choose>
                    </td>
                    <td class="td-n" style="width: 80px; height: 40px;"><a
                            href="/sr/user/updateStatus?id=${user.id}&status=0">冻结</a></td>
                    <td class="td-n" style="width: 80px; height: 40px;"><a
                            href="/sr/user/updateStatus?id=${user.id}&status=1">解冻</a></td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>
<div>
    <span style="align-content: center;margin-left: -540px;">&reg;轻松短租网</span>
</div>
</body>
