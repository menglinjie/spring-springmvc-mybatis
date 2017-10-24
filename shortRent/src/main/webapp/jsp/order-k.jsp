<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>待受理订单-房客</title>
    <style type="text/css">
        #cols {
            /*background: #f8f8f8;*/
            width: 100%;
            height: 487px;
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
        <li><a href="">${sessionScope.user.name}</a></li>
        :
        <li><a href="/sr/user/loginout">退出</a></li>
        :
        <li><a href="${pageContext.request.contextPath}/jsp/create.jsp">免费创建房屋</a></li>
    </ul>
</div>
<div id="cols">
    <div id="aside">
        <d1>
            <dt>
                我是房东
            </dt>
            <dd>
                <a href="/sr/house/getByUser?status=2&page=1&creatorId=${sessionScope.user.id}">发布房屋</a>
            </dd>
            <dd>
                <a href="/sr/order/getByD?status=0&page=0&creatorId=${sessionScope.user.id}">受理订单</a>
            </dd>
            <dd>
                <a href="/sr/house/getAllByUser?page=1&creatorId=${sessionScope.user.id}">我的房屋</a>
            </dd>
            <dd>
                <a href="${pageContext.request.contextPath}/jsp/create.jsp">创建房屋</a>
            </dd>
        </d1>
        <d1>
            <dt>
                我是房客
            </dt>
            <dd>
                <a href="/sr/order/getByK?status=0&page=0&orderoId=${sessionScope.user.id}">待受理订单</a>
            </dd>
            <dd>
                <a href="/sr/order/getByKy?page=1&status=1&orderoId=${sessionScope.user.id}">已受理订单</a>
            </dd>
            <dd>
                <a href="${pageContext.request.contextPath}/jsp/index.jsp">去订房</a>
            </dd>
        </d1>
        <d1>
            <dt>
                交易记录
            </dt>
            <dd>
                <a href="/sr/order/getByDy?status=1&page=1&creatorId=${sessionScope.user.id}">成交订单</a>
            </dd>
        </d1>
    </div>
    <div id="content">
        <span style="color: #F75000;font-size: 20px">待受理订单-房客</span>
        <table border="5">
            <tr>
                <td colspan="8">
                    现在是第${orderK.currentPage}页，
                    共${orderK.totalPage}页 ，
                    ${orderK.allRow}条记录
                    <a href="/sr/house/getByK?page=1&creatorId=${sessionScope.user.id}&status=0">首页</a>
                    <a href="/sr/house/getByK?page=${requestScope.orderK.currentPage-1}&creatorId=${sessionScope.user.id}&status=0">上一页</a>
                    <a href="/sr/house/getByK?page=${requestScope.orderK.currentPage-1}&creatorId=${sessionScope.user.id}&status=0">下一页</a>
                    <a href="/sr/house/getByK?page=${requestScope.orderK.totalPage}&creatorId=${sessionScope.user.id}&status=0">末页</a>
                </td>
            </tr>
            <tr>
                <td>订单号</td>
                <td>房屋预览</td>
                <td>入住时间</td>
                <td>退房时间</td>
                <td>单价(￥)</td>
                <td>总价(￥)</td>
                <td>预定时间</td>
                <td>操作</td>
            </tr>
            <c:set var="path" value="http://localhost:8080/pic" scope="session"/>
            <c:forEach items="${orderK.list}" var="orderk">
                <tr>
                    <td class="td-n" style="width: 80px; height: 70px;">${orderk.order.number}</td>
                    <td class="td-n" style="width: 250px; height: 70px;">
                          <img src="${path}/${orderk.house.picture}">
                    </td>
                    <td class="td-n" style="width: 150px; height: 70px;">${orderk.order.inTime}</td>
                    <td class="td-n" style="width: 150px; height: 70px;">${orderk.order.outTime}</td>
                    <td class="td-n" style="width: 80px; height: 70px;">${orderk.order.onePrice}</td>
                    <td class="td-n" style="width: 80px; height: 70px;">${orderk.order.totlePrice}</td>
                    <td class="td-n" style="width: 150px; height: 70px;">${orderk.order.createTime}</td>
                    <td class="td-n" style="width: 80px; height: 70px;">
                        <a href="/sr/order/updateStatus?status=-1&id=${orderk.order.id}">取消订单</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div>
    <ul class="link-t">
        <li><a href="">登录</a></li>
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