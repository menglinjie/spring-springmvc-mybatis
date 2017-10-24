<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>个人信息 -- 缘来在此交友网</title>
		<link type="text/css" rel="stylesheet" href="../css/stylereset.css" />
		<link rel="stylesheet" href="../css/bootstrap.css" />
		<link rel="stylesheet" href="../css/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" type="text/css" href="../css/style.css">
		<link rel="stylesheet" href="../css/media.css" />
		<link rel="stylesheet" href="../css/alert.css" />
	</head>

	<body style="overflow-x: hidden; overflow-y: auto;">
		<!--Start top-->
		<header class="top clearfix">
			<!--Start top_logo-->
			<div class="top_logo">
				<a href="${pageContext.request.contextPath}/index.html" title="缘来在此交友网">缘来在此交友网</a>
			</div>
			<!--End top_logo-->

			<!--Start top_main-->
			<div id="people" class="top_main clearfix">
				<!--Start top_main_nav-->
				<nav class="top_main_nav clearfix">
					<label class="top_main_nav_icon hidden-lg pull-right">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
					</label>
					<ul class="clearfix">
						<li title="会员信息">
							<a href="queryPeople.html">会员信息</a>
						</li>
						<li title="礼物商城">
							<a href="giftMall.html">礼物商城</a>
						</li>
						<li title="博客系统">
							<a href="personBlog.html">博客系统</a>
						</li>
						<li :title="person.nickname">
							<a href="personInfo.jsp">{{ person.nickname }}</a>
						</li>
					</ul>
				</nav>
				<!--End top_main_nav-->
			</div>
			<!--End top_main-->
		</header>
		<!--End top-->

		<!--Start person_info_container-->
		<article  class="person_info_container container-fluid" style="padding: 0;z-index: 1;">
			<!--Start person_info_left-->
			<aside id="people1" class="person_info_left col-xs-12 col-sm-12 col-md-4 col-lg-4">
				<!--Start person_info_left_top-->
				<div class="person_info_left_top container-fluid">
					<div class="content_main_header">
						<h3>个人信息</h3>
					</div>
					<div class="person_info_logo col-xs-12 col-sm-12 col-md-12 col-lg-5">
						<section class="person_info_logo_1">
							<label>
								<img :src="'http://139.199.16.239:8080/demo/image/' + fileName" title="用户头像" alt="用户头像" class="img-responsive img-thumbnail"/>
							</label>
							<form name="upload_person_logo" method="post">
								<input type="file" id="upload_logo" name="file" />
							</form>
						</section>
						<section class="person_info_logo_2">
							<label for="upload_logo" title="更换头像" @click="changeHeadIcon()">更换头像</label>
						</section>
					</div>
					<section class="person_info_content col-xs-12 col-sm-12 col-md-12 col-lg-7">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="col-xs-5 col-sm-5 col-md-5 col-lg-5 control-label text-info">ID:</label>
								<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
									<p class="form-control-static text-muted text-center">{{ person.id }}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-5 col-sm-5 col-md-5 col-lg-5 control-label text-info">昵称:</label>
								<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
									<p class="form-control-static text-muted text-center">{{ person.nickname }}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-5 col-sm-5 col-md-5 col-lg-5 control-label text-info">魅力值:</label>
								<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
									<p class="form-control-static text-muted text-center">{{ account.charm }}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-5 col-sm-5 col-md-5 col-lg-5 control-label text-info">财富值</label>
								<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
									<p class="form-control-static text-muted text-center">{{ account.wealth }}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-5 col-sm-5 col-md-5 col-lg-5 control-label text-info">积分</label>
								<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
									<p class="form-control-static text-muted text-center">{{ account.point }}</p>
								</div>
							</div>
						</form>
					</section>
				</div>
				<!--End person_info_left_top-->

				<!--Start person_info_left_bottom-->
				<div class="person_info_left_bottom clearfix">
					<div class="content_main_header">
						<h3>系统公告栏</h3>
					</div>
					<!--  
					<div class="alert alert-danger alert-dismissible col-lg-12">
						<button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
						<strong>危险!</strong> 网站将于用户登录后十分钟后注销！！！
					</div>
					<div class="alert alert-warning alert-dismissible col-lg-12">
						<button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
						<strong>警告!</strong> 站点将于2017年4月24日 13:51:23紧急维修，届时将会关闭系统三十分钟，请做好相关准备工作！
					</div>
					<div class="alert alert-info alert-dismissible col-lg-12">
						<button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
						<strong>新信息!</strong> 请张三快速联系系统维护人员。
					</div>
					-->
				</div>
				<!--End person_info_left_bottom-->
			</aside>
			<!--End person_info_left-->

			<!--Start person_info_right-->
			<section id="app" class="person_info_right clearfix col-xs-12 col-sm-12 col-md-8 col-lg-8">
				<section class="person_info_right_1 container-fluid clearfix">
					<div class="text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<h3>个人主页</h3>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 15px;">
						<ul class="nav nav-pills nav-justified">
							<li v-for="(p, index) in list" :class="{ 'active': i == index }" v-on:click="changeActive(index)">
								<router-link :to="'/home' + (index + 1)">{{ p.text }}</router-link>
							</li>
						</ul>
					</div>

					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="container-fluid text-center" id="app1" style="background: transparent;border: 1px solid #ddd; border-radius: 4px;">
							<!--用于渲染匹配的组件-->
							<router-view></router-view>
						</div>
					</div>
				</section>
			</section>
			<!--End person_info_right-->
		</article>
		<!--End person_info_container-->

		<!--Start goToBtn-->
		<div id="goTopBtn" title="回到顶部" style="display: none; left: 1313px;">&and;</div>
		<!--End goToBtn-->

		<template id="temp1">
			<div class="" style="padding: 0;padding-top: 15px;">
				<form class="">
					
					<div class="form-group container-fluid text-center">
						<label style="font-size: 17px;">信息完善度:</label>
						<div class="progress" style="margin: 0;">
							<!--直接在progress-bar-striped的基础上添加active即可-->
							<div class="progress-bar progress-bar-info progress-bar-striped active" :style="{'width': personInfo.sum + '%'}">{{ personInfo.sum + '%' }}</div>
						</div>
					</div>
					
					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="input-group">
							<label class="input-group-addon" for="id">ID:</label>
							<input type="text" id="id" name="id" v-model="personInfo.id" class="form-control disabled" readonly />
						</div>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="input-group">
							<label class="input-group-addon" for="nickname">昵称:</label>
							<input type="text" id="nickname" v-model="personInfo.nickname" name="nickname" class="form-control" placeholder="请输入昵称"  />
						</div>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="input-group">
							<label class="input-group-addon" for="phone">电话:</label>
							<input type="text" id="phone" v-model="personInfo.phone"  name="phone" class="form-control disabled" placeholder="请输入电话" readonly />
						</div>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="input-group">
							<label class="input-group-addon" for="sex">性别:</label>
							<select class="form-control" v-model="personInfo.sex" >
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="input-group">
							<label class="input-group-addon" for="job">工作:</label>
							<input type="text" id="job" name="job" v-model="personInfo.job"  class="form-control" placeholder="请输入工作"  />
						</div>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="input-group date form_date">
							<label class="input-group-addon" for="birthday">生日:</label>
							<input class="form-control" id="birthday" v-model="personInfo.birthday"  name="birthday" type="text" >
						</div>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="input-group">
							<label class="input-group-addon" for="marriage">婚姻状况:</label>
							<select class="form-control" v-model="personInfo.marriage" >
								<option value="未婚">未婚</option>
								<option value="离异">离异</option>
								<option value="丧偶">丧偶</option>
								<option value="单身">单身</option>
							</select>
						</div>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="input-group">
							<label class="input-group-addon" for="address">地址:</label>
							<input type="text" id="address" name="address" v-model="personInfo.address"  class="form-control" placeholder="请输入地址"  />
						</div>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="input-group">
							<label class="input-group-addon" for="height">身高:</label>
							<input type="text" id="height" name="height" v-model="personInfo.height"  class="form-control" placeholder="请输入身高"  />
						</div>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<div class="input-group">
							<label class="input-group-addon" for="salary">收入:</label>
							<input type="text" id="salary" name="salary" v-model="personInfo.salary"  class="form-control" placeholder="请输入收入"  />
						</div>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="input-group">
							<label class="input-group-addon" for="introduce">个人介绍:</label>
							<textarea class="form-control" id="introduce" v-model="personInfo.introduce"  name="introduce" style="resize: none;height: 200px;" >

							</textarea>
						</div>
					</div>
					
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
					
						<button type="button" class="btn btn-success" @click="submitInfo()">提交修改</button>
						
					</div>
					
				</form>
			</div>
		</template>
		
		<template id="temp2">
			<div style="padding: 0;padding-top: 15px;">
				<div class="form-group thumbnail article" v-for="(o, index) in blogList">
					<header class="article_header clearfix">
						<h3 class="pull-left"><a class="text-success" :href="'personBlog.html?id=' + o.user.id" target="_blank" title="进入博客系统">{{ o.title }}</a></h3>
						<div class="article_header_info pull-right">
							<label class="hidden-xs">
								<span class="glyphicon glyphicon-eye-open"></span>
								<span>浏览量</span>
								(<span>{{ o.countView }}</span>)
							</label>
							<label class="hidden-xs">
								<span class="glyphicon glyphicon-comment"></span>
								<span class="hidden-xs">评论数</span>
								(<span>{{ o.countForward }}</span>)
							</label>
						</div>	
					</header>
					
					<article class="article_simple_content text_overflow1 text-left text-muted">
						<strong class="text-danger">内容摘要:</strong>
						{{ o.brief }}
					</article>
				</div>
				
				<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
					<a href="writeBlog.html" class="btn btn-success">发表新文章</a>
				</div>
			</div>
		</template>
		<template id="temp3">
			<div class="gift_list person_list col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-bottom: 15px;">					
				<div class="gift person col-xs-12 col-sm-6 col-md-4 col-lg-3" v-for="(o, index) in ownerList">
					<div class="clearfix">
					
						<div class="person_logo col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<a :href="'personInfo2.html?id=' + o.id" target="_blank" title="查看用户信息">
								<img :src="o.imgPath" class="img-responsive" />
							</a>
						</div>
						<div class="caption col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="clearfix person_name">
								<h4 class="text-center">{{ o.nickname }}</h4>
							</div>

							<p class="text_overflow1 person_info">
								<span class="text-center" style="display: block;text-indent: 0px;">个人简介</span>
								{{ o.introduce }}
							</p>
							
						</div>
					</div>
				</div>
			</div>
		</template>
		
		<template id="temp4">
			<div class="gift_list person_list col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-bottom: 15px;">					
				<div class="gift person col-xs-12 col-sm-6 col-md-4 col-lg-3" v-for="(o, index) in fansList">
					<div class="clearfix">
						<div class="person_logo col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<a :href="'personInfo2.html?id=' + o.id" target="_blank" title="查看用户信息">
								<img :src="o.imgPath" class="img-responsive" />
							</a>
						</div>
						<div class="caption col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="clearfix person_name">
								<h4 class="text-center">{{ o.nickname }}</h4>
							</div>

							<p class="text_overflow1 person_info">
								<span class="text-center" style="display: block;text-indent: 0px;">个人简介</span>
								{{ o.introduce }}
							</p>
						</div>
					</div>
				</div>
			</div>
		</template>

	</body>

	<script type="text/javascript" src="../js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap.js"></script>
	<script type="text/javascript" src="../js/tree.js"></script>
	<script type="text/javascript" src="../js/public.js"></script>
	<script type="text/javascript" src="../js/vue.js"></script>
	<script type="text/javascript" src="../js/vue-router.js"></script>
	<script type="text/javascript" src="../js/vue-resource.js" ></script>
	<script type="text/javascript">
		$(document).ready(function() {
			(function(Vue) {
				/* 创建组件构造器  */
				var Temp1 = Vue.extend({
					template: '#temp1',
					data: function(){
						return {
							personInfo: {}
						}
					},
					created: function(){
						this.requestData();
					},
					methods: {
						requestData: function(){
							this.$http({
								method: "POST",
								url: "../user/findById.action?id=" + this.getCookie("userId")
							}).then((resp) => {
								this.personInfo = resp.data;
								
								if (this.personInfo.sex) {
									this.personInfo.sex = '男';
								} else {
									this.personInfo.sex = '女';
								}
							}).catch((resp) => {
								alert("加载用户信息失败!");
							})
						},
						getCookie: function(name){
						    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
						    if(arr=document.cookie.match(reg)){
						    	return unescape(arr[2]);
						    }else{
						    	return null;
						    } 
						},
						submitInfo: function(){
							var flag = confirm("您确定提交修改后的个人信息吗？");
							if (flag) {
								if (this.personInfo.sex == "男") {
									this.personInfo.sex = true;
								} else {
									this.personInfo.sex = false;
								}
								$.ajax({
							        type: "post",
							        url: "../user/updateUser.action?user=" + this.personInfo,
							        data: JSON.stringify(this.personInfo),
							        dataType: "json",
							        contentType: "application/json;charset=UTF-8",
							        async: true,
							        cache: true,
							        success: function(data) {
							        	if(data.message){
							        		alert(data.message); 
							        	}
							        	location.reload();
							        }
							    });
							    this.requestData();
							}
						}
					}
				})
				
				
				// 加载博客列表
				var Temp2 = Vue.extend({
					template: '#temp2',
					data: function(){
						return {
							blogList: []
						}
					},
					created: function(){
						this.$http({
							method: "POST",
							url: "../forward/showAllForward.action?type=" + "all" + "&userId=" + this.getCookie("userId")
						}).then((resp) => {
							this.blogList = resp.data;
							for(var i = 0; i < this.blogList.length; i++){
								if (!this.blogList[i].countForward){
									this.blogList[i].countForward = 0;
									console.log(this.blogList[i].countForward);
								}
							}
						}).catch((resp) => {
							alert("加载博客列表数据失败!");
						})
					},
					methods: {
						getCookie: function(name){
						    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
						    if(arr=document.cookie.match(reg)){
						    	return unescape(arr[2]);
						    }else{
						    	return null;
						    } 
						}
					}
				})
				// 加载关注列表
				var Temp3 = Vue.extend({
					template: '#temp3',
					data: function(){
						return {
							ownerList: []
						}
					},
					created: function(){
						this.$http({
							method: "POST",
							url: "../user/selFllow.action?userId=" + this.getCookie("userId")
						}).then((resp) => {
							this.ownerList = resp.data;
						}).catch((resp) => {
							alert("加载用户关注列表失败!");
						})
					},
					methods: {
						getCookie: function(name){
						    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
						    if(arr=document.cookie.match(reg)){
						    	return unescape(arr[2]);
						    }else{
						    	return null;
						    } 
						} 
					}
				})
				
				// 加载粉丝列表
				var Temp4 = Vue.extend({
					template: '#temp4',
					data: function(){
						return {
							fansList: []
						}
					},
					created: function(){
						this.$http({
							method: "POST",
							url: "../user/selFans.action?userId=" + this.getCookie("userId")
						}).then((resp) => {
							this.fansList = resp.data;
						}).catch((resp) => {
							alert("加载用户粉丝列表失败!");
						})
					},
					methods: {
						getCookie: function(name){
						    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
						    if(arr=document.cookie.match(reg)){
						    	return unescape(arr[2]);
						    }else{
						    	return null;
						    } 
						} 
					}
				})
				/* 创建路由器  */
				var router = new VueRouter({
					routes: [{
							path: '/',
							redirect: '/home1'
						},
						{
							path: '/home1',
							component: Temp1
						},
						{
							path: '/home2',
							component: Temp2
						},
						{
							path: '/home3',
							component: Temp3
						},
						{
							path: '/home4',
							component: Temp4
						}
					]
				})

				new Vue({
					el: "#app",
					router: router,
					data: {
						list: [{
								text: "个人信息"
							},
							{
								text: "我的博客"
							},
							{
								text: "我的关注"
							},
							{
								text: "我的粉丝"
							}
						],
						i: 0
					},
					methods: {
						changeActive: function(index) {
							this.i = index;
						},
						getCookie: function(name){
						    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
						    if(arr=document.cookie.match(reg)){
						    	return unescape(arr[2]);
						    }else{
						    	return null;
						    } 
						}
					}
				})
				
				new Vue({
					el: "#people",
					data: {
						person: {}
					},
					created: function(){
						// 加载用户信息
						this.$http({
							method: "POST",
							url: "../user/findById.action?id=" + this.getCookie("userId")
						}).then((resp) => {
							this.person = resp.data;
						}).catch((resp) => {
							alert("加载用户信息失败!");
						})	
					},
					methods: {
						getCookie: function(name){
						    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
						    if(arr=document.cookie.match(reg)){
						    	return unescape(arr[2]);
						    }else{
						    	return null;
						    } 
						}
					}
				})
				
				new Vue({
					el: "#people1",
					data: {
						person: {},
						fileName: "",
						account: {}
					},
					created: function(){
						// 加载用户信息
						this.$http({
							method: "POST",
							url: "../user/findById.action?id=" + this.getCookie("userId")
						}).then((resp) => {
							this.person = resp.data;
						}).catch((resp) => {
							alert("加载用户信息失败!");
						})
						
						// 获取用户头像
						this.$http({
							method: "POST",
							url: "../attachment/showPicture.action?userId=" + this.getCookie("userId")
						}).then((resp) => {
							this.fileName = resp.data.fileName;
						}).catch((resp) => {
							alert("加载用户信息失败!");
						})
						
						// 加载用户的账户信息
						this.$http({
							method: "POST",
							url: "../user/selAccount.action?userId=" + this.getCookie("userId")
						}).then((resp) => {
							this.account = resp.data;
						}).catch((resp) => {
							alert("加载用户信息失败!");
						})
					},
					methods: {
						getCookie: function(name){
						    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
						    if(arr=document.cookie.match(reg)){
						    	return unescape(arr[2]);
						    }else{
						    	return null;
						    } 
						},
						changeHeadIcon: function(){
							// 监听文件框的变化
							$("#upload_logo").change(function(){
								var flag = false;
								var formData = new FormData();
								//实现上传多个文件
								var files = $("#upload_logo")[0].files;
								for(var i = 0; i < files.length; i++) {
									//判断文件类型和大小
									console.log(files[i]);
									var extStart = files[i].name.lastIndexOf(".");//获取文件后缀名开始位置
									var extContent = files[i].name.substring(extStart, files[i].length).toUpperCase();//获取文件后缀名，并强制转换为大写
									console.log(extContent);
									if(extContent != ".BMP" && extContent != ".GIF" && extContent != ".JPG" && extContent != ".JPEG" && extContent != ".PNG"){
										alert("图片类型仅限bmp、gif、jpg、jpeg、png，" + files[i].name + "不符合要求！！");
										flag = true;//更改标志位，拒绝上传
										alert("上传失败2!");
										break;
									}else{
										//再判断文件大小是否符合要求
										if(files[i].size < 5 * 1024 * 1024){//文件大小小于5M
											alert("符合要求，给予上传");
											formData.append("file", files[i]);		
										}else{
											alert("文件" + files[i].name + "，大小不符合要求！！");
											flag = true;//更改标志位，拒绝上传
											alert("上传失败1!");
											break;
										}
									}
								}	
								if(!flag){
									$.ajax({
										type: "POST",
										url: "../attachment/updateUser.action",
										data: formData,
										cache: false,
										contentType: false, //必须false才会避开jQuery对 formdata 的默认处理 , XMLHttpRequest会对 formdata 进行正确的处理
										processData: false, //必须false才会自动加上正确的Content-Type
										async: true,
										success: function() {
											alert("上传成功！！！");
											location.reload();
										},
										error: function(){
											//若出现错误，直接返回退出
											alert("上传失败!");
										}
									});
								}
							})
						}
					}
				})
			})(Vue)

		})
	</script>
	<script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap-datetimepicker.zh-CN.js"></script>

</html>