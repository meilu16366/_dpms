<%--
/* 
 * @Description: 修改密码
 * @author: ml
 * @createDate: 2017-7
 * @company: 广东振森智能科技有限公司
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<!DOCTYPE html>
<html>
	<head>
	<title></title>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="/sys/css/base.css">
	<link rel="stylesheet" type="text/css" href="/sys/css/demo.css">
	<link rel="stylesheet" type="text/css" href="/sys/js/validation/css/validationEngine.jquery.css">
	<link rel="stylesheet" type="text/css" href="/sys/css/layout.css">
	<style type="text/css">
		.formular{min-height: 420px !important;height: 420px;width: 95% !important;}
		html,body{overflow: auto;}
	</style>
	</head>
	<body>
	<div class="main">
		<div class="inwrap">
			<div class="example">
			 	<form name="form1" class="formular" method="post" action="/sys/user/multipwd" id="form1">
			 		<input type="hidden" name="userid" value="${user.userid}">
					<fieldset>
						<legend>修改密码</legend>
	
						<label>登录密码：</label>
						<input value="" class="validate[required] text-input" type="password" name="password" id="password" />
						<label>确认密码：</label>
						<input value="" class="validate[required,equals[password]] text-input" type="password" />
						
					</fieldset>
					<div class="btn-sub">
					<input type="submit" class="submit" value="保存" />
					<input type="button" class="submit" value="关闭" onclick="dialog.close();" />
					</div>
			    </form>
			</div>
		</div>
	</div>

	</body>
	<script src="/sys/js/jquery/jquery-1.9.0.min.js"></script>
	<script src="/sys/js/ligerUI/js/ligerui.all.js"></script>
	<script src="/sys/js/validation/languages/jquery.validationEngine-zh_CN.js"></script>
	<script src="/sys/js/validation/jquery.validationEngine.js"></script>
	<script>
	var dialog = frameElement.dialog;
	if('${saved}'=='true'){
		alert("保存成功");
		dialog.close();
	}
	$(function(){
		$('form').validationEngine();
	})
	</script>
</html>
