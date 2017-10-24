<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
				<div class="text-center">
					<h2>缘来在此后台管理系统</h2>
				</div>
				<!-- End content_main_header -->
				<!-- Start data_left -->
				<section class="data_left clearfix col-xs-12 col-sm-12 col-md-3 col-lg-3">
					<div class="panel panel-default">
						<!-- Start pandel-heading-->
						<div class="panel-heading">
							<h4 class="text-center">管理菜单选项</h4>
						</div>
						<!-- End pandel-heading-->
						
						<!--Start list-group-->
						<ul class="list-group">
							<li class="list-group-item" v-for="(p, index) in list" :class="{'active': i == index}" @click="changeActive(index)">
								<router-link :to="'/home' + (index + 1)">{{ p.text }}</router-link>
							</li>
							
						</ul>
						<!--End list-group-->
					</div>
				</section>
				<!-- End data_left -->

				<!-- Start data_right -->
				<section class="data_right clearfix col-xs-12 col-sm-12 col-md-9 col-lg-9">
					<!--用于渲染匹配的组件-->
					<router-view></router-view>
				</section>
				<!-- End data_right -->
			</article>
			<!-- End data_inner_container -->
		</article>
		<!-- End data_outer_container -->
		
		
		<template id="temp1">
			<div class="panel panel-default">
				<!--Start pandel-heading-->
				<div class="panel-heading">
					<h4>
						<span>用户信息列表</span>
					</h4>
				</div>
				<!--End pandel-heading-->

				<!--Start table-responsive-->
				<div class="table-responsive container-fluid">
					<table class="table table-hover">
						<tr>
							<th>#</th>
							<th>ID</th>
							<th>用户昵称</th>
							<th>电话</th>
							<th>财富值</th>
							<th>积点值</th>
							<th>魅力值</th>
							<th>当前状态</th>
							<th>操作</th>
						</tr>
						<tr v-for="(o, index) in userList" :style="{'color': o.state == 1 ? 'red' : '#000' }">
							<td>{{ index + 1 }}</td>
							<td>{{ o.id }}</td>
							<td>{{ o.nickname }}</td>
							<td>{{ o.phone }}</td>
							<td>{{ o.wealth }}</td>
							<td>{{ o.point }}</td>
							<td>{{ o.charm }}</td>
							<td>{{ o.state }}</td>
							<td>
								<button v-if="o.state == 1" style="outline:none" class="btn btn-link" type="button" @click="backUser(o.id)">恢复账户</button>
								<button v-if="o.state == 0" style="outline:none" class="btn btn-link" type="button" @click="delUser(o.id)">冻结账户</button>
							</td>
						</tr>
						
					</table>
				</div>
				<!--Start table-responsive-->
			</div>
		</template>
		
		
		<template id="temp2">
			<!--Start pandel-heading-->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>
						<span>博客信息列表</span>
					</h4>
				</div>
				<!--End pandel-heading-->

				<!--Start table-responsive-->
				<div class="table-responsive container-fluid">
					<table class="table table-hover">
						<tr>
							<th>#</th>
							<th>ID</th>
							<th>标题</th>
							<th>写作时间</th>
							<th>创建者</th>
							<th>当前状态</th>
							<th>操作</th>
						</tr>
						<tr v-for="(o, index) in blogList" :style="{'color': o.state == 'off' ? 'red' : '#000' }">
							<td>{{ index + 1 }}</td>
							<td>{{ o.id }}</td>
							<td>{{ o.title }}</td>
							<td>{{ o.writeTime }}</td>
							<td>{{ o.creatorId }}</td>
							<td>{{ o.state }}</td>
							<td>
								<button v-if="o.state == 'off'" style="outline:none" class="btn btn-link" type="button" @click="backBlog(o.id)">恢复文章</button>
								<button v-if="o.state == 'on'" style="outline:none" class="btn btn-link" type="button" @click="delBlog(o.id)">删除文章</button>
							</td>
						</tr>
						
					</table>
				</div>
				<!--Start table-responsive-->
			</div>
		</template>
		<template id="temp3">
			<!--Start pandel-heading-->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>
						<span>商品信息列表</span>
					</h4>
					
					<h4>
						<a href="../manage/goodsTransfer.action" class="btn btn-success">添加新商品</a>
					</h4>
				</div>
				<!--End pandel-heading-->

				<!--Start table-responsive-->
				<div class="table-responsive container-fluid">
					<table class="table table-hover">
						<tr>
							<th>#</th>
							<th>ID</th>
							<th>商品名</th>
							<th>魅力值</th>
							<th>RMB价格</th>
							<th>积分价格</th>
							<th>RMB折扣</th>
							<th>积分折扣</th>
							<th>销售量</th>
							<th>商品状态</th>
							<th>操作</th>
						</tr>
						<tr v-for="(o, index) in giftList" :style="{'color': o.state == 0 ? 'red' : '#000' }">
							<td>{{ index + 1 }}</td>
							<td>{{ o.id }}</td>		
							<td>{{ o.name }}</td>
							<td>{{ o.charm }}</td>
							<td>{{ o.price_rmb }}</td>
							<td>{{ o.price_point }}</td>
							<td>{{ o.discount_rmb }}</td>
							<td>{{ o.discount_point }}</td>
							<td>{{ o.sales }}</td>
							<td >{{ o.state }}</td>
							<td>
								<a :href="'../manage/goodsTransfer.action?id=' + o.id" class="btn btn-warning">修改</a>
							</td>
						</tr>
						
					</table>
				</div>
				<!--Start table-responsive-->
			</div>
		</template>	
		
	</body>

	<script type="text/javascript" src="../js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap.js"></script>
	<script type="text/javascript" src="../js/public.js"></script>
	<script type="text/javascript" src="../js/vue.js"></script>
	<script type="text/javascript" src="../js/vue-resource.js"></script>
	<script type="text/javascript" src="../js/vue-router.js"></script>
	<script type="text/javascript">
		<!--
		(function(Vue) {
			
			var Temp1 = Vue.extend({
				template: '#temp1',
				data: function(){
					return {
						userList: []
					}
				},
				created: function(){
					// 加载用户信息
					this.$http({
						method: "POST",
						url: "../manage/selectAllUser.action"
					}).then((resp) => {
						this.userList = resp.data;
					}).catch((resp) => {
						alert("加载用户信息失败!");
					})	
				},
				methods:{
					delUser: function(id){
						var flag = confirm("您确定冻结此账户吗？");
						if (flag) {
							// 加载用户信息
							this.$http({
								method: "POST",
								url: "../user/delete.action?userId=" + id
							}).then((resp) => {
								alert("冻结用户" + "'" + id + "'" + "成功!");
								location.reload();
							}).catch((resp) => {
								alert("冻结用户失败!");
							})	
						}
					},
					backUser:function(id){
						var flag = confirm("您确定恢复此账户吗？");
						if (flag) {
							// 恢复用户权限
							this.$http({
								method: "POST",
								url: "../manage/backUser.action?userId=" + id
							}).then((resp) => {
								alert("恢复用户" + "'" + id + "'" + "成功!");
								location.reload();
							}).catch((resp) => {
								alert("恢复用户失败!");
							})	
						}
					}
				}
			})
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
						url: "../post/getPost.action"
					}).then((resp) => {
						this.blogList = resp.data;
					}).catch((resp) => {
						alert("加载博客信息失败!");
					})	
				},
				methods:{
					delBlog: function(id){
						var flag = confirm("您确定删除此文章吗？");
						if (flag) {
							this.$http({
								method: "POST",
								url: "../post/setOff.action?postId=" + id
							}).then((resp) => {
								alert("删除文章" + "'" + id + "'" +  "成功!");
								location.reload();
							}).catch((resp) => {
								alert("删除文章失败!");
							})	
						}
					},
					backBlog: function(id){
						var flag = confirm("您确定恢复此文章吗？");
						if (flag) {
							this.$http({
								method: "POST",
								url: "../manage/backBlog.action?postId=" + id
							}).then((resp) => {
								alert("恢复文章" + "'" + id + "'" +  "成功!");
								location.reload();
							}).catch((resp) => {
								alert("恢复文章失败!");
							})	
						}
					}
				}
			})
			var Temp3 = Vue.extend({
				template: '#temp3',
				data: function(){
					return {
						giftList: []
					}
				},
				created: function(){
					this.$http({
						method: "POST",
						url: "../manage/selectAllGoodsForManage.action"
					}).then((resp) => {
						this.giftList = resp.data;
					}).catch((resp) => {
						alert("加载商品信息失败!");
					})	
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
					}
				]
			})
			
			new Vue({
				el: "#app",
				router: router,
				data: {
					list: [{
							text: "用户信息"
						},
						{
							text: "博客信息"
						},
						{
							text: "商品信息"
						}
					],
					i: 0
				},
				methods: {
					changeActive: function(index) {
						this.i = index;
					}
				}
			})
			
			
		})(Vue)
		//-->
	</script>

</html>