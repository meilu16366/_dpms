<%--
/* 
 * @Description: 用户编辑
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
			 	<form name="form1" class="formular" method="post" action="/sys/user/save" id="form1">
					<input type="hidden" name="userid" value="${user.userid}">
					<input value="${user.disabled}" type="hidden" name="disabled" />
					<input value="${user.password}" type="hidden" name="password" id="password" />
					<fieldset>
						<legend>用户编辑</legend>
						<div class="form-loc">
							<label>用户名称：</label>
							<input value="${user.sysname}" class="validate[required,maxSize[50]] text-input" type="text" name="sysname" id="sysname" />
						</div>
						<div class="form-loc">
							<label>系统登录名：</label><!-- 验证说明 ajax[checkrepeat,类型bean] id 必须为bean要检查的属性名称-->
							<input value="${user.username}" checkName="系统登录名" data-oldvalue="${user.username}" class="validate[required,ajax[checkrepeat,User],maxSize[50]] text-input" type="text" name="username" id="username" />
						</div>
						<div class="form-loc">
							<label>性别：</label>
							<select name="sex" id="sex">
								<option value="">选择</option>
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
						<div class="form-loc">
							<label>生日：</label>
							<input value="<fmt:formatDate value="${user.birthday}" type="both" pattern="yyyy-MM-dd" />" class="text-input" readonly="readonly"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,minDate:'2014-01-01'})" 
							type="text" name="birthday" id="birthday" />
						</div>
						<div class="form-loc">
							<label>邮箱：</label>
							<input value="${user.email}" class="validate[custom[email],maxSize[100]] text-input" type="text" name="email" id="email" />
						</div>
						<div class="form-loc">
							<label>QQ：</label>
							<input value="${user.qq}" class="validate[custom[integer],maxSize[100],min[0]] text-input" type="text" name="qq" id="qq" />
						</div>
						<div class="form-loc">
							<label>电话：</label>
							<input value="${user.phone}" class="validate[custom[phone],maxSize[20]] text-input" type="text" name="phone" id="phone" />
						</div>
					</fieldset>
					<div class="btn-sub">
					<input type="submit" class="submit" value="保存" />
					<input type="button" class="submit" value="返回" onclick="location.href='/sys/user/list'" />
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
