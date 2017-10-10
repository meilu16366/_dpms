<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<!DOCTYPE html>
<html style="overflow: hidden;">
	<head>
	<title></title>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="/static/js/bootstrap/css/bootstrap.min.css"/>
	<script type="text/javascript" src="/static/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap/bootstrap.min.js"></script>
	<style type="text/css">
		.btn-sm{
			
		}
	</style>
	</head>
	<body>
	<div class="container-fluid">
		 	<form name="form1" class="form-inline" method="post" id="form1">
					<div class="form-group">
						<label>测点名称</label>
						<input class="form-control input-sm" readonly="readonly" type="text" value="${eqname}" >
					</div>
					<c:if test="${param.type==4||param.type==5}">
					<div class="form-group">
						<label>设置值：</label>
						<input value="" class="form-control input-sm " type="text" id="ytValue" />
					</div>
					</c:if>
					<c:if test="${param.type==0||param.type==1||param.type==2||param.type==3}">
					<div class="form-group">
					<label>操作</label>
					<select name="way" class="form-control input-sm" id="excway">
						<option value="0">分闸</option>
						<option value="1">合闸</option>
					</select>
					</div>
					</c:if>
					<div class="row" style="text-align: center;">
					<button type="button" id="choose" onclick="_choose(this)" class="btn btn-default btn-sm" >选择</button>
					<button type="button" id="excute" onclick="_excut(this)" style="display: none;" class="btn btn-default btn-sm">执行</button>
					<button type="button" class="btn btn-default btn-sm" onclick="parent.$.ligerDialog.hide();">关闭</button>
					</div>
		    </form>
			</div>
	</body>
	<script src="/static/js/ligerui.all.js"></script>
	<script>

	
	//0:开机选择，1：开机执行，2：关机选择，3：关机执行，4：遥调选择，5：遥调执行
	var urls = ["chooseopen","excutopen","chooseclose","excutclose","chooseadjust","excutadjust"];
	
	function _choose(obj){
		var url = "";
		if($("#excway").val()==1){
			url = urls[0];
		}else{
			url = urls[2];
		}
		if($("#ytValue").val()){
			url = urls[4];
		}
	    $.ajax({
	        type: "post",
	        async: false,
	        url: "/remote/"+url,
	        dataType: "json",
	        data: {_id:'${param._id}',value:$("#ytValue").val()},
	        success: function(data) {
		        alert(data[1]);
				if(data[0]){
					$(obj).hide();
					$("#excute").show();
				}
			}
	    })
	}
	function _excut(obj){
		var url = "";
		if($("#excway").val()==1){
			url = urls[1];
		}else{
			url = urls[3];
		}
		if($("#ytValue").val()){
			url = urls[5];
		}
		$.ajax({
	        type: "post",
	        async: false,
	        url: "/remote/"+url,
	        dataType: "json",
	        data: {_id:'${param._id}',value:$("#ytValue").val()},
	        success: function(data) {
		        alert(data[1]);
				if(data[0]){
					$(obj).hide();
					$("#choose").show();
				}
			}
	    })
	}
	</script>
</html>
