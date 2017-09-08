<%--
/* 
 * @Description: 角色编辑
 * @author: ml
 * @createDate: 2017-7
 * @company: 广东振森智能科技有限公司
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
	</head>
	<body>
	<div class="main">
		<div class="inwrap">
			<div class="example">
			 	<form name="form1" class="formular" method="post" action="/sys/role/save" id="form1">
					<input type="hidden" name="rid" value="${role.rid}">
					<fieldset>
						<legend>角色编辑</legend>
						<div class="form-center">
						<div class="tips">角色编码</div>
						<input value="${role.rcode}" class="validate[required,maxSize[10]] text-input" type="text" name="rcode" id="rcode" />
						<div class="tips">角色名称</div>
						<input value="${role.rname}" class="validate[required,maxSize[50]] text-input" type="text" name="rname" id="rname" />
						<div class="tips">职责</div>
						<textarea class="validate[maxSize[200]] text-input" rows="2" cols="20" name="duty" id="duty">${role.duty}</textarea>
						</div>
					</fieldset>
					<div class="btn-sub">
					<input type="submit" class="submit" value="保存" />
					<input type="button" class="submit" value="返回" onclick="location.href='/sys/role/list'" />
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
	$(function(){
		$('form').validationEngine();
	})
	</script>
</html>
