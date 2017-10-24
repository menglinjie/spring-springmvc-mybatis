<%--
  Created by IntelliJ IDEA.
  User: mlj
  Date: 17-10-10
  Time: 下午11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>注册</title>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" language="JavaScript">


        $(function () {//加载完页面加载js


            var x = 122;
            var y = 120;
            $("#img").mouseover(function (e) {  //绑定一个鼠标悬停时事件

                //新建一个p标签来存放大图片，this.rel就是获取到a标签的大图片的路径，然后追加到body中
                var $target = $(event.target);
                if ($target.is('img')) {
                    $("<img id='tip' src='" + $target.attr("src") + "'>").css({
                        "height": 100,
                        "width": 200,
                    }).appendTo($("#body"));
                    //改变小图片的透明度为0.5，结合上面的CSS，看起来就象是图片变暗了
                    $(this).find('img').stop().fadeTo('slow', 0.5);
                    console.log({top: (e.pageY - y ) + 'px', left: (e.pageX + x ) + 'px'});
                    //将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，然后以fadeIn的效果显示
                    $("#tdp").css({top: (e.pageY - y ) + 'px', left: (e.pageX + x ) + 'px'}).fadeIn('fast');
                }
            }).mouseout(function () {
                console.log("鼠标移除")
                $("#tip").remove();
                /*移除元素*/
                //将变暗的图片复原
                $(this).find('img').stop().fadeTo('slow', 1);
            })


            $("#img").mousemove(function (e) {  //绑定一个鼠标移动的事件
                console.log("鼠标移动")
                //将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，这样大图片就能跟随图片而移动了
                $("#tdp").css({top: (e.pageY - y ) + 'px', left: (e.pageX + x ) + 'px'});
            });

            //        var x = 22;
            //            var y = 20;
            //            var size = 3.0 * $('#img').width();
            //            console.log(size);
            //            $("#img").mouseover(function (event) {
            //
            //                var $target = $(event.target);
            //                if ($target.is('img')) {
            //                    $("<img id='tip' src='" + $target.attr("src") + "'>").css({
            //                        "height": 100,
            //                        "width": 300,
            //                    }).appendTo($("#body"));
            //                    /*将当前所有匹配元素追加到指定元素内部的末尾位置。*/
            //
            //                    //改变小图片的透明度为0.5，结合上面的CSS，看起来就象是图片变暗了
            //                    $(this).find('img').stop().fadeTo('slow', 0.5);
            //                    //将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，然后以fadeIn的效果显示
            //                    //         $("#bigimage").css({top: (e.pageY - y ) + 'px', left: (e.pageX + x ) + 'px'}).fadeIn('fast');
            //
            //                }
            //            }).mouseout(function () {
            //                $("#tip").remove();
            //                /*移除元素*/
            //                //将变暗的图片复原
            //                $(this).find('img').stop().fadeTo('slow', 1);
            //            })
            //            $("#img").mousemove(function (e) {  //绑定一个鼠标移动的事件
            //                //将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，这样大图片就能跟随图片而移动了
            //                $("#tip").css({top: (e.pageY - y ) + 'px', left: (e.pageX + x ) + 'px'});
            //            });

            //            加载城市列表
            $.ajax({
                type: "post",
                url: 'CityAction!showF.action',
                datatype: 'json',
                success: function (data) {
                    // alert(data + "======"+data[0].name);
                    //console.log(data);


                    $.each($.parseJSON(data), function (i, list) {
                        //console.log(list);


                        var selectF = "<option value='" + list.id + "' id='sele'>" + list.name + "</option>";

                        $("#select_f").append(selectF);

                        console.log(selectF);

                    })

                    $("#select_f").on('change', function () {
                        //alert("进入点击事件")
                        //alert($("#select_f").val());可以（被选中select的value值）
                        //alert($("#select_f").value);不可以
                        $("#select_s").empty();
                        $.ajax({
                            type: "get",
                            url: 'CityAction!showS.action',
                            //datatype: 'json',
                            data: {id: $("#select_f").val()},
                            success: function (data) {
                                $.each($.parseJSON(data), function (i, list) {
                                    console.log($.parseJSON(data))

                                    var selectS = "<option value='" + list.id + "'>" + list.name + "</option>";

                                    $("#select_s").append(selectS);

                                })
                            }
                        })

                    })


                    //console.log($.parseJSON(data)[0].id);
                    $.ajax({
                        type: "get",
                        url: 'CityAction!showS.action',
                        //datatype: 'json',
                        data: {id: $.parseJSON(data)[0].id},
                        success: function (data) {
                            $.each($.parseJSON(data), function (i, list) {
                                var selectS = "<option value='" + list.id + "'>" + list.name + "</option>";
                                $("#select_s").append(selectS);

                            })
                        }
                    })
                },
                error: function () {
                    alert("请求城市失败")
                }
            })


        })


        <%--验证邮箱的方法--%>

        function checkEmail() {
            var myemail = document.getElementsByName("myemail")[0].value;
            var myReg = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;

            if (myemail == "") {
                myspan.innerText = "请填写邮箱！";
            } else if (!myReg.test(myemail)) {
                myspan.innerText = "邮箱格式不对!";
            } else {
                $.ajax({
                    type: "post",
                    url: 'UserAction!show.action',
                    data: {//设置数据源
                        myemail: myemail
                    },
                    // dataType: "json",请求失败
                    //dataType:"string",请求失败
                    //contentType: "text/xml;charset=utf-8",后台获取的值为null
                    //contentType: "charset=utf-8",获取的值为空
                    //contentType: "application/json; charset=utf-8",不可以
                    //contentType: "text/xml",不可以
                    contentType: "application/x-www-form-urlencoded",//可以
                    success: function (data) {
                        myspan.innerText = data;
                    },
                    error: function () {
                        // console.log(url)
                        console.log("请求失败")
                    }
                })
            }

        }

        /*验证密码为8位*/
        function checkPass() {
            var pass = document.getElementsByName("pass")[0].value;
            if (pass.length != 8) {
                passSpan.innerText = "密码只能为8位！";
            } else {
                passSpan.innerText = "";
            }
        }

        //验证码刷新
        function flush() {
            //alert("进入点击事件")
            $("#img").attr('src', "VerifyAction?timestamp=" + new Date().getTime());
        }


        //        $(function () {
        //            var x = 22;
        //            var y = 20;
        //            $("a.smallimage").hover(function (e) {  //绑定一个鼠标悬停时事件
        //                //新建一个p标签来存放大图片，this.rel就是获取到a标签的大图片的路径，然后追加到body中
        //                $("body").append('<p id="bigimage"><img src="' + this.rel + '" alt="" /></p>');
        //                //改变小图片的透明度为0.5，结合上面的CSS，看起来就象是图片变暗了
        //                $(this).find('img').stop().fadeTo('slow', 0.5);
        //                //将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，然后以fadeIn的效果显示
        //                $("#bigimage").css({top: (e.pageY - y ) + 'px', left: (e.pageX + x ) + 'px'}).fadeIn('fast');
        //            }, function () { //鼠标离开后
        //                //将变暗的图片复原
        //                $(this).find('img').stop().fadeTo('slow', 1);
        //                //移除新增的p标签
        //                $("#bigimage").remove();
        //            });
        //            $("a.smallimage").mousemove(function (e) {  //绑定一个鼠标移动的事件
        //                //将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，这样大图片就能跟随图片而移动了
        //                $("#bigimage").css({top: (e.pageY - y ) + 'px', left: (e.pageX + x ) + 'px'});
        //            });
        //        });


    </script>
</head>
<body id="body">
<form action="UserAction" method="post">
    <table>
        <tr>
            <td>邮&nbsp;&nbsp;箱：</td>
            <td><input type="text" name="myemail" onblur="checkEmail()"/></td>
            <td><span id="myspan" style="color: red"></span></td>
        </tr>
        <tr>
            <td>密&nbsp;&nbsp;码：</td>
            <td><input type="password" name="pass" onblur="checkPass()"/></td>
            <td><span id="passSpan" style="color: red"></span></td>
        </tr>
        <tr>
            <td>
                <select id="select_f">
                    <%--&lt;%&ndash;<option value="">河南</option>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<option value="">上海</option>&ndash;%&gt;--%>
                </select>
            </td>
            <td>
                <select id="select_s">

                </select>
            </td>
        </tr>
        <tr>
            <td>验证码：</td>
            <td><p id="tdp"><img src="VerifyAction" id="img" onclick="flush()"/></p></td>
        </tr>
        <tr>
            <td id="bigimg"></td>
            <td>点击刷新</td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"/>提交</td>
        </tr>
    </table>
</form>
</body>
</html>
