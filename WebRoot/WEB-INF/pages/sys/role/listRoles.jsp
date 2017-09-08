<%--
/* 
 * @Description: 角色列表
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
	<link rel="stylesheet" type="text/css" href="/sys/js/ligerUI/skins/Aqua/css/ligerui-all.css">
	<link rel="stylesheet" type="text/css" href="/sys/css/main.css">
	</head>
	<body>
	<form action="">
	<div class="search-bar">
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
		<c:forEach var="role" items="${roles}" varStatus="state">
			arr.push({
				_1:${role.rid},
				_2:'${role.rcode}',
				_3:'${role.rname}',
				_4:'${role.duty}',
				_5:'<a href="/sys/role/addFun?rid=${role.rid}" >角色功能权限</a>&nbsp;&nbsp;&nbsp;<a href="/sys/role/editUsers?id=${role.rid}" >角色用户</a>'
			});
		</c:forEach>
		var grid;
		manager = grid = $("#gridid").ligerGrid({
	      columns: [
	      { display: '角色编号', name: '_1', width:'15%',align: 'center',minWidth:75,isAllowHide:false },
	      { display: '角色编码', name: '_2', width:'15%',align: 'center',minWidth:75,isAllowHide:false },
	      { display: '角色名称', name: '_3', width:'15%',align: 'center',minWidth:75,isAllowHide:false },
	      { display: '角色职责', name: '_4', width:'34%',align: 'left',minWidth:75,isAllowHide:false   },
	      { display: '操作',   name:  '_5', width:'20%',align: 'center',minWidth:75,isAllowHide:false }
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
				ids.push(rows[a]._1);
			}
			location.href = "/sys/role/delete?ids="+ids;
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
			location.href = "/sys/role/find?id="+rows[0]._1;
		}
		
	}
	
	function add(){
		location.href = "/sys/role/find";
	}
	</script>
</html>
