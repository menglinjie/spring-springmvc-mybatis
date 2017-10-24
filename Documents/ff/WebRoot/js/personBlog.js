
function waitLoading() {
	// 添加动画样式
	$("<div>").attr("id", "all").appendTo($("body"));
	var center = $("<div>").addClass("center1").appendTo($("body"));
	$("<label>").addClass("common lab1").appendTo(center);
	$("<label>").addClass("common lab2").appendTo(center);
	$("<label>").addClass("common lab3").appendTo(center);
	$("<label>").addClass("common lab4").appendTo(center);
}
function cleanWaitLoading() {
	// 消失动画
	$(".center1").animate({
		opacity : "0"
	});
	$(".center1").hide("fast");

	// 延时清除HTML内容
	setTimeout(function() {
		$("#all").remove();
		$(".center1").remove();
	}, 500)
}

$(document)
		.ready(
				function() {

					function getQueryString(name) {
						var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
						var r = window.location.search.substr(1).match(reg);
						if (r != null)
							return unescape(r[2]);
						return null;
					}

					// 调用方法：
					// console.log(getQueryString("id")+"=============测试解析url");

					var id = getQueryString("id");

					var userId = $.cookie("userId");

					if (id != null) {
						userId = id
					}

					console.log(userId);

					function postTime(post) {
						var newElement = "<ul style='height:50px'><label><label></label>"
								+ post.time + "</label>";

						for (var i = 0; i < post.article.length; i++) {

							// 通过li的value属性传参
							var li = "<li value='" + post.article[i].id + "'>"
									+ post.article[i].title + "</li>";
							newElement += li;
						}
						newElement += "</ul>";
						console.log(newElement);

						return newElement;
					}
					waitLoading();
					// 显示时间轴的ajax请求
					$
							.ajax({
								url : '../forward/showAllForward.action',
								// url:
								// 'http://localhost:8081/ff/forward/showAllForward',
								async: false,
								type : 'get',
								dataType : 'json	',
								data : {
									'type' : 'all',
									'userId' : userId
								},
								success : function(data) {
									cleanWaitLoading();
									console.log(data);// data原数组
									console.log($.cookie("userId")
											+ "获取cookie userid");

									var po = [];// 定义一个新的数组，进行数组重组

									for (var i = 0; i < data.length; i++) {// 遍历data

										var wt = data[i].writeTime;// 2017-05-11
																	// 00:51:36
																	// 字符串
										var time = wt.substring(0, 7), flag = true;// 截取字符串到月

										for (var j = 0; j < po.length; j++) {
											if (time === po[j].time.substring(
													0, 7)) {
												po[j].article.push(data[i]);
												flag = false;
												break;
											}

										}

										if (flag) {

											po.push({
												'time' : time,
												'article' : [ data[i] ]
											});

										}

									}// 数组重组得到po[time,article]
									console.log(po);
									// 遍历新数组
									for (var i = 0; i < po.length; i++) {

										var ul_label = $(postTime(po[i]));
										console.log(po[i].time);
										$(".root").append(ul_label);
										$(".root").trigger("create");

									}

									// 将最新的博客显示到页面
									showPost(po[0].article[0].id);

									// 触发点击事件
									$("li").click(function() {
										console.log($(this).val());
										var postId = $(this).val();// 获取到当前点击的文章id

										showPost(postId);

									});

								},
								error : function(data) {
									alert("请先登录！");
								}

							})

					// 显示单个文本
					function showPost(postId) {
						console.log("------------------获取postId");
						waitLoading();
						// 查询单个博客的ajax请求
						$.ajax({
							url : '../post/showOne.action',
							// url: 'http://localhost:8081/ff/post/showOne',
							type : 'get',
							dataType : 'json',
							data : {
								'postId' : postId
							},

							success : function(pos) {
								console.log(pos + 1111111111111111111);// 测试获取的博客
								cleanWaitLoading();
								// 数据渲染
								$("#title").html(pos.title);// 标题
								$("#author").html("作者：" + pos.user.nickname);// 作者
								$("#view").html(pos.countView);
								$("#content").html(pos.content);
								console.log("test");

							},
							error : function(argument) {
								console.log("获取单个文本错误");
							}

						})

						// 请求评论数的ajax请求
						$.ajax({

							url : '../post/countPost.action',
							type : 'get',
							dataType : 'text',
							data : {
								'type' : 'comment',
								'parentId' : postId
							},

							success : function(countComment) {
								console.log("评论数" + countComment);

								if (countComment) {
									$("#comment").html(countComment);
								} else {
									$("#comment").html("0");
								}

							},
							error : function(argument) {
								console.log(postId + "测试");
								console.log("请求评论数失败");
							}

						})

						// 请求所有评论的ajax请求
						$.ajax({
							url : '../post/showComment.action',
							type : 'get',
							dataType : 'json',

							data : {
								'parentId' : postId
							},

							success : function(comments) {
								console.log(comments);

								$("#comments").html("");// 清空原有数据
								for (var i = 0; i < comments.length; i++) {
									console.log("进入comments循环渲染数据");
									var co = showCommentS(comments[i]);

									$("#comments").append(co);

								}
							},
							error : function(argument) {
								console.log("请求所有评论的ajax请求失败");
							}

						})
						function showCommentS(comment) {
							var section = "<section><label><span class='glyphicon glyphicon-user' aria-hidden='true'></span></label><p><strong>评论人:</strong>"
									+ comment.user.nickname
									+ "</p><p><strong>评论时间:</strong>"
									+ comment.writeTime
									+ "</p><p><strong>评论内容:</strong>"
									+ comment.content + "</p></section>";
							console.log(section);
							return section;
						}

						// 添加评论点击事件
						$("#addComment").click(function() {

							console.log(editor.$txt.text());
							var con = editor.$txt.text();
							// console.log(CommentCon+"========富文本获得内容");
							console.log(postId);
							var args = {
								'title' : '',
								'brief' : '',
								'content' : con,
								'type' : 'comment',
								'article' : {
									"id" : postId
								},
								"user" : {
									"id" : $.cookie("userId")
								}
							};
							console.log(JSON.stringify(args) + "评论信息");
							waitLoading();
							$.ajax({
								// url: 'http://localhost:8081/ff/post/save',
								url : "../post/save.action",
								type : 'post',
								async : true,
								dataType : 'text',
								contentType : "application/json",
								data : JSON.stringify(args),
								success : function() {
									console.log("保存成功");
									cleanWaitLoading();
									history.go(0);// 刷新页面
									// window.location.href
									// ="personBlog.html?id="+postId;//跳转页面
								},
								error : function() {
									console.log("添加评论或者博客的方法请求失败");
									alert("保存失败");
								}
							})

						});

					}

					// 添加博客点击事件
					$("#addArticle").click(function(event) {

						var Atit = $("#ArticleTitle").val();
						var Acon = editor.$txt.formatText();
						console.log(Atit + "=========标题");
						console.log(Acon + "=========内容");

						var args = {
							'title' : Atit,
							'brief' : '',
							'content' : Acon,
							'type' : 'article',
							"user" : {
								"id" : userId
							}
						};
						console.log(JSON.stringify(args) + "博客信息");

						$.ajax({
							// url: 'http://localhost:8081/ff/post/save',
							url : "../post/save.action",
							type : 'post',

							dataType : 'text',
							contentType : "application/json",
							data : JSON.stringify(args),
							success : function() {
								console.log("保存成功");
								alert("保存成功");
								 window.location.href
								 ="personBlog.html";//跳转页面
							},
							error : function() {
								console.log("添加评论或者博客的方法请求失败");
								alert("保存失败");
							}
						})

					});

					// 请求删除博客方法
					function removePost(postId) {

						$
								.ajax({
									url : '../post/setOff.action',
									type : 'delete',
									dataType : 'json',
									data : {
										'postId' : postId
									},
									async : true,
									success : function(argument) {
										alert("删除成功");
										window.location.href = "../html/personBlog.html";
									},
									error : function(argument) {
										console.log("请求删除博客方法请求失败");
										alert("删除失败");
									}
								})

					}

				});