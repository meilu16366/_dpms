
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
	<form class="form-inline">
		<div class="row">
		<div class="col-xs-12 search-bat">
			<div class="form-group">
			    <label>电表名称</label>
			    <input type="text" class="form-control input-sm input-text" name="name" >
			</div>
			<button type="button" onclick="search()" class="btn btn-default btn-sm search-bnt da-searchcolor">查询</button>
			</div>
		</div>
		<table id="datagrid" ></table>
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
			fitColumns:true,
			pagination:false,
			remoteSort:true,
			width:'100%',
			url:'/base/elm/list',
			queryParams:qc,
			columns:[[
			      { title: '电表名称', field: 'name', width:'15%',align: 'left',halign:'center'},
			      { title: '位置', field: 'place', width:'15%',align: 'left',halign:'center'},
			      { title: '生产厂家', field: 'comply', width:'16%',align: 'left',halign:'center'},
			      { title: '采集编号', field: 'collectid', width:'15%',align: 'center',halign:'center'},
			      { title: '型号', field: 'models', width:'15%',align: 'left',halign:'center'},
			      { title: '类型', field: 'type', width:'15%',align: 'left',halign:'center',formatter:function(value){
			      	if(!value){
			      		return '';
			      	}
			      	var des = ['','主表','副表','线路表','站用电表','其他'];
			      	return des[value];
			      }},
			      { title: '倍率', field: 'rate', width:'15%',align: 'left',halign:'center',formatter:num2},
			      { title: '计量方式', field: 'sumway', width:'15%',align: 'left',halign:'center',formatter:function(value){
			      	if(!value){
			      		return '';
			      	}
			      	var des = ['','正向有功','反向有功',''];
			      	return des[value];
			      }},
			      { title: '备注', field: 'remark', width:'25%',align: 'left',halign:'center'},
		    ]],
		    onLoadSuccess:function(data){
		    	paging(data.total);
		    }
		});
	})

	</script>
</html>
