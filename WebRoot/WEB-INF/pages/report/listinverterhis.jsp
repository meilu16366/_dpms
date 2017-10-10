
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
			    <label>逆变器名称</label>
			    <input type="text" class="form-control input-sm input-text" name="name" >
			</div>
			<div class="form-group">
			    <label>采集时间</label>
			    <input type="text" readonly="readonly" class="form-control input-sm input-time" name="stime" id="stime" value="${stime}">--
			    <input type="text" readonly="readonly" class="form-control input-sm input-time" name="etime" id="etime" value="${etime}">
			</div>
			<button type="button" onclick="checkTime()" class="btn btn-default btn-sm search-bnt da-searchcolor">查询</button>
			<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm search-bnt da-searchcolor" onclick="_exp('/da/inverterhis/export');">导出</button>
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
			url:'/da/inverterhis/list',
			queryParams:{
				pageNo:qc.pageNo,
				pageSize:qc.pageSize,
				stime:$('#stime').val(),
				etime:$('#etime').val(),
			},
			frozenColumns:[[
				 { title: '逆变器名称', field: 'name', width:'10%',align: 'left',halign:'center'},
			      { title: '采集时间', field: 'ctime', width:'16%',align: 'center',halign:'center',
			    	  formatter:function(value,row,index){
				    	  	var date = new Date(value);
				    	  	return date.format("yyyy-MM-dd hh:mm:ss");
				      	 }
			      },
			]],
			columns:[[
			      { title: '总发电量(kWh)', field: 'totalCapacity', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '日发电量(KWh)', field: 'dayCapacity', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '直流功率(KWh)', field: 'dcPower', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '交流功率(kW)', field: 'acPower', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'AB线电压(V)', field: 'uab', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'BC线电压(V)', field: 'ubc', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'CA线电压(V)', field: 'uca', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'A相电流(A)', field: 'ia', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'B相电流(A)', field: 'ib', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: 'C相电流(A)', field: 'ic', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '电网频率(Hz)', field: 'gridFrequency', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '功率因素', field: 'powerFactor', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '逆变器温度(℃)', field: 'invertorTemp', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '无功功率(kW)', field: 'reactivePower', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电压1(V)', field: 'dcu1', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电压2(V)', field: 'dcu2', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电压3(V)', field: 'dcu3', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电压4(V)', field: 'dcu4', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电压5(V)', field: 'dcu5', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电压6(V)', field: 'dcu6', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电压7(V)', field: 'dcu7', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电压8(V)', field: 'dcu8', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电流1(V)', field: 'dci1', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电流2(V)', field: 'dci2', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电流3(V)', field: 'dci3', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电流4(V)', field: 'dci4', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电流5(V)', field: 'dci5', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电流6(V)', field: 'dci6', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电流7(V)', field: 'dci7', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '支路电流8(V)', field: 'dci8', width:'10%',align: 'right',halign:'center',formatter:num2},
			      { title: '转换效率(%)', field: 'efficient', width:'10%',align: 'right',halign:'center',formatter:num2}
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
