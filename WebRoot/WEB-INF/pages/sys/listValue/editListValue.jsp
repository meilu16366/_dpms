<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	<base href="<%=basePath%>">
	<title></title>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="/sys/css/base.css">
	<link rel="stylesheet" type="text/css" href="/sys/css/demo.css">
	<link rel="stylesheet" type="text/css" href="/sys/js/validation/css/validationEngine.jquery.css">
	<link rel="stylesheet" type="text/css" href="/sys/css/layout.css">
	<style type="text/css">

	</style>
	</head>
	<body>
	<div class="main">
		<div class="inwrap">
			<div class="example">
			 	<form name="form1" class="formular" method="post" action="/sys/listValue/save" id="form1">
					<input type="hidden" name="listid" value="${listValue.listid}">
					<fieldset>
						<legend>值列表编辑</legend>
						<div class="form-loc">
							<label>代码类型：</label>
							<input value="${listValue.code}" class="validate[required,maxSize[50]] text-input" type="text" name="code" id="code" />
						</div>
						<div class="form-loc">
							<label>类型说明：</label>
							<input value="${listValue.description}" class="validate[required,maxSize[50]] text-input" type="text" name="description" id="description" />
						</div>
						<div class="form-loc">
							<label>值：</label>
							<input value="${listValue.value}" class="validate[required,maxSize[50]] text-input" type="text" name="value" id="value" />
						</div>
						<div class="form-loc">
							<label>名称：</label>
							<input value="${listValue.name}" class="validate[required,maxSize[50]] text-input" type="text" name="name" id="name" />
						</div>
						<div class="form-loc">
							<label>排序号：</label>
							<input value="${listValue.orderno}" class="validate[custom[integer],maxSize[100],min[0]] text-input" type="text" name="orderno" id="orderno" />
						</div>
						<div class="form-loc">
							<label>备注：</label>
							<input value="${listValue.remark}" class="validate[required,maxSize[50]] text-input" type="text" name="remark" id="remark" />
						</div>
						
					</fieldset>
					<div class="btn-sub">
					<input type="submit" class="submit" value="保存" />
					<input type="button" class="submit" value="返回" onclick="location.href='/sys/listValue/list'" />
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
	<script src="/sys/js/My97DatePicker/WdatePicker.js"></script>
	<script>
	$(function(){
		$('form').validationEngine();
	});
	</script>
</html>
