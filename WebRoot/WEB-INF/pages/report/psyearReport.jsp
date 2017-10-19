
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
		<style type="text/css">
		.list-contentd{padding-bottom: 20px;height: calc( 100% - 20px);}
		</style>
	</head>
	<body>
	<div class="container-fluid b-white list-contentd" >
	<form class="form-inline list-from">
		<div class="row">
			<div class="col-xs-12 search-bat">
				<div class="form-group">
					<label>年份</label>
					<input type="text" id="year" readonly="readonly" class="form-control input-sm input-text" name="year"
					 value="<fmt:formatDate value='<%=new java.util.Date()%>' type="both" pattern='yyyy' />">
				</div>
				 <button type="button" onclick="search()" class="btn btn-default btn-sm search-bnt da-searchcolor">查询</button>
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm search-bnt da-searchcolor" onclick="_exp('/da/psyearquota/exp');">导出</button>
				</div>
			</div>
		</div>
		<table id="datagrid" ></table>
	</form>
	</div>
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
		var wid = '10%';
		grid = $('#datagrid').datagrid({
			fitColumns:true,
			pagination:false,
			remoteSort:true,
			singleSelect:true,
			width:'100%',
			url:'/da/psyearquota/list',
			queryParams:{
				year:$('#year').val()
			},
			frozenColumns:[[
				 { title: '日期',field: 'ctime', align: 'center',width:wid,halign:'center',
			    	  formatter:function(value,row,index){
				    	  	var date = new Date(value);
				    	  	return date.format("yyyy-MM");
				      	 }
			      }
			]],
			columns:[[
			     
			      { title: '逆变器发电量(kWh)',field: 'nbqdaycap', align: 'right',width:wid,halign:'center',formatter:num2},
			      { title: '线路表发电量(kWh)', field: 'dbdaycap', align: 'right',width:wid,halign:'center',formatter:num2},
			      { title: '并网点发电量(KWh)', field: 'bwdaycap', align: 'right',width:wid,halign:'center',formatter:num2},
			      { title: '满发小时数(h)', field:  'hours', align: 'right',width:wid,halign:'center',formatter:num2},
			      { title: '逆变器最大出力(kW)', field: 'maxnbqpower', align: 'right',width:wid,halign:'center',formatter:num2},
			      { title: '逆变器最大出力时间',   field:  'maxnbqtime', align: 'right',width:wid,halign:'center', formatter:function(value,row,index){
			    	  	if(!value){
		    		  		return '';
		    		  	}
			    	  	var date = new Date(value);
			    	  	return date.format("yyyy-MM-dd hh:mm:ss");
			      	 }},
			      { title: '并网点最大出力(kW)',   field:  'maxbwpower', align: 'right',width:wid,halign:'center',formatter:num2},
			      { title: '并网点最大出力时间',   field:  'maxbwtime',align: 'right',width:wid,halign:'center',
			    	  formatter:function(value,row,index){
			    		  	if(!value){
			    		  		return '';
			    		  	}
				    	  	var date = new Date(value);
				    	  	return date.format("yyyy-MM-dd");
				      	 }
			      },
			      { title: '逆变器平均转换效率(%)',   field:  'avgnbqefficiency',align: 'right',width:wid,halign:'center',formatter:num2},
			      { title: '减排二氧化碳(t)',   field:  'co2',align: 'right',halign:'center',width:wid,formatter:num2},
			      { title: '节约标准煤(t)',   field:  'coal',align: 'right',halign:'center',width:wid,formatter:num2},
			      { title: '总辐射量(MJ/m²)',   field:  'totalradia',align: 'right',halign:'center',width:wid,formatter:num2}
		    ]],
		    onLoadSuccess:function(data){
		    	
		    }
		   
		});
	})
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
