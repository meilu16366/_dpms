<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-cn">
	<head>
	<meta charset="utf-8" />
	<title>分布式光伏电站监控系统</title>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link type="text/css" href="/static/css/login.css" rel="stylesheet" />
	</head>
	<body>
		<div class="bg">			
		</div>		<header class="nav">
			
		</header>	
			<div class="login">
				<div class="content login_content ">
			 <div class="login_box">
			 	<form class="login_form" id="validationLoginForm" method="post" action="/login">
			 		<legend class="login_title">用户登录 </legend>
			 		<div class="login_p"> 
			 			<span>用户名</span>
			 			<input type="text" name="username" id="username" class="form-control login_input" placeholder="用户名"  />
			 		</div>
			 		<div class="login_p"> 
			 			<span>密 &nbsp;&nbsp;码</span>
			 			<input type="password" name="password" id="password" class="form-control login_input" placeholder="请输入密码"  />
			 		</div>
			 		<div style="text-align: center;margin-top: -15px;"><span id="loginTip" style="color:#A94442">${message}</span></div>
			 		<div class="login_footer">
			 			<div class="login_tools">
			 				<div class="input_box">
			 					<input value="true" type="checkbox" id="rememberMe" value="true" class="input_checkbox" />		 				
			 					<label class="input_info">记住密码</label>
			 				</div>
			 			</div>
			 			<div class="login_btn">
			 				<button type="button" onclick="commit()" name="submits" class="login_btn" />登录</button>
			 			</div>
			 		</div>
			 	</form>
			 </div>
			 </div>
			</div>
			<footer class="footer text" style="padding-top: 0;">
				<div><p style="margin-top: 20px;">Copyright &copy; 2017 <a href="">xxx </a> All rights reserved.
				</p>
				</div>
			</footer>
	</body>
	<script src="/static/js/jquery-1.9.1.min.js"></script>
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

