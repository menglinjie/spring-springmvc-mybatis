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
		<meta http-equiv="refresh" content="3;url=login.jsp"> 
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>中转处理页面 -- 缘来再此交友网</title>
		<link type="text/css" rel="stylesheet" href="../css/stylereset.css" />
		<link rel="stylesheet" href="../css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="../css/style.css">
	</head>

	<body>
	  	<h1>系统提示:</h1>
	  	<p style="color:red">该用户session已失效，正在跳转登录页面，请重新登录...</p>
	</body>
	
</html>