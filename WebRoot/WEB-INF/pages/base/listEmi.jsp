
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
			    <label>环境监测仪名称</label>
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
			url:'/base/emi/list',
			queryParams:qc,
			columns:[[
			      { title: '环境监测仪名称', field: 'name', width:'11%',align: 'left',halign:'center'},
			      { title: '位置', field: 'place', width:'11%',align: 'left',halign:'center'},
			      { title: '生产厂家', field: 'comply', width:'11%',align: 'left',halign:'center'},
			      { title: '安装方式', field: 'inittype', width:'11%',align: 'left',halign:'center',formatter:function(value){
			      	if(value==1){
			      		return "水平";
			      	}else if(value==2){
			      		return '倾斜';
			      	}else{
			      		return "";
			      	}
			      }},
			      { title: '安装角度', field: 'angle', width:'10.5%',align: 'left',halign:'center',formatter:num2},
			      { title: '采集编号', field: 'collectid', width:'7%',align: 'center',halign:'center'},
			      { title: '型号', field: 'models', width:'11%',align: 'left',halign:'center'},
			      { title: '是否主气象仪', field: 'mains', width:'11%',align: 'center',halign:'center',formatter:function(value){
			      	return value?'是':'否';
			      }},
			      { title: '备注', field: 'remark', width:'18%',align: 'left',halign:'center'},
		    ]],
		    onLoadSuccess:function(data){
		    	paging(data.total);
		    }
		});
	})

	</script>
</html>
