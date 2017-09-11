
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
		<script type="text/javascript" src="/static/js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="/static/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="/static/js/easyUI/jquery.easyui.min.js"></script>
	</head>
	<body style="background: #F4F4F4">
	<form action="/user/psinfo/list" method="post" class="form-inline">
		<div style="padding: 10px;">
			<div class="form-group">
			    <label>逆变器名称</label>
			    <input type="text" class="form-control input-sm" name="psname" value="${psname}">
			</div>
			<div class="form-group">
				<label>统计方式</label>
				<select name="selWay" class="form-control input-sm" onclick="chanw(this.value)">
					<option value="day" >按日</option>
					<option value="month" >按月</option>
					<option value="year" >按年</option>
				</select>
			</div>
			<div class="form-group _datas" id="days" >
				<label>日期</label>
				<input type="text" id="date" readonly="readonly" class="form-control input-sm" name="date"
				 value="<fmt:formatDate value='<%=org.apache.commons.lang.time.DateUtils.addDays(new java.util.Date(), -1)%>' type="both" pattern='yyyy-MM-dd' />">
			</div>
			<div class="form-group _datas" style="display: none;" id="months">
				<label>年月</label>
				<input type="text" id="month" readonly="readonly" class="form-control input-sm" name="month"
				 value="<fmt:formatDate value='<%=new java.util.Date()%>' type="both" pattern='yyyy-MM' />">
			</div>
			<div class="form-group _datas" style="display: none;" id="years">
				<label>年份</label>
				<input type="text" id="year" readonly="readonly" class="form-control input-sm" name="year"
				 value="<fmt:formatDate value='<%=new java.util.Date()%>' type="both" pattern='yyyy' />">
				
			</div>
			 <button type="button" onclick="search()" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-search"></span> 查询</button>
			<div class="pull-right">
				<button type="button" class="btn btn-default btn-sm" onclick="add('/user/psinfo/find');"><span class="glyphicon glyphicon-collapse-down"></span> 新建</button>
				<button type="button" class="btn btn-default btn-sm" onclick="edit('/user/psinfo/find');"><span class="glyphicon glyphicon-edit"></span> 修改</button>
				<button type="button" class="btn btn-default btn-sm" onclick="del('/user/psinfo/delete');"><span class="glyphicon glyphicon-trash"></span> 删除</button>
			</div>
		</div>
		<div class="l-clear"></div>
		<div id="ta" >
		<table id="datagrid" >
		</table>
		</div>	
		<div id="next-page">
			
		</div>
	</form>
	</body>
	<script src="/static/js/dealWith/dealWith.js"></script>
	<script src="/static/js/dealWith/pagination.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script>
	function chanw(val){
		$("._datas").hide();
		$("#"+val+"s").show();
	}
	$(function(){
		grid = $('#datagrid').datagrid({
			queryParams:qc,
			fitColumns:true,
			pagination:false,
			remoteSort:true,
			width:'100%',
			url:'/da/quota/list',
			columns:[[
			      { title: '逆变器名称',field: '_1', width:'10%',align: 'left',halign:'center',sortable:true},
			      { title: '发电量(kWh)',field: '_3', width:'15%',align: 'right',halign:'center',},
			      { title: '转换效率(%)', field: '_4', width:'15%',align: 'right',halign:'center'},
			      { title: '满发小时数(h)', field: '_5', width:'15%',align: 'right',halign:'center'},
			      { title: '最大直流功率(kW)', field:  '_6', width:'15%',align: 'right',halign:'center'},
			      { title: '最大交流功率(kW)', field: '_7', width:'15%',align: 'right',halign:'center'},
			      { title: '平均温度(℃)',   field:  '_8', width:'15%',align: 'right',halign:'center'}
		    ]],
		    onLoadSuccess:function(data){
		    	paging(data.total);
		    }
		});
	})
	$("#date").datetimepicker({
	    	format: 'yyyy-mm-dd',//时间显示样式
	    	autoclose: true,//自动关闭
	    	todayBtn: true,//当天时间按钮显示
	    	language: 'zh-CN',//语言设置，默认为英文
	    	startDate: "2015-01-01",//开始时间设置
	    	startView:2,
	    	minView:2
	});
	$("#month").datetimepicker({
    	format: 'yyyy-mm',//时间显示样式
    	autoclose: true,//自动关闭
    	todayBtn: true,//当天时间按钮显示
    	language: 'zh-CN',//语言设置，默认为英文
    	startDate: "2015-01-01",//开始时间设置
    	startView:3,
    	minView:3
	});
	$("#year").datetimepicker({
    	format: 'yyyy',//时间显示样式
    	autoclose: true,//自动关闭
    	todayBtn: true,//当天时间按钮显示
    	language: 'zh-CN',//语言设置，默认为英文
    	startDate: "2015-01-01",//开始时间设置
    	startView:4,
    	minView:4
	});
	</script>
</html>
