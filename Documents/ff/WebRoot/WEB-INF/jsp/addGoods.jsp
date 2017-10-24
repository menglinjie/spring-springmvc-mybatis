<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>缘来在此交友网 -- 后台管理系统</title>
		<link type="text/css" rel="stylesheet" href="../css/stylereset.css" />
		<link rel="stylesheet" href="../css/bootstrap.css" />
		<link rel="stylesheet" href="../css/style.css">
		<link rel="stylesheet" href="../css/index.css" />
		<link rel="stylesheet" href="../css/media.css" />
		<style type="text/css">
			.list-group-item a{
				width: 100%;
				height: 100%;
				display: block;
				color: #000;
				font-size: 16px;
				text-align: center;
			}
		</style>
	</head>

	<body style="overflow-x: hidden; overflow-y: auto;">

		<!--Start top-->
		<header class="top clearfix">
			<!--Start top_logo-->
			<div class="top_logo">
				<a href="#" title="缘来在此后台管理系统">缘来在此后台管理系统</a>
			</div>
			<!--End top_logo-->

			<!--Start top_main-->
			<div class="top_main clearfix">
				<!--Start top_main_nav-->
				<nav class="top_main_nav clearfix">
					<label class="top_main_nav_icon hidden-lg pull-right">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
					</label>
				</nav>
				<!--End top_main_nav-->
			</div>
			<!--End top_main-->
		</header>
		<!--End top-->
		
		<!-- Start data_outer_container -->
		<article id="app" class="data_outer_container container-fluid clearfix" style="padding: 15px; z-index: 1;">
			<!-- Start data_inner_container -->
			<article class="data_inner_container container-fluid clearfix" style="z-index: 1; background: #fff;">
				<!-- Start content_main_header -->
				<div class="text-center"style="padding-bottom: 15px">
					<h2>${ state }</h2>
				</div>
				<!-- End content_main_header -->
				<form class="text-center" method="post" action="${pageContext.request.contextPath}/manage/${url}" enctype="multipart/form-data">
					<input type="hidden" id="id" name="id" value="${ goods.id }" />
					<div class="form-group form-group-lg col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group">
							<label for="name" class="input-group-addon">商品名称</label>
							<input type="text" class="form-control" id="name" name="name" value="${goods.name}" autocomplete="off" placeholder="请输入商品名" />
						</div>
					</div>
					<div class="form-group form-group-lg col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group">
							<label for="charm" class="input-group-addon">商品魅力值</label>
							<input type="text" class="form-control" id="charm" name="charm" value="${goods.charm}" autocomplete="off" placeholder="请输入商品魅力值" />
						</div>
					</div>
					<div class="form-group form-group-lg col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group">
							<label class="input-group-addon" for="state">商品状态:</label>
							<input type="text" class="form-control" id="state" name="state" value="${ goods.state }" autocomplete="off" placeholder="请输入商品状态" />
						</div>
					</div>
					<div class="form-group form-group-lg col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<div class="input-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label for="price_rmb" class="input-group-addon">RMB价格</label>
							<input type="text" class="form-control" id="price_rmb" name="price_rmb" value="${ goods.price_rmb }" autocomplete="off" placeholder="请输入商品RMB价格" />
						</div>
					</div>
					<div class="form-group form-group-lg col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<div class="input-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label for="discount_rmb" class="input-group-addon">RMB折扣</label>
							<input type="text" class="form-control" id="discount_rmb" name="discount_rmb" value="${ goods.discount_rmb }" autocomplete="off" placeholder="请输入商品RMB折扣" />
						</div>
					</div>
					<div class="form-group form-group-lg col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<div class="input-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label for="price_point" class="input-group-addon">积分价格</label>
							<input type="text" class="form-control" id="price_point" name="price_point" value="${ goods.price_point }" autocomplete="off" placeholder="请输入商品积分价格" />
						</div>
					</div>
					<div class="form-group form-group-lg col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<div class="input-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label for="discount_point" class="input-group-addon">积分折扣</label>
							<input type="text" class="form-control" id="discount_point" name="discount_point" value="${ goods.discount_point }" autocomplete="off" placeholder="请输入商品积分折扣" />
						</div>
					</div>
					<div class="form-group form-group-lg col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group">
							<label for="message" class="input-group-addon">商品信息</label>
							<input type="text" class="form-control" id="message" name="message" value="${ goods.message }" autocomplete="off" placeholder="请输入商品信息" />
						</div>
					</div>
					
					<div class="form-group form-group-lg col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group">
							<label for="imgFile" class="input-group-addon">商品图片</label>
							<c:if test="${goods.img != null}">
								<img src="http://139.199.16.239:8080/demo/image/${goods.img}" width=100 height=100 />
							</c:if> 
							<input type="file" class="form-control" id="imgFile" name="imgFile"/>
						</div>
					</div>
					
					<div class="form-group form-group-lg col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<button onclick="history.go(-1)" class="btn btn-danger btn-lg" >放弃修改</button>
						<input type="submit" class="btn btn-success btn-lg" value="${ state }" />
					</div>
					
				</form>

			</article>
			<!-- End data_inner_container -->
		</article>
		<!-- End data_outer_container -->
	</body>

	<script type="text/javascript" src="../js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap.js"></script>
	<script type="text/javascript" src="../js/public.js"></script>

</html>