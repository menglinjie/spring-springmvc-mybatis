<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试上传</title>
</head>
<body>
<form action="/sr/house/up" method="post" enctype="multipart/form-data">
    <input type="file" name="picture"/>
    <input type="submit" value="提交"/>
    <img src="http://localhost:8080/pic/538031d8c9c1794a155d07b5847dc9b6.jpg">z

</form>
</body>
</html>
