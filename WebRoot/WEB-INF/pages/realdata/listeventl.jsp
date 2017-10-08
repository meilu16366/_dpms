
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
		<script type="text/javascript" src="/static/js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="/static/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="/static/js/easyUI/jquery.easyui.min.js"></script>
	</head>
	<body>
	<div class="container-fluid b-white list-content" >
	<form method="post" class="form-inline">
		<div class="row">
		<div class="col-xs-12 search-bat">
			<div class="form-group">
			    <label>设备名称</label>
			    <input type="text" class="form-control input-sm input-text" name="eqname" >
			</div>
			<div class="form-group">
				<select name="elevel" class="form-control input-sm input-select" >
					<option value="" >--事件等级--</option>
					<option value="1" >提示</option>
					<option value="2" >警告</option>
					<option value="3" >严重</option>
				</select>
			</div>
			<div class="form-group">
				<select name="eventtype" class="form-control input-sm input-select" >
					<option value="">--事件类型--</option>
					<option value="1" >操作事件</option>
					<option value="2" >遥信变位</option>
					<option value="3" >遥测越限</option>
					<option value="4" >其他</option>
				</select>
			</div>
			<div class="form-group">
			    <label>发生时间</label>
			    <input type="text" readonly="readonly" class="form-control input-sm input-time" name="stime" id="stime" value="${stime}">--
			    <input type="text" readonly="readonly" class="form-control input-sm input-time" name="etime" id="etime" value="${etime}">
			</div>
			<button type="button" onclick="search()" class="btn btn-default btn-sm search-bnt da-searchcolor">查询</button>
			<button type="button" onclick="sureAll()" class="btn btn-default search-bnt da-searchcolor">全部确认</button>
			</div>
		</div>
		<div id="ta" >
			<table id="datagrid" ></table>
		</div>
		<div class="row fixed-pager">
			<div class="col-xs-12" style="border-bottom: 2px solid #e5e5e5;padding: 0 5px 0 5px;margin-top: 20px;"></div>
			<div class="col-xs-12">
				<div id="next-page"></div>
			</div>
		</div>
	</form>
	</div>
	</body>
	<script src="/static/js/dealWith/dealWith.js"></script>
	<script src="/static/js/dealWith/pagination.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script>

	$(function(){
		grid = $('#datagrid').datagrid({
			queryParams:qc,
			fitColumns:true,
			pagination:false,
			remoteSort:true,
			width:'100%',
			url:'/da/event/list',
			columns:[[
			      { title: '设备名称', field: 'eqname', width:'10%',align: 'left',halign:'center'},
			      { title: '发生时间', field: 'ctime', width:'16%',align: 'center',halign:'center',
			    	  formatter:function(value,row,index){
				    	  	var date = new Date(value);
				    	  	return date.format("yyyy-MM-dd hh:mm:ss");
				      	 }
			      },
			      { title: '事件级别', field: 'elevel', width:'15%',align: 'center',halign:'center',
			    	  formatter:function(value,row,index){
			    	  	var des = ['','提示','警告','严重'];
			    	  	if(!value){
			    	  		return '';
			    	  	}
			    	  	return des[+value];
			      	  }
			      },
			      { title: '事件类型', field: 'eventtype', width:'15%',align: 'center',halign:'center',
			    	  formatter:function(value,row,index){
				    	  	var des = ['','操作事件','遥信变位','遥测越限','其他'];
				    	  	if(!value){
				    	  		return '';
				    	  	}
				    	  	return des[+value];
				      }
			      },
			      { title: '事件内容', field: 'describes', width:'20%',align: 'left',halign:'center'},
			      { title: '事件动作', field: 'action', width:'15%',align: 'center',halign:'center',
			    	  formatter:function(value,row,index){
				    	  	var des = ['由合到分','由分到合','执行成功','选择成功'];
				    	  	if(!value){
				    	  		return '';
				    	  	}
				    	  	return des[+value]+"&nbsp;"+(row.issoe?"soe":'');
				      }    
			      },
			      { title: '确认',    field: 'sure', width:'10%',align: 'center',halign:'center',
			    	  formatter:function(value,row,index){
				    	  	if(value){
				    	  		return "已确认";
				    	  	}else{
				    	  		return '<a href="javascript:void(0)" onclick="update('+row.id+',this)">确认</a>';
				    	  	}
				    	  	return des[+value]+"&nbsp;"+(row.issoe?"soe":'');
				      }  
			      }
		    ]],
		    onLoadSuccess:function(data){
		    	paging(data.total);
		    }
		});
	})
	function update(id,obj) {
		$.ajax({
	           type: "POST",
	           url: "/da/event/update",
	           dataType:"text",
	           data: {eid:id},
	           success: function(data){
		        	$(obj).after("已确认");
		   		  	$(obj).remove();
	           }
		})
	}
  function sureAll(){
	  if(confirm("是否全部确认？")){
		  location.href = "/da/event/sureAll"; 
	  }
	
  }
	$("#stime").datetimepicker({
	    	format: 'yyyy-mm-dd hh:ii:ss',//时间显示样式
	    	autoclose: true,//自动关闭
	    	todayBtn: true,//当天时间按钮显示
	    	language: 'zh-CN',//语言设置，默认为英文
	    	startDate: "2015-01-01",//开始时间设置
	    	startView:2,
	    	minView:1
	});
	$("#etime").datetimepicker({
    	format: 'yyyy-mm-dd hh:ii:ss',//时间显示样式
    	autoclose: true,//自动关闭
    	todayBtn: true,//当天时间按钮显示
    	language: 'zh-CN',//语言设置，默认为英文
    	startDate: "2015-01-01",//开始时间设置
    	startView:2,
    	minView:1
	});
	</script>
</html>
