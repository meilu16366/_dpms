<%--
/* 
 * @Description: 系统参数列表
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
	<link rel="stylesheet" type="text/css" href="/sys/js/ligerUI/skins/Aqua/css/ligerui-all.css">
	<link rel="stylesheet" type="text/css" href="/sys/css/main.css">
	<style type="text/css">
		.search-input{
			float: left;
			padding-left: 30px;
		}
		.search-input input{
			margin: 10px;
		}
		.search-input button{
			margin-top: 9px;
		}
	</style>
	</head>
	<body>
	<form>
	<div class="search-bar">
		<div class="search-input">
			<label>参数名称</label><input type="text" name="name" value="${name}">
			<button type="submit" class="l-button">查询</button>
		</div>
		<div class="buttons">
			<button type="button" class="l-button" onclick="del();">删除</button>
			<button type="button" class="l-button" onclick="edit();">修改</button>
			<button type="button" class="l-button" onclick="add();">新建</button>
		</div>
	</div> 
	<div class="l-clear"></div>
	<div id="gridid" ></div>
	</form>
	</body>
	<script src="/sys/js/jquery/jquery-1.9.0.min.js"></script>
	<script src="/sys/js/ligerUI/js/ligerui.all.js"></script>
	<script>
	$(function(){
		var arr = [];
		<c:forEach var="parameter" items="${params}" varStatus="state">
			arr.push({
				_0:"${parameter.paramid}",
				_1:"${parameter.name}",
				_2:"${parameter.value}",
				_3:"${parameter.option1}",
				_4:"${parameter.option2}",
				_5:"${parameter.description}",
				_6:"${parameter.remark}",
			});
		</c:forEach>
		var grid;
		manager = grid = $("#gridid").ligerGrid({
	      columns: [
	      { display: '系统参数名', name: '_1', width:'15%',align: 'left',minWidth:75,isAllowHide:false },
	      { display: '参数值',  name: '_2', width:'15%',align: 'left',minWidth:75,isAllowHide:false },
	      { display: '选项1',  name: '_3', width:'15%',align: 'center',minWidth:75,isAllowHide:false },
	      { display: '选项2',  name: '_4', width:'15%',align: 'center',minWidth:75,isAllowHide:false },
	      { display: '描述', 	  name: '_5', width:'19%',align: 'left',minWidth:75,isAllowHide:false   },
	      { display: '备注',   name:  '_6', width:'20%',align: 'right',minWidth:75,isAllowHide:false }
	      ],
	      checkbox:true,
	      usePager:true,
	      frozenCheckbox:false,
	      data:{
				Rows : arr 
				}
	 	  });
	})
	
	function del(){
		var manager = $("#gridid").ligerGetGridManager(); 
		var rows = manager.getCheckedRows();
		if(rows.length==0){
			alert("请选择要删除的记录！");
		}else if(confirm("确认删除？")){
			var ids = [];
			for(var a=0;a<rows.length;a++){
				ids.push(rows[a]._0);
			}
			location.href = "/sys/param/delete?ids="+ids;
		}
	}
	

	
	function edit(){
		var manager = $("#gridid").ligerGetGridManager(); 
		var rows = manager.getCheckedRows(); 
		if(rows.length==0){
			alert("请选择要修改的记录！");
		}else if(rows.length>1){
			alert("请选择一条记录进行修改！");
		}else{
			location.href = "/sys/param/find?id="+rows[0]._0;
		}
	}

	
	
	function add(){
		location.href = "/sys/param/find";
	}
	</script>
</html>
