<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>注册 -- 缘来再此交友网</title>
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
		<!-- Start login(注册页面主体) -->
		<div class="container-fluid login">
			<!-- Start mask(遮罩层) -->
			<div class="mask"></div>
			<!-- End mask(遮罩层) -->

			<!-- Start login_box(登录盒子) -->
			<div class="center login_box col-xs-12 col-sm-12 col-md-5 col-lg-4">
				<!-- Start login_form -->
				<form class="login_form clearfix thumbnail" id="register_form" action="${pageContext.request.contextPath}/user/addUser.action" method="post">
					<h1 class="login_logo">
						<span>用户信息注册</span>
					</h1>
					<div class="form-group form-group-lg has-feedback col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label for="phone" class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></label>
							<input type="text" class="form-control" id="phone" name="phone" autocomplete="off" placeholder="请输入手机号" />
						</div>
						<span class="glyphicon form-control-feedback"></span>
					</div>

					<div class="form-group form-group-lg has-feedback clearfix col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group col-xs-7 col-sm-7 col-md-7 col-lg-7 pull-left">
							<label for="cycle" class="input-group-addon"><span class="glyphicon glyphicon-picture"></span></label>
							<input type="text" class="form-control" id="cycle" name="cycle" autocomplete="off" placeholder="请输入验证码" />
						</div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 pull-right thumbnail cycle_image">
							<div title="点击更换验证码" id="cycle_img"><img alt="点击更换验证码" title="点击更换验证码" /></div>
						</div>
					</div>

					<div class="form-group form-group-lg has-feedback col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label for="password" class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></label>
							<input type="password" class="form-control" id="password" name="password" autocomplete="off" placeholder="请输入密码" />
						</div>
						<span class="glyphicon form-control-feedback"></span>
					</div>

					<div class="form-group form-group-lg has-feedback col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label for="password_again" class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></label>
							<input type="password" class="form-control" id="password_again" name="password_again" autocomplete="off" placeholder="请再次输入密码" />
						</div>
						<span class="glyphicon form-control-feedback"></span>
					</div>

					<div class="form-group form-group-lg has-feedback clearfix col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group col-xs-6 col-sm-6 col-md-6 col-lg-6 pull-left">
							<label for="sms_cycle" class="input-group-addon"><span class="glyphicon glyphicon-credit-card"></span></label>
							<input type="text" class="form-control" id="sms_cycle" name="sms_cycle" autocomplete="off" placeholder="请输入短信验证码" />
						</div>
						<button title="发送验证码" type="button" id="sms_btn" class="btn btn-primary btn-lg col-xs-5 col-sm-5 col-md-5 col-lg-5 pull-right text_overflow">发送验证码</button>
					</div>

					<div class="form-group form-group-lg col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<label class="checkbox-inline"><input type="checkbox" name="reg" id="reg" checked />我已阅读<a href="#">《注册条例》</a></label>
					</div>

					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<button type="button" class="btn btn-info btn-lg col-xs-12 col-sm-12 col-md-12 col-lg-12" id="register_form_submit">注册</button>
						<a href="login.jsp" class="text-muted pull-left">&laquo;返回登录页面</a>
						<a href="../index.html" class="text-muted pull-right">返回首页&raquo;</a>
					</div>
				</form>
				<!-- End login_form -->
			</div>
			<!-- End login_box(登录盒子) -->
		</div>
		<!-- End login(注册页面主体) -->
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