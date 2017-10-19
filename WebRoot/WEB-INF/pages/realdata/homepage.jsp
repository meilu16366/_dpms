
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ page import="com.kx.frame.def.KXFrameDef" %>
<!DOCTYPE html> 
<html lang="zh-CN">
	<head>
	<title></title>
		<meta name="content-type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="/static/js/bootstrap/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="/static/css/mstyle.css"/>
		
		<script type="text/javascript" src="/static/js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="/static/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="/static/js/echarts/echarts.min.js"></script>
		<style type="text/css">
			.home-head{
				width: 100%;
				padding: 0;
			}
			li{
			list-style: none;
			}
			.home-head li{
				float: left;
				width: 16.66666%;
				list-style: none;
			}
			.home-head li img{
				float: left;
				margin: 7% 0 7% 7%;
			}
			.home-head li dl{
				float: left;
				margin: 0;
				padding-left: 10%;
			}
			dd>span{
				font-size: 26px;
				font-weight: 500;
			}
			.head-tile{
				text-align: center;
				
			}
			.head-tile span,.cut-tile span{
				font-size: 16px;
				color: #5f5f5f;
			}
			.cut-tile{
				padding: 10px 12px;
			}
			.head-p{
				padding-top: 12px;
				padding-bottom: 12px;
			}
			html,body{
				height: 100%;
			}
			body{
				padding-top:20px;
				background-color: #e5e5e5;
			}
			.home-head dd{
				height: 47px;
			}
			
			.home-head li{
				border-right: 2px solid #e5e5e5;
			}
			.none-border {
				border-right: 0 !important;
			}
			.h-char{
				height: 270px;
			}
			.c-chart{
				height: 225px;
			}
			.state-c{
				padding: 0;
			}
			.state-c span{
				display: inline-block;
				margin-left: 8%;
			}
			.state-c li{
				height: 20%;
			}
			.rect-state{
				width: 24px;
				height: 15px;
				display:inline-block;
				border-radius: 3px;
				margin-bottom: -2px;
			}
			.normal-run{
				background-color: #d4534f;
			}
			.normal-stop{
				background-color: #406f8f;
			}
			.error-run{
				background-color: #61a0a8;
			}
			.error-stop{
				background-color: #d48265;
			}
			.bread{
				background-color: #91c7ea;
			}
			.bot-h{
				height: calc( 100% - 470px );
				min-height: 370px;
			}
			.view-d{
				height: 100%;
			}
			.coals{
				padding: 0;
				height: 100%;
			}
			.coals img ,.coals dl{
				float: left;
			}
			.coals img {
				width: 22%;
				margin-top: 5%;
			}
			.coals li{
				float:none;
				clear: both;
				height: 25%;
				/* padding-bottom: 12%; */
			}
			.coalsd{
				padding: 5% 0 0 0;
			}
			.coalsd dd{
				text-align: right;
			}
			.coalsd li,	.coalsd dd{
				width: 100%;
			}
			.coalsd dl{
				width: 64%;
			}
			.ps-info{
			text-align: right;
			font-size: 16px;
			}
			.avol-head{
				display: inline-block;
				height: 8px;
				width: 8px;
				border-radius: 4px;
				margin-bottom: 1px;
				margin-right: 5px;
			}
			@media (max-width: 1400px){
				dd span{
					font-size: 20px;
				}
				dd{
					font-size: 12px;
				}
				.home-head dd{
					height: 34px;
				}
				.home-head li img{
					float: left;
					margin: 4% 0 7% 5%;
				}
				.state-c span{
					font-size: 12px;
				
				}
				.home-head li dl{
				float: left;
				margin: 0;
				padding-left: 5%;
				}
			}
		</style>
	</head>
	<body>
		<div class="container-fluid b-white" style="margin-top: 0;">
			<div class="row head-p">
				<div class="col-xs-8 head-tile"><span class="avol-head" style="border: 2px solid #14c972;"></span><span>电量数据</span></div>
				<div class="col-xs-4 head-tile"><span class="avol-head" style="border: 2px solid #f2900f;"></span><span>环境数据</span></div>
			</div>
			<div class="row">
	  			<ul class="home-head">
	  				<li>
	  				<img src="/static/images/power.png" >
	  				<dl>
	  					<dd><span id="acpower">0.00</span></dd>
	  					<dd>当前功率(kW)</dd>
	  				</dl>
	  				</li>
	  				
	  				<li>
	  				<img src="/static/images/daycap.png"  >
	  				<dl>
	  					<dd><span id="daycap">0.00</span></dd>
	  					<dd>日发电量(kWh)</dd>
	  				</dl>
	  				</li>
	  				
	  				<li>
	  				<img src="/static/images/monthcap.png"  >
	  				<dl>
	  					<dd><span id="monthcap">0.00</span></dd>
	  					<dd>月发电量(kWh)</dd>
	  				</dl>
	  				</li>
	  				
	  				<li>
	  				<img src="/static/images/totalcap.png"  >
	  				<dl>
	  					<dd><span id="totalcap">0.00</span></dd>
	  					<dd>总发电量(kWh)</dd>
	  				</dl>
	  				</li>
	  				
	  				<li>
	  				<img src="/static/images/radian.png"  >
	  				<dl>
	  					<dd><span id="ridian">0.00</span></dd>
	  					<dd>辐射强度(W/m²)</dd>
	  				</dl>
	  				</li>
	  				
<!-- 	  				<li>
	  				<img src="/static/images/radian.png"  >
	  				<dl>
	  					<dd><span id="ridianq">0.00</span></dd>
	  					<dd>日辐射量(MJ/m²)</dd>
	  				</dl>
	  				</li> -->
	  				
<!-- 	  				<li>
	  				<img src="/static/images/windy.png"  >
	  				<dl>
	  					<dd><span>0.00</span></dd>
	  					<dd>风速(m/s)</dd>
	  				</dl>
	  				</li> -->
	  				
	  				<li class="none-border">
	  				<img src="/static/images/templet.png"  >
	  				<dl>
	  					<dd><span>0.00</span></dd>
	  					<dd>环境温度(℃)</dd>
	  				</dl>
	  				</li>
	  				
	  				<li style="clear: both;width: 0;"></li>
	  				
	  			</ul>
  			</div>
  			
		</div>
		<div class="container-fluid b-white">
			<div class="row"> 
  				<div class="col-xs-7 h-char" id="rcharts">
  					
  				</div>
  				<div class="col-xs-5 h-char" style="border-left: 20px solid #e5e5e5;">
  					<div class="row">
  						<div class="col-xs-12 cut-tile">
 							<span>逆变器状态</span>
 						</div>
  					</div>
  					<div class="row c-chart" >
  						<div id="statecharts" class="col-xs-6 c-chart"></div>
  						<div class="col-xs-6 view-d">
  							<ul class="state-c view-d">
  								<li> 
  									<span class="rect-state normal-run"></span>
  									<span>正常运行</span>
  									<span style="color:#d4534f;" id="normal_run">0</span>
  									<span>台</span>
  								</li>
  								<li>
  									<span class="rect-state normal-stop"></span>
  									<span>正常停机</span>
  									<span style="color:#406f8f;" id="normal_stop">0</span>
  									<span>台</span>
  								</li>
  								<li>
  									<span class="rect-state error-run"></span>
  									<span>告警运行</span>
  									<span style="color:#61a0a8;" id="error_run">0</span>
  									<span>台</span>
  								</li>
  								<li>
  									<span class="rect-state error-stop"></span>
  									<span>故障停机</span>
  									<span style="color:#d48265;" id="error_stop">0</span>
  									<span>台</span>
  								</li>
  								<li>
  									<span class="rect-state bread"></span>
  									<span>通讯中断</span>
  									<span style="color:#91c7ea;" id="bread">0</span>
  									<span>台</span>
  								</li>
  								
  							</ul>
  						</div>
  					</div>
  				</div>
  			</div>
		</div>
		<div class="container-fluid b-white bot-h">
			<div class="row view-d" > 
				<div class="col-xs-7 view-d" >
					<div class="row view-d">
						<div class="col-xs-10 view-d" id="capchart"></div>
						<div class="col-xs-2 view-d coalsd">
							<ul class="coals">
				  				<li>
				  				<img src="/static/images/co2.png" style="margin-top: 16%;">
				  				<dl>
				  					<dd><span style="color: #f39e2f;" id="co2">0.00</span></dd>
				  					<dd>累计减排(t)</dd>
				  				</dl>
				  				<div style="clear: both;"></div>
				  				</li>
				  				
				  				<li>
				  				<img src="/static/images/coal.png" style="margin-top: 11%;">
				  				<dl>
				  					<dd><span style="color: #f67171;" id="coal">0.00</span></dd>
				  					<dd>节约标煤(t)</dd>
				  				</dl>
				  				<div style="clear: both;"></div>
				  				</li>
				  				
				  				<li>
				  				<img src="/static/images/so2.png" style="margin-top: 18%;">
				  				<dl>
				  					<dd><span style="color: #1aa2da;" id="so2">0.00</span></dd>
				  					<dd>二氧化硫(t)</dd>
				  				</dl>
				  				<div style="clear: both;"></div>
				  				</li>
				  				
				  				<li>
				  				<img src="/static/images/cos.png" style="margin-top: 13%;">
				  				<dl>
				  					<dd><span style="color: #25cbe9;" id="cox">0.00</span></dd>
				  					<dd>碳氧化物(t)</dd>
				  				</dl>
				  				<div style="clear: both;"></div>
				  				</li>
				  				
				  				
							</ul>
						</div>
					</div>
				</div>
				<div class="col-xs-5 view-d" style="border-left: 20px solid #e5e5e5;">
					<div class="row">
  						<div class="col-xs-12 cut-tile">
 							<span>电站信息</span>
 						</div>
  					</div>
  					<div class="row">
  						<div class="col-xs-12 ps-info" >
  							<span><%=KXFrameDef.PSNAME %></span>
  							<span>&nbsp;&nbsp;投产日期：</span>
  							<span><%=KXFrameDef.RUN_DATE %></span>
  						</div>
  					</div>
  					<div class="row">
  						<div class="col-xs-12">
  							<img src="/static/images/timg-4.jpg" class="img-responsive" style="margin: auto;">
  						</div>
  					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="/static/js/realdata/home.js"></script>
	<script>
	
	</script>
</html>
