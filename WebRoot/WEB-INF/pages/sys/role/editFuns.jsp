<%--
/* 
 * @Description: 角色功能编辑
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
	<link rel="stylesheet" type="text/css" href="/sys/css/base.css">
	<link rel="stylesheet" type="text/css" href="/sys/css/demo.css">
	<link rel="stylesheet" type="text/css" href="/sys/js/validation/css/validationEngine.jquery.css">
	<link rel="stylesheet" type="text/css" href="/sys/css/layout.css">
	<style type="text/css">
        .middle input {
            display: block;width:30px; margin:2px;
        }
        li[url*='modul'] > .l-body > .l-checkbox{
        	display: none;
        }
        h5{text-align: center;}
	</style>
	</head>
	<body>
	<div class="main">
		<div class="inwrap">
			<div class="example">
				<form name="form1" class="formular" method="post" id="form1">
					<input type="hidden" name="rid" id="rid" value="${rid}">
					<input type="hidden" name="fids" id="fids" value="">
					<fieldset>
						<legend>角色功能</legend>
						<div style="margin:auto;">
							<h5>功能列表</h5>
				        	<ul id="modul_tree" style="margin: auto;height: 400px;"></ul>
					    </div>
					</fieldset>
					<div class="btn-sub">
					<input type="button" class="submit" onclick="_save()" value="保存" />&nbsp;&nbsp;&nbsp;
					<input type="button" class="submit" value="返回" onclick="location.href='/sys/role/list'" />
					</div>
			    </form>
			</div>
		</div>
	</div>
	</body>
	<script src="/sys/js/jquery/jquery-1.9.0.min.js"></script>
	<script src="/sys/js/ligerUI/js/ligerui.all.js"></script>
	<script>
		if('${msg}'){alert('${msg}');}
        $(function (){
        	$('#fids').val(${fids}+"");
        
	        manager = $("#modul_tree").ligerTree({
	             data:${moduls},
	             checkbox: true,
	             idFieldName :'id',
	             slide : false,
	             parentIDFieldName :'pid',
	 			 isExpand: true,
	             onSelect:function(node){
					
	             }
	        });
	        treeManager = $("#modul_tree").ligerGetTreeManager();
        });
		function _save(){
			var nodes = manager.getChecked();
			var fids = [];
			$.each(nodes,function(){
				if(this.data.fid){
					fids.push(this.data.fid);
				}
			});
			$('#fids').val(fids+"");
			$('#form1').submit();
		}
	</script>
</html>
