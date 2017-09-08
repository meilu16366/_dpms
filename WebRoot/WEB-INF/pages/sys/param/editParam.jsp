<%--
/* 
 * @Description: 系统参数编辑
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

	</style>
	</head>
	<body>
	<div class="main">
		<div class="inwrap">
			<div class="example">
			 	<form name="form1" class="formular" method="post" action="/sys/param/save" id="form1">
					<input value="${parameter.paramid}" type="hidden" name="paramid" />
					<fieldset>
						<legend>系统参数编辑</legend>
						<div class="form-loc">
							<label>参数名称：</label>
							<input value="${parameter.name}" class="validate[required,maxSize[50]] text-input" type="text" name="name" id="name" />
						</div>
						<div class="form-loc">
							<label>参数值：</label>
							<input value="${parameter.value}" class="validate[required,ajax[checkrepeat,Parameter],maxSize[200]] text-input" type="text" name="value" id="value" />
						</div>
						<div class="form-loc">
							<label>可选项1：</label>
							<input value="${parameter.option1}" class="validate[maxSize[200]] text-input" type="text" name="option1" id="option1" />
						</div>
						<div class="form-loc">
							<label>可选项2：</label>
							<input value="${parameter.option2}" class="validate[maxSize[200]] text-input" type="text" name="option2" id="option2" />
						</div>
						<div class="form-loc">
						<label>描述</label>
						<textarea class="validate[maxSize[500]] text-input" rows="3" cols="20" name="description" id="description">${parameter.description}</textarea>
						</div>
						<div class="form-loc">
						<label>备注</label>
						<textarea class="validate[maxSize[500]] text-input" rows="3" cols="20" name="remark" id="remark">${parameter.remark}</textarea>
						</div>
					</fieldset>
					<div class="btn-sub">
					<input type="submit" class="submit" value="保存" />
					<input type="button" class="submit" value="返回" onclick="location.href='/sys/param/list'" />
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
