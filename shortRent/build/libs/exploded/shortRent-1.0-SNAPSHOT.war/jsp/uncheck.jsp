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

        td{
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
                <a href="/sr/house/getListNOStatus?page=1">所有房屋</a>
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
        <span style="color: #F75000;font-size: 20px">待审核房屋</span>
        <table border="5">
            <tr>
                <td colspan="10">
                    现在是第${requestScope.houses.currentPage}页，共${requestScope.houses.totalPage}页 ${requestScope.houses.allRow}条记录
                    <a href="/sr/house/getList?page=1&status=1">首页</a>
                    <a href="/sr/house/getList?page=${requestScope.houses.currentPage-1}&status=1">上一页</a>
                    <a href="/sr/house/getList?page=${requestScope.houses.currentPage+1}&status=1">下一页</a>
                    <a href="/sr/house/getList?page=${requestScope.houses.totalPage}&status=1">末页</a>
                </td>
            </tr>
            <tr>
                <td>房屋名称</td>
                <td>房屋缩略图</td>
                <td>房东</td>
                <td>房屋类型</td>
                <td>出租类型</td>
                <td>面积(m)</td>
                <td>价格</td>
                <td>创建时间</td>
                <td colspan="2">操作</td>
            </tr>
            <c:set var="path" value="http://localhost:8080/pic" scope="session"/>
            <c:forEach items="${requestScope.houses.list}" var="house">
                <tr>
                    <td class="td-n" style="width: 250px; height: 70px;">${house.name}</td>
                    <td class="td-n" style="width: 250px; height: 70px;">
                           <img src="${path}/${house.picture}">
                    </td>
                    <td class="td-n" style="width: 80px; height: 70px;">${house.creator.name}</td>
                    <td class="td-n" style="width: 80px; height: 70px;">
                        <c:choose>
                            <c:when test="${house.houseType eq 1}">
                                酒店
                            </c:when>
                            <c:when test="${house.houseType eq 2}">
                                民宿
                            </c:when>

                            <c:when test="${house.houseType eq 3}">
                                主题
                            </c:when>
                        </c:choose>
                    </td>
                    <td class="td-n" style="width: 80px; height: 70px;">
                        <c:choose>
                            <c:when test="${house.rentType eq 1}">
                                整租
                            </c:when>
                            <c:when test="${house.rentType eq 2}">
                                单间
                            </c:when>
                            <c:when test="${house.rentType eq 3}">
                                合租
                            </c:when>
                        </c:choose>
                    </td>
                    <td class="td-n" style="width: 80px; height: 70px;">${house.area}</td>
                    <td class="td-n" style="width: 80px; height: 70px;">${house.price}</td>
                    <td class="td-n" style="width: 150px; height: 70px;">${house.createTime}</td>
                    <td class="td-n" style="width: 80px; height: 70px;"><a href="/sr/house/updateStatus?id=${house.id}&status=2">通过</a> </td>
                    <td class="td-n" style="width: 80px; height: 70px;"><a href="/sr/house/updateStatus?id=${house.id}&status=0">拒绝</a> </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div>
    <span style="align-content: center;margin-left: -540px;">&reg;轻松短租网</span>
</div>
</body>
</html>