
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
			    <label>箱变名称</label>
			    <input type="text" class="form-control input-sm input-text" name="name" >
			</div>
			<div class="form-group">
			    <label>采集时间</label>
			    <input type="text" readonly="readonly" class="form-control input-sm input-time" name="stime" id="stime" value="${stime}">--
			    <input type="text" readonly="readonly" class="form-control input-sm input-time" name="etime" id="etime" value="${etime}">
			</div>
			<button type="button" onclick="checkTime()" class="btn btn-default btn-sm search-bnt da-searchcolor">查询</button>
			<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm search-bnt da-searchcolor" onclick="_exp('/da/btfhis/export');">导出</button>
			</div>
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
			url:'/da/btfhis/list',
			queryParams:{
				pageNo:qc.pageNo,
				pageSize:qc.pageSize,
				stime:$('#stime').val(),
				etime:$('#etime').val(),
			},
			frozenColumns:[[
				{ title: '箱变名称', field: 'name', width:'10%',align: 'left',halign:'center'},
			      { title: '采集时间', field: 'ctime', width:'16%',align: 'center',halign:'center',
			    	  formatter:function(value,row,index){
				    	  	var date = new Date(value);
				    	  	return date.format("yyyy-MM-dd hh:mm:ss");
				      	 }
			      },
			]],
			columns:[[
			      
			      { title: '有功功率(kW)', field: 'activePower', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '无功功率(kVar)', field: 'reactivePower', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '功率因数', field: 'cos', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'AB线电压(V)', field: 'uab', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'BC线电压(V)', field: 'ubc', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'CA线电压(V)', field: 'uca', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'A相电压(V)', field: 'ua', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'B相电压(V)', field: 'ub', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'C相电压(V)', field: 'uc', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'A相电流(A)', field: 'ia', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'B相电流(A)', field: 'ib', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'C相电流(A)', field: 'ic', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'A相有功功率(kW)', field: 'pa', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'B相有功功率(kW)', field: 'pb', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'C相有功功率(kW)', field: 'pc', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'A相无功功率(kVar)', field: 'qa', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'B相无功功率(kVar)', field: 'qb', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'C相无功功率(kVar)', field: 'qc', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'A相视在功率(kW)', field: 'sa', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'B相视在功率(kW)', field: 'sb', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'C相视在功率(kW)', field: 'sc', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '总视在功率(kW)', field: 's', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '电网频率(Hz)', field: 'gridFrequency', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '正向有功电能(kWh)', field: 'gmpPow', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '反向有功电能(kWh)', field: 'gmnPow', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '正向无功电能(kWh)', field: 'gmpReaPow', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '反向无功电能(kWh)', field: 'gmnReaPow', width:'10%',align: 'right',halign:'center',formatter:num2},
		    ]],
		    onLoadSuccess:function(data){
		    	paging(data.total);
		    }
		});
	})

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
	function checkTime(){
		var stime= $("#stime").val();
		var etime=$("#etime").val();
		var substime=stime.trim().substring(0,stime.indexOf(" "));
		var subetime= etime.trim().substring(0,etime.indexOf(" "));
		if(substime == subetime){
		    search();
		}else{
			alert("请选择同一天日期！");
		}
	}
	</script>
</html>
