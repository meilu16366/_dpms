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
		<c:forEach var="listValues" items="${listValues}" varStatus="state">
			arr.push({
				_0:"${listValues.listid}",
				_1:"${listValues.code}",
				_2:"${listValues.description}",
				_3:"${listValues.value}",
				_4:"${listValues.name}",
				_5:"${listValues.orderno}",
				_6:"${listValues.remark}"
			});
		</c:forEach>
		var grid;
		manager = grid = $("#gridid").ligerGrid({
	      columns: [
	      { display: '代码类型', name: '_1', width:'16%',align: 'left',minWidth:75,isAllowHide:false },
	      { display: '类型说明', name: '_2', width:'16%',align: 'left',minWidth:75,isAllowHide:false },
	      { display: '值', name: '_3', width:'16%',align: 'left',minWidth:75,isAllowHide:false },
	      { display: '名称', name: '_4', width:'16%',align: 'left',minWidth:75,isAllowHide:false },
	      { display: '排序号', name: '_5', width:'16%',align: 'left',minWidth:75,isAllowHide:false   },
	      { display: '备注',   name:  '_6', width:'18%',align: 'left',minWidth:75,isAllowHide:false }
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
			location.href = "/sys/listValue/delete?ids="+ids;
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
			location.href = "/sys/listValue/find?id="+rows[0]._0;
		}
	}

	function add(){
		location.href = "/sys/listValue/find";
	}
	</script>
</html>
