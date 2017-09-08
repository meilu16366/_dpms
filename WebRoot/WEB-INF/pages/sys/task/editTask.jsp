<%--
/* 
 * @Description: 调度作业编辑
 * @author: ml
 * @createDate: 2017-7
 * @company: 广东振森智能科技有限公司
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>

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
			 	<form name="form1" class="formular" method="post" action="/sys/task/save" id="form1">
					<input type="hidden" name="taskid" value="${taskScheduler.taskid}">
					<fieldset>
						<legend>调度作业编辑</legend>
						<div class="form-center">
						<div class="form-loc">
						<label>作业编号</label>
						<input value="${taskScheduler.code}" class="validate[required,maxSize[10]] text-input" type="text" name="code" id="code" />
						</div>
						
						<div class="form-loc">
						<label>作业名称</label>
						<input value="${taskScheduler.name}" class="validate[required,maxSize[50]] text-input" type="text" name="name" id="name" />
						</div>
						
						<div class="form-loc">
						<label>类名</label>
						<input value="${taskScheduler.className}" class="validate[required,maxSize[100]] text-input" type="text" name="className" id="className" />
						</div>
						
						<div class="form-loc">
						<label>周期</label>
						<input value="${taskScheduler.cycle}" class="validate[required,maxSize[20],custom[tasked]] text-input" type="text" name="cycle" id="cycle" />
						</div>
						
						<div class="form-loc">
						<label>功能描述</label>
						<textarea class="validate[maxSize[500]] text-input" rows="5" cols="20" name="note" id="note">${function.note}</textarea>
						</div>
						<div class="form-loc">
						<label>停用</label>
						<input value="true" <c:if test="${taskScheduler.disabled}">checked="checked"</c:if> class="checkbox" type="checkbox" name=disabled id="disabled" />
						</div>
						</div>
					</fieldset>
					<div class="btn-sub">
					<input type="submit" class="submit" value="保存" />&nbsp;&nbsp;&nbsp;
					<input type="button" class="submit" onclick="location.href='/sys/task/list'" value="返回" />
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
	if('${msg}'){alert('${msg}');parent.selectNode(${mid});}
	$(function(){
		$('form').validationEngine();
	})
	
	function del(fid,mid){
		if(confirm("确认删除？")){
			parent.removeTreeItem();
			location.href = "/sys/fun/delete?fid="+fid+"&mid="+mid;
		}
	}
	var node = '${node}';
	if(node && '${issave}'){
		parent.addTreeItem([${node}]);
	}
	</script>
</html>
