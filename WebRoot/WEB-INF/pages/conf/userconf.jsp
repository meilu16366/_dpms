
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
	<title></title>
		<meta name="content-type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="/static/js/bootstrap/css/bootstrap.min.css"/>
		<link href="/static/js/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/static/js/easyUI/css/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="/static/css/mstyle.css"/>
		<link rel="stylesheet" type="text/css" href="/static/js/validation/css/validationEngine.jquery.css"/>
		<script type="text/javascript" src="/static/js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="/static/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="/static/js/easyUI/jquery.easyui.min.js"></script>
		<style type="text/css">
			.user-conf{
				padding-left: 12%;
				padding-right: 12%;
			}
			.search-bat>div{
				margin: 30px;
			}
			.input-text{
				width: auto !important;
			}
			label{
			text-align: right;
			padding-top: 7px;
			}
		</style>
	</head>
	<body>
	<div class="container-fluid b-white list-content" >
	<form class="form-inline" action="/da/password/multipwd">
		<h3 style="text-align: center;">修改密码</h3>
		<div class="row user-conf" >
		<div class="col-xs-12 search-bat">
			
			<div class="row">
			    <label class="col-xs-5">原密码</label>
			    <div class="col-xs-7">
			    <input type="password" class="validate[required,ajax[checkpass],maxSize[18],min[6]] form-control" name="oldpass" id="pass" >
			    </div>
			</div>
			
			<div class="row">
			    <label class="col-xs-5">新密码</label>
			    <div class="col-xs-7">
			    <input type="password" class="validate[required,maxSize[12],min[6]] form-control" name="password" id="password" >
			    </div>
			</div>
			<div class="row">
			    <label class="col-xs-5">确认密码</label>
			    <div class="col-xs-7">
			    <input type="password" class="validate[required,equals[password]] form-control" >
				</div>
			</div>
			</div>
		</div>
		<div class="row fixed-pager">
			<div class="col-xs-12" style="border-bottom: 2px solid #e5e5e5;padding: 0 5px 0 5px;margin-top: 20px;"></div>
			<div class="col-xs-12">
				<div id="next-page" style="padding-top: 15px;text-align: center;">
					<button type="button" onclick="remeb()" class="btn btn-default btn-sm  da-searchcolor">保存</button>
				</div>
			</div>
		</div>
	</form>
	</div>
	</body>
	<script src="/static/js/dealWith/dealWith.js"></script>
	<script src="/static/js/dealWith/pagination.js"></script>
	<script src="/static/js/validation/languages/jquery.validationEngine-zh_CN.js"></script>
	<script src="/static/js/validation/jquery.validationEngine.js"></script>
	<script>
	$(function(){
		$('form').validationEngine();
	})
	function remeb(){
		if(confirm("确认修改？")){
			$("form").submit();
		}
	}
	</script>
</html>
