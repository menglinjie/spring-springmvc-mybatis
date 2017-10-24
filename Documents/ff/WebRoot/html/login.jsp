<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%//实现利用Cookie登录的功能 
	String username = "";
	String password = "";
	Cookie[] cookies = request.getCookies();//获取客户端Cookie信息		
	if(cookies != null){//假设客户端保存有用户的Cookie信息		
		for (Cookie c : cookies) {				
			if("username".equals(c.getName())){
				username = c.getValue();
			}
			if("password".equals(c.getName())){
				password = c.getValue();
			}
		}			
	}
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>登录 -- 缘来再此交友网</title>
		<link type="text/css" rel="stylesheet" href="../css/stylereset.css" />
		<link rel="stylesheet" href="../css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="../css/style.css">
		<style type="text/css">
			/*覆盖bootstrap原始样式*/		
			.form-control-feedback {
				top: 6px;
				right: 15px;
			}
		</style>
	</head>

	<body>
		<!-- Start container-fluid(登录页面主体) -->
		<div class="container-fluid login">
			<!-- Start (遮罩层) -->
			<div class="mask"></div>
			<!-- End (遮罩层) -->

			<!-- Start login_box(登录盒子) -->
			<div class="center login_box col-xs-12 col-sm-12 col-md-5 col-lg-4">

				<!-- Start login_form(登录表单) -->
				<form class="login_form clearfix thumbnail" id="login_form" action="${pageContext.request.contextPath}/user/isValid.action" method="post">
					<h1 class="login_logo">
						<span>用户登录</span>
					</h1>
					<div class="form-group form-group-lg has-feedback col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group">
							<label for="username" class="input-group-addon"><span class="glyphicon glyphicon-user"></span></label>
							<input type="text" class="form-control" id="username" value="<%= username%>" name="username" autocomplete="off" placeholder="请输入手机号" />
						</div>
						<span class="glyphicon form-control-feedback"></span>
					</div>

					<div class="form-group form-group-lg has-feedback col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label for="password" class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></label>
							<input type="password" class="form-control" id="password" value="<%= password%>" name="password" autocomplete="off" placeholder="请输入密码" />
						</div>
						<span class="glyphicon form-control-feedback"></span>
					</div>

					<div class="form-group form-group-lg has-feedback clearfix col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group col-xs-7 col-sm-7 col-md-7 col-lg-7 pull-left">
							<label for="cycle" class="input-group-addon"><span class="glyphicon glyphicon-credit-card"></span></label>
							<input type="text" class="form-control" id="cycle" name="cycle" autocomplete="off" placeholder="请输入验证码" />
						</div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 pull-right thumbnail cycle_image">
							<div title="点击更换验证码" id="cycle_img"><img alt="点击更换验证码" title="点击更换验证码" /></div>
						</div>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<label class="checkbox-inline"><input type="checkbox" name="remb_password" id="remb_password" />记住密码</label>
						<a href="foundPassword1.jsp" class="pull-right">忘记密码?</a>
					</div>

					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<button type="button" class="btn btn-info btn-lg col-xs-12 col-sm-12 col-md-12 col-lg-12" id="login_form_submit">登录</button>
						<a href="../index.html" class="text-muted pull-left">&laquo;返回首页</a>
						<a href="register.jsp" class="text-muted pull-right">没有帐号?前往注册&raquo;</a>
					</div>
				</form>
				<!-- End login_form(登录表单) -->
			</div>
			<!-- End login_box(登录盒子) -->
		</div>
		<!-- End container-fluid(登录页面) -->
	</body>
	<script type="text/javascript" src="../js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap.js"></script>
	<script type="text/javascript" src="../js/login.js"></script>
	<script type="text/javascript">	
		//文档准备加载
		$(document).ready(function(){
			$.ajaxSetup ({
				cache: false //关闭AJAX相应的缓存
			});
		
			//加载时请求
			loadCycleImg();
			
			//注册点击事件
			$("#cycle_img").click(loadCycleImg);
			
			//请求验证图片加载函数
			function loadCycleImg(){				
				$.ajax({
					type: "POST", //设置请求方式
					url: "${pageContext.request.contextPath}/user/cycleImg.action", //设置请求路径
					success: function(data){
						//直接进行验证码图片替换
						$("#cycle_img > img").attr("src", "data:image/png;base64," + data);
					}, //设置请求成功事件
					dataType: "json" 
					//设置服务器返回数据类型，由于使用Base64编码，所以设置返回值类型为"text"
				});
			};
		});
	</script>

</html>