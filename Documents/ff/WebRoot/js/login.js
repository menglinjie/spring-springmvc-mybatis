//注意 : 用户登录与注册时，实现更加友好的用户交互行为

//文档开始加载
$(document).ready(function() {

	//"用户名"输入框焦点丢失事件
	$("#username").blur(function() {
		//清空原先样式
		cleanBeforeStyle($(this));
		var login = /^1[3|7|4|5|8][0-9]{9}$/;
		//正则验证输入符合要求，若符合规范提示输入正确，若不符合规范提示输入错误
		if(login.test($(this).val())) { //符合规范
			setSuccessStyle($(this));
		} else { //不符合规范
			//用户名输入框判空操作
			if($(this).val() == "") {
//				warningTip(this, 0, 800);
			} else {
				warningTip(this, 4, 2000);
				setErrorStyle($(this));
			}
		}
	});
	
	//"用户名"输入框焦点丢失事件
	$("#phone").blur(function() {
		//清空原先样式
		cleanBeforeStyle($(this));
		var login = /^1[3|7|4|5|8][0-9]{9}$/;
		//正则验证输入符合要求，若符合规范提示输入正确，若不符合规范提示输入错误
		if(login.test($(this).val())) { //符合规范
			setSuccessStyle($(this));
		} else { //不符合规范
			//用户名输入框判空操作
			if($(this).val() == "") {
//				warningTip(this, 0, 800);
			} else {
				warningTip(this, 4, 2000);
				setErrorStyle($(this));
			}
		}
	});

	//密码输入框焦点丢失事件
	$("#password").blur(function() {
		//清空原先样式
		cleanBeforeStyle($(this));
		var login = /^[a-zA-Z0-9]{6,100}$/;
		//正则验证输入符合要求，若符合规范提示输入正确，若不符合规范提示输入错误
            if(login.test($(this).val())) { //符合规范
                setSuccessStyle($(this));
		} else { //不符合规范
			//用户名输入框判空操作
			if($(this).val() == "") {
//				warningTip(this, 1, 800);
			} else {
				warningTip(this, 4, 2000);
				setErrorStyle($(this));
			}
		}
	});

	//重复密码框点击丢失事件
	$("#password_again").blur(function() {
		//清空原先样式
		cleanBeforeStyle($(this));
		var login = /^[a-zA-Z0-9]{6,100}$/;
		if($(this).val() == "") {
//			warningTip(this, 1, 800);
		} else {
			if(login.test($(this).val())) { //符合规范
				//判断两次密码是否保持一致
				if($(this).val() != $("#password").val()) {
					warningTip(this, 3, 800);
					setErrorStyle($(this));
				} else {
					setSuccessStyle($(this));
				}
			} else { //不符合规范	
				warningTip(this, 4, 2000);
				setErrorStyle($(this));
			}
		}
	});

	//验证码框点击丢失事件
	$("#cycle").blur(function() {
		//清空原先样式
		cleanBeforeStyle($(this));

		//正则验证输入符合要求，若符合规范提示输入正确，若不符合规范提示输入错误
		if(/^[0-9a-zA-Z]{4}$/.test($(this).val())) { //符合规范
			setSuccessStyle($(this));
		} else { //不符合规范
			//用户名输入框判空操作
			if($(this).val() == "") {
//				warningTip(this, 2, 800);
			} else {
				warningTip(this, 4, 2000);
				setErrorStyle($(this));
			}
		}
	});

	$("#sms_cycle").blur(function() {
		//清空原先样式
		cleanBeforeStyle($(this));

		//正则验证输入符合要求，若符合规范提示输入正确，若不符合规范提示输入错误
		if(/^[0-9]{6}$/.test($(this).val())) { //符合规范
			setSuccessStyle($(this));
		} else { //不符合规范
			//用户名输入框判空操作
			if($(this).val() == "") {
//				warningTip(this, 2, 800);
			} else {
				warningTip(this, 4, 2000);
				setErrorStyle($(this));
			}
		}
	});
	
	//发送验证码按钮请求方法
	$("#sms_btn").click(function() {
		//校验手机号是否为空及是否符合规范
		if($("#phone").val() != "" && /^1[3|7|4|5|8][0-9]{9}$/.test($("#phone").val())){
			$.ajax({
				type: "GET", //设置请求方式
				url: "../user/mobileCycle.action?phone="+$("#phone").val() //设置请求路径	
			});
			$(this).attr("disabled", "disabled");
			//重置变量操作
			count = 60;
			timeOut = false;
			time();
		}else{
			warningTip("#phone", 5, 2000);
		}		
	});

	//短信计时函数
	var count = 60; //计数器
	var timeOut = false; //启动及关闭计数器的标志  
	function time() {	
		if(timeOut) return;	//根据标志判断是否继续进行
		$("#sms_btn").html("重新发送(" + count + ")");
		count--;
		if(count == 0) {//当计数结束后
			timeOut = true;//改变标志
			$("#sms_btn").removeAttr("disabled");//重置disabled
			$("#sms_btn").html("重新发送");//重置内容
		}
		setTimeout(time, 1000); //time是指本身,延时递归调用自己,100为间隔调用时间,单位毫秒  
	}

	//登录表单提交逻辑判断
	$("#login_form_submit").click(function() {
		//获取当前元素下的所有input元素节点
		var inps = $("#login_form").find("input[type!='checkbox']");
		//设置表单数据是否提交的标志
		var flag = false;

		//循环遍历表单下所有的input元素
		for(var i = 0; i < inps.length; i++) {
			var temp = $(inps[i]).parent("div").next().attr("class");
			console.log(temp);
			if(temp.indexOf("glyphicon-remove") != -1) {
				//警告框提示
				warningTip(inps[i], 4, 2000);
				//改变标志位
				flag = true;
				//当出现警告框时，直接退出
				break;
			}

			//判断input元素值是否为空
			if($(inps[i]).val() == "") {
				//警告框提示
				warningTip(inps[i], i, 800);
				//改变标志位
				flag = true;
				//当出现警告框时，直接退出
				break;
			}
		}

		//提交表单数据
		if(!flag) {
			$("#remb_password").val("off");
			$("#remb_password:checked").val("on");
			$("#login_form").submit();
		}
	});

	//注册表单提交逻辑判断
	$("#register_form_submit").click(function() {
		//获取当前元素下的所有input元素节点
		var inps = $("#register_form").find("input[type!='checkbox']");
		//设置表单数据是否提交的标志
		var flag = false;

		//密码校验是否相等
		//获取当前所有type属性为password的输入框
		var inps_temp = $("#register_form").find("input[type*='password']");
		if($(inps_temp[0]).val() != "" && $(inps_temp[1]).val() != "") {
			if($(inps_temp[0]).val() != $(inps_temp[1]).val()){
				warningTip(inps_temp[0], 3, 800);
				flag = true;
			}	
		}

		//循环遍历表单下所有的input元素
		for(var i = 0; i < inps.length; i++) {
			var temp = $(inps[i]).parent("div").next().attr("class");
			
			if(temp.indexOf("glyphicon-remove") != -1) {
				//警告框提示
				warningTip(inps[i], 4, 2000);
				//改变标志位
				flag = true;
				//当出现警告框时，直接退出
				break;
			}

			//判断input元素值是否为空
			if($(inps[i]).val() == "") {
				//警告框提示
				if(i != inps.length - 2 && i != inps.length - 1) { //用户名及密码提示
					warningTip(inps[i], i, 800);
				} else if(i == inps.length - 2) { //密码校验提示
					warningTip(inps[i], 1, 800);
				} else if(i == inps.length - 1) { //验证码提示
					warningTip(inps[i], 2, 800);
				}
				
				//改变标志位
				flag = true;
				//当出现警告框时，直接退出
				break;
			}
			
		}
		console.log(flag);
		//提交表单数据
		if(!flag){
			$("#register_form").submit();
		}	
	});

	/**
	 * 设置不符合规范样式
	 * 
	 * @param target 目标对象
	 * */
	function setErrorStyle(target) {
		target.parent("div").next("span").addClass("glyphicon-remove");
		target.parent("div").parent("div").addClass("has-error");
	}

	/**
	 * 设置符合规范样式
	 *
	 * @param target 目标对象
	 * */
	function setSuccessStyle(target) {
		target.parent("div").next("span").addClass("glyphicon-ok");
		target.parent("div").parent("div").addClass("has-success");
	}

	/**
	 * 清除输入框原先样式内容
	 * 
	 * @param target 目标对象
	 * */
	function cleanBeforeStyle(target) {
		target.parent("div").next().removeClass("glyphicon-ok glyphicon-remove");
		target.parent("div").parent("div").removeClass("has-success has-error");
	}

	/**
	 * 警告提示框显示函数
	 * @param traget 目标元素对象(即输入框对象)
	 * @param tipIndex 提示框文本数据的编号
	 * */
	function warningTip(target, tipIndex, timeOut) {
		var warning_box = warningFrameStyle(tipIndex);
		//获取添加警告框的父元素
		$(target).parent().parent(".form-group").append(warning_box);
		setTimeout(function() {
			//重新隐藏元素
			warning_box.fadeOut();
		}, timeOut);
	}

	/**
	 * 定义警告样式模板
	 * @param index 带填充数据的编号
	 * @return 已定义的警告框样式
	 * */
	function warningFrameStyle(index) {
		//定义填充文本数据
		var warningString = ["请填写用户名", "请填写密码", "请填写验证码", "两次密码不一致", "请规范输入内容", "请填写合法的手机号"];
		//设置定位标志
		$(".form-group").css("position", "relative");

		//定义警告框样式
		var warning_box = $("<div class='warning_box'>");
		var warning_box_style = $("<div class='warning_box_style'>");
		var warning_box_position = $("<div class='warning_box_position'>");
		var warning_box_text = $("<span class='warning_box_text'>");
		//填充数据
		warning_box_text.text(warningString[index]);

		//添加元素
		warning_box_position.append("<label class='warning_box_logo'>");
		warning_box_position.append(warning_box_text);
		warning_box_style.append(warning_box_position);
		warning_box.append(warning_box_style);
		warning_box.css({
			"top": "40px",
			"left": "60px"
		});

		//返回最上父级元素
		return warning_box;
	}
	
	/**
	 * 警告提示框显示函数
	 * @param traget 目标元素对象(即输入框对象)
	 * @param tipIndex 提示框文本数据的编号
	 * */
	function warningTip1(target, tipText, timeOut) {
		var warning_box = warningFrameStyle1(tipText);
		//获取添加警告框的父元素
		$(target).parent().parent(".form-group").append(warning_box);
		setTimeout(function() {
			//重新隐藏元素
			warning_box.fadeOut();
		}, timeOut);
	}

	/**
	 * 定义警告样式模板
	 * @param index 带填充数据的编号
	 * @return 已定义的警告框样式
	 * */
	function warningFrameStyle1(tipText) {
		//设置定位标志
		$(".form-group").css("position", "relative");

		//定义警告框样式
		var warning_box = $("<div class='warning_box'>");
		var warning_box_style = $("<div class='warning_box_style'>");
		var warning_box_position = $("<div class='warning_box_position'>");
		var warning_box_text = $("<span class='warning_box_text'>");
		//填充数据
		warning_box_text.text(tipText);

		//添加元素
		warning_box_position.append("<label class='warning_box_logo'>");
		warning_box_position.append(warning_box_text);
		warning_box_style.append(warning_box_position);
		warning_box.append(warning_box_style);
		warning_box.css({
			"top": "40px",
			"left": "60px"
		});

		//返回最上父级元素
		return warning_box;
	}
});