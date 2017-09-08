<%--
/* 
 * @Description: 角色用户编辑
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
        h5{text-align: center;}
	</style>
	</head>
	<body>
	<div class="main">
		<div class="inwrap">
			<div class="example">
				<form name="form1" class="formular" method="post" id="form1">
					<input type="hidden" name="hasids" id="hasids" value="">
					<input type="hidden" name="rid" id="rid" value="${role.rid}">
					<fieldset>
						<legend>角色用户</legend>
						<div style="margin:4px;float:left;">
							<h5>用户列表</h5>
				        	<div id="listbox1"></div>
					    </div>
					    <div style="margin:4px;float:left;margin-top: 100px;" class="middle">
					        <input type="button" onclick="moveToRight()" value="&gt;" />
					        <br>
					        <input type="button" onclick="moveToLeft()"  value="&lt;" />
					    </div>
					    <div style="margin:4px;float:left;">
					    	<h5>此角色的用户</h5>
					        <div id="listbox2"></div>
					    </div>
					</fieldset>
					<div class="btn-sub">
					<input type="submit" class="submit" value="保存" />
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
		var hasUserids = [];
        $(function (){
            $("#listbox1,#listbox2").ligerListBox({
                isShowCheckBox: true,
                isMultiSelect: true,
                height: 300,
                width:220
            });
            var data1 = [],data2 = [];
            <c:forEach var="user" items="${users}" varStatus="state">
            data1.push({text:"${user.sysname}(${user.username})",id:'${user.userid}'});
            </c:forEach>
			<c:forEach var="user" items="${role.users}" varStatus="state">
			data2.push({text:"${user.sysname}(${user.username})",id:'${user.userid}'});
			hasUserids.push(${user.userid});
			</c:forEach>
			$("#hasids").val(hasUserids+"");
            liger.get("listbox1").setData(data1);
            liger.get("listbox2").setData(data2);
        });
        
        function moveToLeft(){
            var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
            var selecteds = box2.getSelectedItems();
            if (!selecteds || !selecteds.length) return;
            $.each(selecteds,function(){
            	hasUserids.splice($.inArray(+ this.id,hasUserids),1)
            })
            $("#hasids").val(hasUserids+"");
            box2.removeItems(selecteds);
            box1.addItems(selecteds);
        }
        function moveToRight(){
            var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
            var selecteds = box1.getSelectedItems();
            if (!selecteds || !selecteds.length) return;
            $.each(selecteds,function(){
            	hasUserids.push(+this.id);
            })
            $("#hasids").val(hasUserids+"");
            box1.removeItems(selecteds);
            box2.addItems(selecteds);
        }


	</script>
</html>
