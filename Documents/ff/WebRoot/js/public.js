//该文件主要是对公共样式的更改
$(document).ready(function(){
	//初始加载"回到顶部"函数
	checkScrollTop();
	
	//针对页面宽度变化后，导航栏图标的点击事件
	$(".top_main_nav_icon").click(function(){
		var ul = $(".top_main_nav > ul");
		if(ul.css("display") == "none"){
			ul.slideDown("normal");
		}else{
			ul.slideUp("mormal");
		}	
	});
	
	//注册窗口大小改变函数
	$(window).resize(checkScrollTop);
	//注册窗口滚动函数
	$(window).scroll(checkScrollTop);
	
	//滚动条加载"回到顶部"函数
	function checkScrollTop(){
		var sc = $(window).scrollTop();
		var rwidth = $(window).width();
		if(sc > 0) {
			$("#goTopBtn").css("display", "block");
			$("#goTopBtn").css("left", (rwidth - 36) + "px")
		} else {
			$("#goTopBtn").css("display", "none");
		}
	}
	
	//注册"回到顶部"点击函数
	$("#goTopBtn").click(function() {
		scrollOffset(0, 500);
	});
	
	var flag = $("#flag").offset().top > $(window).height() ? $("#flag").offset().top : $(window).height();
	//注册主页"详细了解"按钮跳转事件
	$(".btn_flag").click(function(){	
		scrollOffset(flag, 400);
	});
	
	var flag1 = $(document).height();
	//注册主页"下载App"按钮跳转事件
	$(".btn_flag1").click(function(){	
		scrollOffset(flag1, 1000);
	});
	
	//滚动条偏移函数
	function scrollOffset(temp, time){
		$("body, html").animate({
			scrollTop: temp
		}, time);
	};
	
	//注册主页导航栏置顶事件
	$(document).scroll(function(){
//		console.log(document.body.scrollHeight);//页面总高度
//		console.log(document.body.scrollTop);//页面卷起高度
		var scrollTop = document.body.scrollTop;
		var top = $(".index_top_box");
		//滚动条重新移动
		if(scrollTop > 0){
			top.css({"background-color":"#373D41", "position": "fixed"});
			$(".top_logo_1").removeClass("index_top_logo");
			$(".top_logo_1").css("border", "none");
			$(".top_main_1").removeClass("index_top_main");
			
		}
		//滚动条回到顶部
		if(scrollTop == 0){
			top.css({"background-color":"transparent", "position": "static"});
			$(".top_logo_1").addClass("index_top_logo");
			$(".top_main_1").addClass("index_top_main");
		}	
	});
	
	
});
