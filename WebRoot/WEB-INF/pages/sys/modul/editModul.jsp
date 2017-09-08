<%--
/* 
 * @Description: 功能模块编辑
 * @author: ml
 * @createDate: 2017-7
 * @company: 广东振森智能科技有限公司
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld" %>

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
			 	<form name="form1" class="formular" method="post" action="/sys/modul/save" id="form1">
					<input type="hidden" name="mid" value="${modul.mid}">
					<input type="hidden" name="pid" value="${modul.pid}${pid}">
					<c:if test="${modul.pid ne 0}">
					<fieldset>
						<legend>模块编辑</legend>
						<div class="form-center">
						<div class="form-loc">
						<label>模块名称</label>
						<input value="${modul.name}" class="validate[required,maxSize[50]] text-input" type="text" name="name" id="name" />
						</div>
						<div class="form-loc">
						<label>URL</label>
						<input value="${modul.url}" class="validate[maxSize[100]] text-input" type="text" name="url" id="url" />
						</div>
						<div class="form-loc">
						<label>说明</label>
						<textarea class="validate[maxSize[500]] text-input" rows="3" cols="20" name="note" id="note">${modul.note}</textarea>
						</div>
						
						<div class="form-loc">
						<label>排序号</label>
						<input value="${modul.orderno}" class="validate[maxSize[10]] text-input" type="text" name="orderno" id="orderno" />
						</div>
						<div class="form-loc">
						<label>停用</label>
						<input value="true" <c:if test="${modul.disable}">checked="checked"</c:if> class="checkbox" type="checkbox" name="disable" id="disable" />
						</div>
						</div>
					</fieldset>
					</c:if>
					<div class="btn-sub">
					<c:if test="${modul.pid ne 0}">
					<input type="submit" class="submit" value="保存" />&nbsp;&nbsp;&nbsp;
					<c:if test="${fn:length(modul.functions)==0 && fn:length(modul.moduls)==0 && not empty modul}">
					<input type="button" class="submit" onclick="del(${modul.mid},${modul.pid})" value="删除" />&nbsp;&nbsp;&nbsp;
					</c:if>
					</c:if>
					<c:if test="${not empty modul}">
					<input type="button" class="submit" value="新建子模块" onclick="location.href='/sys/modul/find?pid=${modul.mid}'" />&nbsp;&nbsp;&nbsp;
					<input type="button" class="submit" value="新建功能" onclick="location.href='/sys/fun/find?mid=${modul.mid}'"/>
					</c:if>
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
	if('${msg}'){alert('${msg}');parent.selectNode(${pid});};
	
	$(function(){
		$('form').validationEngine();
	})
	function del(mid,pid){
		if(confirm("确认删除？")){
			parent.removeTreeItem();
			location.href = "/sys/modul/delete?mid="+mid+"&pid="+pid;
		}
	}
	var node = '${node}';
	if(node && '${issave}'){
		parent.addTreeItem([${node}]);
	}
	</script>
</html>
