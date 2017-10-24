//注意 : 本js文件主要为index.html服务，为了更好区别相关服务，将其他页面的js逻辑转移至其他文件中(也就是不集中在同一js文件中)

$(document).ready(function(){
	//首次加载调用调整窗口大小函数
	flushScreenWidth();
	//获取ul的高度
	var ul_height = $(".root > ul").height();
	
	//为侧边导航栏中的所有ul元素节点注册点击事件
	$(".root > ul > label").click(treeClick);	
	
	function treeClick(){	
		//获取ul节点
		var ul = $(this).parent("ul");
		//获取菜单中的li元素节点
		var li = $(this).first().nextAll("li");
		//获取"小三角"元素所在的label
		var flag_parent = $(this).children("label");
		
		//获取label的top，根据此判断选中与未选中
		var flag = flag_parent.css("top");
		if(flag == "23px"){//当ul下的li项处于显示状态时执行
			//"小三角"样式设置
			flag_parent.css(
			{"transform":"rotate(0deg)", "top":"17px", "left":"40px"});
			//重置ul高度
			ul.css("height", ul_height);
			//将ul下的列表隐藏
			li.hide("fast");
		}else{//当ul下的li项处于隐藏状态时执行
			//"小三角"样式设置
			flag_parent.css(
			{"transform":"rotate(90deg)", "top":"23px", "left":"36px"});
			//重置ul高度
			ul.css("height", ul_height * (li.length + 1));
			//将ul下的列表显示
			li.show("normal");
		}
		/**
		 * 关于ul下列表li显隐动画，测试了很多，大部分都有小bug，目前这种比较稳定。
		 * */
	}
	
	//为侧边栏中的所有li列表项注册点击事件
	$(".root > ul > li").click(function(){
		//获取父级元素ul中的文本值
		var ul_node = $(this).parent("ul").children("label");
		//获取此时li列表项中的文本值
		var li_node = $(this);
		//获取目标文本下的所有a标签
		var target = $(".top_main_map_text").children("a");
		
		//清空原本目标文本值
		$(".top_main_map_text").empty();
		//调用字符串分割与替换方法，将重新封装后的结果返回
		var result = stringSplitReplace(ul_node, li_node, target);
		//将元素重新添加
		for(var i = 0; i < result.length; i++){
			$(".top_main_map_text").append(result[i]);
			if(i != result.length - 1){
				$(".top_main_map_text").append("/");
			}	
		}
	});
	
	/**
	 * 字符串的分割与替换，并进行最后的封装
	 * @param ul 列表值
	 * @param li 列表项值
	 * @param target 目标值
	 * @return 返回最终封装好的对象
	 * */
	function stringSplitReplace(ul, li, target){
		//将目标字符串进行分割
		var result = target;
		result[1] = $("<a href='#'>" + ul.text().trim() + "</a>");
		result[2] = $("<a href='"+ li.children("a").attr("href") +"'>" + li.text().trim() + "</a>");
		return result;
	}
	
	//为退出按钮注册点击事件
	$(".slide_bottom_exit").click(function(){
		var flag = confirm("您确定退出系统吗？");
		if(flag){
			window.close();
		}
	});
	
	//为隐藏侧边栏注册事件
	$(".slide_bottom_hidden").click(function(){
		//将侧边栏隐藏
		$(".slide").hide();
		//将显示按钮以淡入的方式显示
		$(".slide_bottom_visible").fadeIn();
		//刷新表单区域宽度
		flushScreenWidth();		
	});
	
	//注册侧边栏显示点击事件
	$(".slide_bottom_visible").click(function(){
		//将侧边栏隐藏
		$(".slide").show();
		//将显示按钮隐藏
		$(".slide_bottom_visible").hide();
		//刷新表单区域宽度
		flushScreenWidth();	
	});
	
	//获取input标签样式是否隐藏
	var search_text = $("#search_text");	
	var search_form = $("#search_form");
	//为搜索框添加点击事件
	$(".search_svg").click(function(){	
		//获取隐藏属性
		var flag = search_text.attr("hidden");
		//进行逻辑判断，确定如何执行
		if(flag){//若标签隐藏，将标签显示
//			alert("输入框隐藏了");
			search_form.submit(function(){
				return false;
			});
		}else{//若标签没隐藏，直接提交表单内容
//			alert("输入框没隐藏，将会提交表单数据！！！");
			if(search_text.val() == ''){
				search_form.submit(function(){
					return false;
				});
			}else{
				search_form.submit(function(){
					return true;
				});
			}
		}	
	});
	
	//为输入框区域注册悬浮事件，移入展开输入框，移出则收缩输入框
	$(".search_position").hover(function(){
		//设置输入框区域样式
		$(".search_position").css({"width" : "300px", "border-radius" : "32px", "border" : "2px solid #fff"});
		$(".search_svg").css("top", "-2px");
		//显示输入框
		search_text.show();
	}, function(){
		//还原输入框区域样式
		$(".search_position").css({"width" : "32px", "border-radius" : "50%", "border" : "1.5px solid #000"});
		$(".search_svg").css("top", "-1px");
		//隐藏输入框
		search_text.hide();
	});
	
	//实时窗口大小检测函数
	window.onresize = flushScreenWidth;
	
	//窗口检测函数，实时改变隐藏框的宽度
	function flushScreenWidth(){
		var width = $(".overflow_box_1").width();
		$(".overflow_box_2").width(width + 22);
		
		//解决导航列表回显时的Bug
		if($(document).width() >= 1200){
			$(".top_main_nav > ul").css("display", "block");
			$(".content_form_button_group").children("input").removeClass("btn-sm");
		}else{
			$(".top_main_nav > ul").css("display", "none");
			$(".content_form_button_group").children("input").addClass("btn-sm");
		}
		
		//改变按钮大小
		if($(document).width() >= 556){
			$(".content_form_button_group").children("input").removeClass("btn-xs");
		}else{
			$(".content_form_button_group").children("input").addClass("btn-xs");
		}
	};
});