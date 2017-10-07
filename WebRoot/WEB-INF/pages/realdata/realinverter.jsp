
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
		<link rel="stylesheet" type="text/css" href="/static/css/mstyle.css"/>
		<link rel="stylesheet" type="text/css" href="/static/js/easyUI/css/easyui.css"/>
		<script type="text/javascript" src="/static/js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="/static/js/bootstrap/bootstrap.min.js"></script>
		<style type="text/css">
		.normal-run{background-color: #1add94;}
		.normal-stop{background-color: #29cdd1;}
		.error-run{background-color: #edd75a;}
		.error-stop{background-color: #f2836b;}
		.bread{background-color: #b5b5b5;}
		.error-dci{background-color: #a28fd8;}
			html,body{
				height: 100%;
			}
			body{
				padding-top:20px;
				background-color: #e5e5e5;
			}
			.head-h{
				padding-top: 5%;
				height: 160px;
			}
			.state-h{
				height: 160px;
			}
			.state-h>.row{
				padding-top: 2%;
			}
			.last-r{
				padding-top: 1%;
			}
			ul,li{
				padding: 0;
				margin: 0;
			}
			li {
				list-style: none;
			}
			.head-num{
				font-size: 26px;
				font-weight: 500;
			}
			.head-h li {
				padding: 5%;
				font-size:16px;
				text-align: center;
			}
			.b-white{
				background-color: white;
				margin: 20px;
			}
			.r-bor{
				border-right: 2px solid #e5e5e5;
				padding: 0;
			}
			.progress{
				margin: 0;
				height: 8px;
				background-color: #e5e5e5;
				margin-top: 5px;
			}
			.datagrid-cell .progress{
				margin: 0 10% 0 10%;
				height: 7px;
			}
			.datagrid-cell{
				font-size: 13px;
			}
			
			.deg-bar{
				float: right;
			}
			.bar-state>div{
				padding: 0 3% 0 3%;
			}
			.head-h li:last-child {
				padding: 0;
				padding-top: 1%;
			}
			.circle-m{
				display: inline-block;
				height:14px;
				width:14px;
				border-radius: 7px;
				margin-bottom: -2px;
			}
			.text-center{
				text-align: right;
			}
			.searchcolor{background-color: #3ab1d5;}
			.searchcolor:focus{
				color:white;
				background-color: #3ab1d5;
			}
			.searchcolor:hover{
				color:white;
				background-color: #3cb3d7;
			}
			.replycolor{background-color: #a28fd8;}
			.replycolor:focus{
				background-color: #a28fd8;
				color: white;
			}
			.replycolor:hover{
				background-color: #a48fda;
				color: white;
			}
			.replycolor:checked{
				color: white;
			}
			.searchcolor,.replycolor{
				color: white;
				font-size: 13px;
			}
			.my-table{min-height: 560px;}
			.dataTables_length {display: none !important;}
			.datagrid-header .datagrid-cell span {
			  font-size: 13px;
			  font-weight: 700;
			}
			div[onclick]{
				cursor: pointer;
			}
			@media (max-width: 1400px){
				.head-num{
					font-size: 22px;
					font-weight: 500;
				}
				.head-h li {
					padding: 5%;
					font-size:14px;
					text-align: center;
				}
				.head-h{
					padding-top: 5%;
					height: 140px;
				}
				.state-h{
					height: 140px;
				}
			}
		</style>
	</head>
	<body>
		<div class="container-fluid b-white" style="margin-top: 0;">
			<div class="row">
				<div class="col-xs-6 ">
					<div class="row">
						<div class="col-xs-3 r-bor">
							<ul class="head-h">
								<li><span>总功率(kW)</span></li>
								<li><img src="/static/images/power.png"></li>
								<li><span class="head-num" id="acpower">0.00</span></li>
							</ul>
						</div>
						<div class="col-xs-3 r-bor">
							<ul class="head-h">
								<li><span>辐射强度(W/m²)</span></li>
								<li><img src="/static/images/radian.png"></li>
								<li><span class="head-num" id="radian">0.00</span></li>
							</ul>
						</div>
						<div class="col-xs-3 r-bor">
							<ul class="head-h">
								<li><span>日发电量(kWh)</span></li>
								<li><img src="/static/images/daycap.png"></li>
								<li><span class="head-num" id="daycap">0.00</span></li>
							</ul>
						</div>
						<div class="col-xs-3 r-bor">
							<ul class="head-h">
								<li><span>总发电量(kWh)</span></li>
								<li><img src="/static/images/totalcap.png"></li>
								<li><span class="head-num" id="totalcap">0.00</span></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-xs-6 state-h">
					<div class="row bar-state" style="height: 50%;">
						<div class="col-xs-4" onclick="clickState(1)">
							<span>正常运行</span><span class="deg-bar" id="normal_run_num">0台</span>
							<div class="progress">
								<div class="progress-bar active" id="normal_run_bar" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 0%;background-color: #1add94;">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
						<div class="col-xs-4" onclick="clickState(2)">
							<span>正常停机</span><span class="deg-bar" id="normal_stop_num">0台</span>
							<div class="progress">
								<div class="progress-bar active" id="normal_stop_bar" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 0%;background-color: #29cdd1;">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
						<div class="col-xs-4" onclick="clickState(5)">
							<span>通讯中断</span><span class="deg-bar" id="bread_num">0台</span>
							<div class="progress">
								<div class="progress-bar active" id="bread_bar" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 0%;background-color: #b5b5b5;">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
					</div>
					<div class="row last-r bar-state" style="height: 50%;">
						<div class="col-xs-4" onclick="clickState(3)">
							<span>告警运行</span><span class="deg-bar" id="error_run_num">0台</span>
							<div class="progress">
								<div class="progress-bar active" id="error_run_bar" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 0%;background-color: #edd75a;">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
						<div class="col-xs-4" onclick="clickState(4)">
							<span>故障停机</span><span class="deg-bar" id="error_stop_num">0台</span>
							<div class="progress">
								<div class="progress-bar active" id="error_stop_bar" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 0%;background-color: #f2836b;">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
						<div class="col-xs-4" onclick="clickState(6)">
							<span>支路异常</span><span class="deg-bar" id="error_dci_num">0台</span>
							<div class="progress">
								<div class="progress-bar active" id="error_dci_bar" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 0%;background-color: #a28fd8;">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid b-white" >
			<form action="" class="form-inline">
				<div class="row">
					<div class="col-xs-12 search-bar">
						<input type="hidden" name="state" id="state">
						<div class="form-group">
							<label>逆变器名称</label>
							<input type="text" class="form-control input-text" id="name" name="name">
						</div>
						<div class="form-group">
							<label>位置</label>
							<select class="form-control input-select" id="place" name="place">
								<option value=""></option>
								<c:forEach var="place" items="${places}" varStatus="states">
								<option value="${place}">${place}</option>
								</c:forEach>
							</select>
						</div>
						<button type="button" onclick="search()" class="btn btn-default search-bnt searchcolor">查询</button>
						<button type="button" onclick="replay()" class="btn btn-default search-bnt replycolor">还原</button>
					</div>
				</div>
				<div class="my-table" >
					<table id="datagrid" ></table>
				</div>
				<div class="row">
					<div class="col-xs-12" style="border-bottom: 2px solid #e5e5e5;padding: 0 5px 0 5px;margin-top: 20px;"></div>
				</div>
				<div class="row">
					<div class="col-xs-8" style="padding-top: 25px;">
						<span>状态：&nbsp;&nbsp;</span>
						<span>正常运行&nbsp;&nbsp;</span><span class="circle-m normal-run"></span>
						<span>&nbsp;正常停机&nbsp;&nbsp;</span><span class="circle-m normal-stop"></span>
						<span>&nbsp;告警运行&nbsp;&nbsp;</span><span class="circle-m error-run"></span>
						<span>&nbsp;故障停机&nbsp;&nbsp;</span><span class="circle-m error-stop"></span>
						<span>&nbsp;通讯中断&nbsp;&nbsp;</span><span class="circle-m bread"></span>
						<!-- <span>&nbsp;支路异常&nbsp;&nbsp;</span><span class="circle-m error-dci"></span> -->
					</div>
					<div id="next-page" class="col-xs-4"></div>
				</div>
			</form>
		</div>
	</body>
	<script type="text/javascript" src="/static/js/easyUI/jquery.easyui.min.js"></script>
	<script src="/static/js/dealWith/dealWith.js"></script>
	<script src="/static/js/dealWith/pagination.js"></script>
	<script src="/static/js/realdata/realinverter.js"></script>
	<script>
	function replay(){
		$("#name").val('');
		$("#place").val('');
		$("#state").val('');
		search();
	}
	function loadBar(value,p,color){
		var width = (+value)*100/(+p);
		var loadbars = '<span>'+num2(value)+'</span><div class="progress">'+
		'<div class="progress-bar active" role="progressbar" '+
		'aria-valuenow="45" aria-valuemin="0" '+
		'aria-valuemax="100" style="width: '+(width>100?100:width)+'%;background-color: '+color+';">'+
			'<span class="sr-only"></span>'+
		'</div>'+
	    '</div>';
		return loadbars;
	}
	function effiloadBar(value,color){
		var width = value * 100;
		var loadbars = '<span>'+num2(value*100)+'</span><div class="progress">'+
		'<div class="progress-bar active" role="progressbar" '+
		'aria-valuenow="45" aria-valuemin="0" '+
		'aria-valuemax="100" style="width: '+(width>100?100:width)+'%;background-color: '+color+';">'+
			'<span class="sr-only"></span>'+
		'</div>'+
	    '</div>';
		return loadbars;
	}
	function num2(value,row,index){
		return (+value).toFixed(2);
	}
	
	$(function(){
		grid = $('#datagrid').datagrid({
			loadMsg:false,
			queryParams:qc,
			fitColumns:true,
			pagination:false,
			singleSelect:true,
			remoteSort:true,
			width:'100%',
			url:'/realdata/inverter/list',
			columns:[[
			      { title: '逆变器编号',field: '_1', width:'9%',align: 'center',halign:'center',sortable:true},
			      { title: '位置',field: '_2', width:'10%',align: 'center',halign:'center',},
			      { title: '名称', field: '_3', width:'10%',align: 'left',halign:'center'},
			      { title: '额定功率(kW)', formatter:num2,field: '_4', width:'11%',align: 'center',halign:'center',sortable:true},
			      { title: '直流功率(kW)', formatter:function(value,row,index){
			    	  return loadBar(value,row._4,"#1adc94");
			      },field:  '_5', width:'11%',align: 'center',halign:'center',sortable:true},
			      { title: '交流功率(kW)', formatter:function(value,row,index){
			    	  return loadBar(value,row._4,"#1adc94");
			      },field: '_6', width:'11%',align: 'center',halign:'center',sortable:true},
			      { title: '日发电量(kWh)',   formatter:num2,field:  '_7', width:'11%',align: 'right',halign:'center',sortable:true},
			      { title: '累计发电量(kWh)',   formatter:num2,field:  '_8', width:'12.2%',align: 'right',halign:'center',sortable:true},
			      { title: '转换效率(%)',   formatter:function(value,row,index){
			    	  return effiloadBar(value,"#a28fd8");
			      },field:  '_9', width:'8%',align: 'center',halign:'center',sortable:true},
			      { title: '状态',   field:  '_10', width:'8%',align: 'center',halign:'center',
			    	  formatter:function(value,row,index){
			    		  var statearr = ["","normal-run","normal-stop","error-run","error-stop","bread","error-dci"];
			    		  var html = "<span class='circle-m "+statearr[+value]+"'></span>";
			    		  return html;
			    	  }  
			      },
		    ]],
		    onLoadSuccess:function(data){
		    	paging(data.total);
		    }
		});
	})
	</script>
</html>
