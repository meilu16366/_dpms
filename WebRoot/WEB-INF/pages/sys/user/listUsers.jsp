<%--
/* 
 * @Description: 用户列表
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
		<c:forEach var="user" items="${users}" varStatus="state">
			arr.push({
				_0:"${user.userid}",
				_1:"${user.sysname}",
				_2:"${user.username}",
				_3:"${user.sex}",
				_4:"<fmt:formatDate value="${user.birthday}" type="both" pattern="yyyy-MM-dd" />",
				_5:"${user.email}",
				_6:"${user.phone}",
				_7:"${user.qq}",
				_8:"<a href='javascript:void(0)' onclick='changePwd(${user.userid},\"${user.sysname}\")'>修改密码</a>&nbsp;&nbsp;&nbsp;"+("${user.disabled}"=="true"?"<a href='javascript:void(0)' onclick='use(${user.userid},this)'>停用</a>":"<a href='javascript:void(0)' onclick='use(${user.userid},this)'>启用</a>")
			});
		</c:forEach>
		var grid;
		manager = grid = $("#gridid").ligerGrid({
	      columns: [
	      { display: '用户名称', name: '_1', width:'12%',align: 'left',minWidth:75,isAllowHide:false },
	      { display: '系统登录名', name: '_2', width:'12%',align: 'left',minWidth:75,isAllowHide:false },
	      { display: '性别', name: '_3', width:'12%',align: 'center',minWidth:75,isAllowHide:false },
	      { display: '生日', name: '_4', width:'12%',align: 'center',minWidth:75,isAllowHide:false },
	      { display: '邮箱', name: '_5', width:'13%',align: 'left',minWidth:75,isAllowHide:false   },
	      { display: '电话',   name:  '_6', width:'13%',align: 'right',minWidth:75,isAllowHide:false },
	      { display: 'QQ',   name:  '_7', width:'13%',align: 'right',minWidth:75,isAllowHide:false },
	      { display: '操作',   name:  '_8', width:'12%',align: 'center',minWidth:75,isAllowHide:false }
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
			location.href = "/sys/user/delete?ids="+ids;
		}
	}
	
	function use(id,obj){
		var tex = $(obj).text();
		var img = new Image();
		var dis;
		if(tex=='启用'){
			dis = true;
			$(obj).text("停用");
		}else{
			dis = false;
			$(obj).text("启用");
		}
		img.src = "/sys/user/disable?id="+id+"&disable="+dis;
	}
	
	function edit(){
		var manager = $("#gridid").ligerGetGridManager(); 
		var rows = manager.getCheckedRows(); 
		if(rows.length==0){
			alert("请选择要修改的记录！");
		}else if(rows.length>1){
			alert("请选择一条记录进行修改！");
		}else{
			location.href = "/sys/user/find?id="+rows[0]._0;
		}
	}
	function changePwd(id,name){
		$.ligerDialog.open({
                height:620,
                width: 820,
                title : name+'修改密码',
                url: '/sys/user/changepwd?id='+id, 
                showMax: false,
                showToggle: true,
                showMin: false,
                isResize: true,
                slide: false
               
         });
		
	}
	
	
	function add(){
		location.href = "/sys/user/find";
	}
	</script>
</html>
