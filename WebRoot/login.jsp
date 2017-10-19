<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-cn">
	<head>
	<meta charset="utf-8" />
	<title>分布式光伏电站监控系统</title>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="/static/js/bootstrap/css/bootstrap.min.css"/>
	<style type="text/css">
		*{
			overflow: hidden;
		}
		html,body{
			height:100%;
			width:100%;
			font-family: "Microsoft YaHei";
			min-width: 1050px;
			min-height: 600px;
		}
		.b-white{
			background-color: #fdfdfd;
		}
		.b-bg{
			background: url(/static/images/login/bg.png) 0 center no-repeat;
		}
		.input-div{
			padding: 8% 18%;
		}
		.login-content{
			padding: 10% 4% 10% 4%;
		}
		
		h2{
			margin: 0;
			padding: 20px;
			border-top-left-radius:10px;
			border-top-right-radius:10px;
			background-color: rgb(246,246,246);
		}
		input{
			border-color: #6d6d6d !important;
			padding-right: 30px !important;
		}
		.footct{
			text-align: center;
			position: fixed;
			height: 10%;
			width: 100%;
			bottom: 0;
		}
		.foot-content1{
			padding-top:20px;
		}
		.head-img img{
			height: 50%;
		}
		.input-div label{
			display: block;
		    margin-bottom: -40px;
		    float: right;
		    padding-top: 5px;
		    padding-right: 7px;
		}
		.submit-ads{
			margin-left: 38%;
		}
		.btn{
			padding: 6px 30px;
		}
		@media (max-width: 1500px){
			.btn{
				padding: 6px 12px;
			}
			.submit-ads{
				margin-left: 25%;
			}
		}
	</style>
	</head>
	<body>
		<div class="container-fluid b-white" >
			<div class="row">
				<div class="col-xs-6 head-img" style="padding: 1% 5%;">
					<img width="10%" src="/static/images/login/ZS-LOGO.png">
					<img width="50%" class="" src="/static/images/login/name.png">
				</div>
				<div class="col-xs-6">
				</div>
			</div>
		</div>
		<div class="container-fluid b-bg" >
			<div class="row">
				<div class="col-xs-6" style="padding-top: 7%;padding-left: 8%;">
					<img class="img-responsive" src="/static/images/login/p-1.png">
				</div>
				<div class="col-xs-4 login-content">
					<div style="background-color: white;border-radius: 10px;">
						<form action="/login" id="validationLoginForm">
						<h2 style="text-align: center;">用户登录</h2>
						<div class="input-div">
				 			<label>
				 				<img src="/static/images/login/username.png">
				 			</label>
				 			<input type="text" name="username" id="username" class="form-control" placeholder="用户名"   />
						</div>
						<div class="input-div">
							<label>
				 				<img src="/static/images/login/password.png">
				 			</label>
							<input type="password" name="password" id="password" class="form-control" placeholder="请输入密码"  />
						</div>
						<div class="input-div">
							<input value="true" type="checkbox" id="rememberMe" value="true" />		 				
				 			<span>记住密码</span>
				 			<button type="button" onclick="commit()" name="submits" class="btn btn-primary submit-ads" >登  录</button>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid b-white footct" >
			<div class="row">  
				<div class="col-xs-12 foot-content1" ><p>为了获得最佳体验效果，建议使用 IE10以上版本及 1366x768 以上分辨率。</p></div>
			</div>
			<div class="row">
				<div class="col-xs-12 foot-content2"><p style="font-size: 13px;">Copyright &copy; 2017-2017 <a href="http://www.zhensenzn.com"> 广东振森智能科技有限公司 </a> All rights reserved.</p></div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="/static/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript">
		var uname = localStorage.getItem("username");
		var pward = localStorage.getItem("password");
		if(pward){
			$('#username').val(uname);
			$('#password').val(pward);
			pward && $("#rememberMe").attr("checked","checked");
		}
		function commit(){
			if($("#rememberMe:checked").val()){
				localStorage.setItem("username", $("#username").val());
				localStorage.setItem("password", $("#password").val());
			}else{
				localStorage.setItem("username", "");
				localStorage.setItem("password", "");
			}
			$('#validationLoginForm').submit();
		}
			// 按回车键触发登录事件
		$(document).keydown(function(event) {
			var key = window.event ? event.keyCode : event.which;
			if (key == 13) {
				commit();
			}
		});
		//session过期退出
		if(parent.logout){
			parent.logout();
		}
	</script>
</html>

