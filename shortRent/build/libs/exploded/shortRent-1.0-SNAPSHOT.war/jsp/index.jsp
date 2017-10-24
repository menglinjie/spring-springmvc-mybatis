<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>首页</title>
    <style type="text/css">
        #head {
            width: 100%;
            height: 60px;
        }

        #content {
            height: 440px;
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

        #search {
            float: left;
            width: 310px;
            height: 35px;
            border-color: #AAAAAA;
            border-style: solid none solid solid;
            border-width: 1px medium 1px 1px;
            font-size: 16px;
            font-weight: bold;
            line-height: 35px;
            color: #57B300;
            padding: 3px 9px 0;
        }

        #button {
            color: #FFFFFF;
            background-color: #5FBA00;
            cursor: pointer;
            float: left;
            width: 106px;
            height: 40px;
            border-color: #459A00;
            border-style: solid;
            border-width: 1px;
            font-family: 微软雅黑;
            font-size: 18px;
            font-weight: bold;
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

        h2 {
            color: #5FBA3D;
        }

        .title {
            margin-top: 97px;
            margin-left: 552px;
            font-size: 35px;
        }

        .search {
            margin-left: 465px;
            margin-top: 55px;
        }

        .link-t {
            margin-left: 105px;
        }

        table {
            float: left;
            margin-left: 292px;
        }
    </style>
</head>
<body>
<div id="head">
    <a href="index.jsp" class="chat"></a>
    <!--<span class="chat"><img src="../image/logo.jpg"></span>-->
    <ul class="link-r">
        <c:if test="${empty sessionScope.user}">
            <li><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>
            :
            <li><a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a></li>
            :
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <li><a href="">${sessionScope.user.name}</a></li>
        </c:if>
        <li><a href="${pageContext.request.contextPath}/jsp/create.jsp">免费创建房屋</a></li>
    </ul>
</div>
<div id="content">
    <h2 class="title">寻找旅途中的家</h2>
    <form action="/sr/house/getByParam?page=1" class="search" method="post">
        <input type="text" id="search" name="param"/>
        <input type="submit" id="button" value="搜索"/>
    </form>
    <br>
    <br>
    <br>
    <table border="1">
        <tr>
            <td style="width: 200px;height: 20px">图片</td>
            <td style="width: 400px;height: 20px">
                详细信息
            </td>
            <td style="width: 200px;height: 20px">
                价格(￥)
            </td>
            <td>
                操作
            </td>
        </tr>
        </tr>
        <c:set var="path" value="http://localhost:8080/pic" scope="session"/>
        <c:forEach items="${requestScope.searchHouse.list}" var="sHouse">
            <tr>
                <td style="width: 200px;height: 80px">
                    <img src="${path}/${sHouse.picture}" alt="">
                </td>
                <td style="width: 400px;height: 80px">
                        ${sHouse.name}<br>
                    <c:choose>
                        <c:when test="${sHouse.houseType eq 1}">
                            酒店
                        </c:when>
                        <c:when test="${sHouse.houseType eq 2}">
                            民宿
                        </c:when>

                        <c:when test="${sHouse.houseType eq 3}">
                            主题
                        </c:when>
                    </c:choose>
                    --
                    <c:choose>
                        <c:when test="${sHouse.rentType eq 1}">
                            整租
                        </c:when>
                        <c:when test="${sHouse.rentType eq 2}">
                            单间
                        </c:when>
                        <c:when test="${sHouse.rentType eq 3}">
                            合租
                        </c:when>
                    </c:choose>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                        ${sHouse.address}
                </td>
                <td style="width: 200px;height: 80px">
                        ${sHouse.price}￥
                </td>
                <td>
                    <a href="/sr/house/get?id=${sHouse.id}">预定</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="4" style="text-align: center">
                现在是第${requestScope.searchHouse.currentPage}页，共${requestScope.searchHouse.totalPage}页 ${requestScope.searchHouse.allRow}条记录
                <a href="/sr/house/getByParam?page=1&param=${requestScope.param}">首页</a>
                <a href="/sr/house/getByParam?page=${requestScope.searchHouse.currentPage-1}&param=${requestScope.param}">上一页</a>
                <a href="/sr/house/getByParam?page=${requestScope.searchHouse.currentPage+1}&param=${requestScope.param}">下一页</a>
                <a href="/sr/house/getByParam?page=${requestScope.searchHouse.totalPage}&param=${requestScope.param}">末页</a>
            </td>
        </tr>
    </table>
    <br>
</div>
<div>
    <ul class="link-t">
        <li><a href="${pageContext.request.contextPath}/jsp/adminLogin.jsp">管理员登录</a></li>
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