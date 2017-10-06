
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
								<li><span class="head-num">0.00</span></li>
							</ul>
						</div>
						<div class="col-xs-3 r-bor">
							<ul class="head-h">
								<li><span>辐射强度(W/m²)</span></li>
								<li><img src="/static/images/radian.png"></li>
								<li><span class="head-num">0.00</span></li>
							</ul>
						</div>
						<div class="col-xs-3 r-bor">
							<ul class="head-h">
								<li><span>日发电量(kWh)</span></li>
								<li><img src="/static/images/daycap.png"></li>
								<li><span class="head-num">0.00</span></li>
							</ul>
						</div>
						<div class="col-xs-3 r-bor">
							<ul class="head-h">
								<li><span>总发电量(kWh)</span></li>
								<li><img src="/static/images/totalcap.png"></li>
								<li><span class="head-num">0.00</span></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-xs-6 state-h">
					<div class="row bar-state" style="height: 50%;">
						<div class="col-xs-4">
							<span>正常运行</span><span class="deg-bar">10台</span>
							<div class="progress">
								<div class="progress-bar active" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 10%;background-color: #1add94;">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
						<div class="col-xs-4">
							<span>正常停机</span><span class="deg-bar">10台</span>
							<div class="progress">
								<div class="progress-bar active" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 10%;background-color: #29cdd1;">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
						<div class="col-xs-4">
							<span>通讯中断</span><span class="deg-bar">10台</span>
							<div class="progress">
								<div class="progress-bar active" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 10%;background-color: #b5b5b5;">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
					</div>
					<div class="row last-r bar-state" style="height: 50%;">
						<div class="col-xs-4">
							<span>告警运行</span><span class="deg-bar">10台</span>
							<div class="progress">
								<div class="progress-bar active" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 10%;background-color: #edd75a;">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
						<div class="col-xs-4">
							<span>故障停机</span><span class="deg-bar">10台</span>
							<div class="progress">
								<div class="progress-bar active" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 10%;background-color: #f2836b;">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
						<div class="col-xs-4">
							<span>支路异常</span><span class="deg-bar">10台</span>
							<div class="progress">
								<div class="progress-bar active" role="progressbar" 
								aria-valuenow="45" aria-valuemin="0" 
								aria-valuemax="100" style="width: 10%;background-color: #a28fd8;">
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
				<div class="form-group">
					<label>逆变器名称</label>
					<input type="text" class="form-control" name="name">
				</div>
				<div class="form-group">
					<label>位置</label>
					<select class="form-control" name="place">
						<option value=""></option>
						<option value="山西">山西</option>
					</select>
				</div>
				<button type="submit" class="btn btn-default">查询</button>
				<button type="submit" class="btn btn-default">还原</button>
			</form>
		</div>
	</body>
	<script type="text/javascript" src="/static/js/easyUI/jquery.easyui.min.js"></script>
	<script src="/static/js/realdata/realinverter.js"></script>
	<script>
	
	</script>
</html>
