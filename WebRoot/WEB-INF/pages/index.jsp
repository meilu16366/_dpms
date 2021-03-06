<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	
	<!-- start: Meta --> 
	<meta charset="utf-8">
	<title>光伏电站监控系统</title>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<!-- end: Meta -->
	
	<!-- start: Mobile Specific -->
	<!-- end: Mobile Specific -->
	
	<!-- start: CSS -->
	<link id="bootstrap-style" href="/static/css/bootstrap.css" rel="stylesheet">
	<link href="/static/css/bootstrap-responsive.css" rel="stylesheet">
	<link id="base-style" href="/static/css/style.css" rel="stylesheet">
	<link id="base-style-responsive" href="/static/css/style-responsive.css" rel="stylesheet">
	<link href='/static/css/google.css' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="/static/js/easyUI/css/easyui.css"/>
	
	<link rel="stylesheet" type="text/css" href="/static/css/ligerui/ligerui-all.css">
	
	<!-- end: CSS -->
	<!-- start: Favicon -->
	<!-- <link rel="shortcut icon" href="/static/img/favicon.ico"> -->
	<!-- end: Favicon -->
	<style type="text/css">
	html,body{
		height: 100%;
		width: 100%;
		min-width: 700px;
	}
	.h100{
		height: 100%;
	}
	.custom-bar{
		height: 3px;
		border:0 !important;
		background-color: #000 !important;
		margin-top: 10px;
	}
	.deg-bar{float: right;}
	.p-bar{
		padding-right: 17px;
	}
	.bar-title{
		font-size: 14px;
	}
	@media screen and (max-width: 1500px){
		.sidebar-nav > ul{
			font-size: 12px;
		}
		.f-title{
			margin: 13px 0 13px 13px;
		}
	}
	.sys-title{
		font-size: 22px;
	}
	.nav-tabs li a{
		border: 0 !important;
	}
	#sidebar-left{
		float: left
	}
	.m-nav{
		margin-top:50px;
		background: #1e2831;
		overflow-y:auto ;
		height: calc( 100% - 50px );
	}
	.timed:hover{
		background-color: rgba(0,0,0,0);
	}
	</style>
		
</head>

<body>
		<!-- start: Header -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand sys-title" href="#"><span>光伏电站监控系统</span></a>
								
				<!-- start: Header Menu -->
				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">
						<li>
							<a id="time" class="btn dropdown-toggle timed"></a>
						</li>
						<!-- start: Notifications Dropdown -->
						<li class="dropdown hidden-phone">
							<a class="btn dropdown-toggle" href="#" onclick="swichPoint()">
								<i class="halflings-icon white fullscreen"></i>
							</a>
						</li>
						<li class="dropdown hidden-phone">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#" onclick="toevent()">
								<i class="icon-bell"></i>
								<span class="badge red" id="eventcount">0</span>
							</a>
						</li>
						
						
						<!-- start: User Dropdown -->
						<li class="dropdown">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#" style="padding: 15px 10px !important;">
								<i class="halflings-icon white user"></i>${LOGIN_USER.sysname}
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-title">
 									<span>设置</span>
								</li>
								<li><a href="javascript:void(0);" onclick="toConf()"><i class="halflings-icon user"></i>修改密码</a></li>
								<li><a href="/logout"><i class="halflings-icon off"></i>退出</a></li>
							</ul>
						</li>
						<!-- end: User Dropdown -->
					</ul>
				</div>
				<!-- end: Header Menu -->
				
			</div>
		</div>
	</div>
	<!-- start: Header -->
	
		<div class="container-fluid-full h100">
		
		<div class="row-fluid h100">
				
			<!-- start: Main Menu -->
			<div class="span2 m-nav scrollbar">
				<div class="nav-collapse sidebar-nav">
					<div class="f-title"><span>实时监测</span></div>
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="menu-click active">
							<a href="/realdata/home/page" target="content">
								<img src="/static/images/home.png" class="t-icon">
								<span class="hidden-tablet">总&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;览</span>
							</a>
						</li>
						<li class="menu-click">
							<a href="/monitor/svg/page?svgurl=bwd" target="content">
								<img src="/static/images/c-line.png" class="t-icon">
								<span class="hidden-tablet">主接线图</span>
							</a>
						</li>
						<li class="menu-click">
							<a href="/realdata/inverter/page" target="content">
								<img src="/static/images/mnt.png" class="t-icon">
								<span class="hidden-tablet">实时监控</span>
							</a>
						</li>
						<li class="menu-click">
							<a href="/da/aequip/page" target="content">
								<img src="/static/images/ero-eq.png" class="t-icon">
								<span class="hidden-tablet">异常设备</span>
								<span class="badge blue dev-num" id="error-count">0</span>
							</a>
						</li>
					</ul>
					<div class="sidebar-line"></div>
					<div class="f-title"><span>图表分析</span></div>
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="menu-click" id="eventlist">
						<a href="/da/event/page" target="content">
							<img src="/static/images/event-mn.png" class="t-icon">
							<span class="hidden-tablet">事件管理</span>
						</a>
						</li>
						<li>
							<a class="dropmenu" href="#">
								<img src="/static/images/report-d.png" class="t-icon">
								<span class="hidden-tablet">报表管理</span>
								<i class="icon-chevron-right sid-rit"></i>
							</a>
							<ul class="has-cil">
								<li class="menu-click">
									<a href="/da/inverterquota/page" target="content">
										<span class="blank-w"></span>
										<span class="">逆变器报表</span>
									</a>
								</li>
								<li class="menu-click">
									<a href="/da/psmonthquota/page" target="content">
										<span class="blank-w"></span>
										<span class="">电站生产月报表</span>
									</a>
								</li>
								<li class="menu-click">
									<a href="/da/psyearquota/page" target="content">
										<span class="blank-w"></span>
										<span class="">电站生产年报表</span>
									</a>
								</li>
							</ul>	
						</li>
						<li>
							<a class="dropmenu" href="#">
								<img src="/static/images/imgbar.png" class="t-icon">
								<span class="hidden-tablet">图表分析</span>
								<i class="icon-chevron-right sid-rit"></i>
							</a>
							<ul class="has-cil">
								<li class="menu-click">
									<a href="da/charts/capbar" target="content">
										<span class="blank-w"></span>
										<span class="">发电量柱图</span>
									</a>
								</li>
								<li class="menu-click">
									<a href="da/charts/ridian" target="content">
										<span class="blank-w"></span>
										<span class="">功率辐射曲线</span>
									</a>
								</li>
							</ul>	
						</li>
						<li>
							<a class="dropmenu" href="#">
								<img src="/static/images/p-9.png" class="t-icon">
								<span class="hidden-tablet">历史数据</span>
								<i class="icon-chevron-right sid-rit"></i>
							</a>
							<ul class="has-cil">
								<li class="menu-click">
									<a href="/da/mergephis/page" target="content">
										<span class="blank-w"></span>
										<span class="">并网点历史数据</span>
									</a>
								</li>
								<li class="menu-click">
									<a href="/da/btfhis/page" target="content">
										<span class="blank-w"></span>
										<span class="">箱变历史数据</span>
									</a>
								</li>
								<li class="menu-click">
									<a href="/da/inverterhis/page" target="content">
										<span class="blank-w"></span>
										<span class="">逆变器历史数据</span>
									</a>
								</li>
								<li class="menu-click">
									<a href="/da/elmhis/page" target="content">
										<span class="blank-w"></span>
										<span class="">电表历史数据</span>
									</a>
								</li>
								<li class="menu-click">
									<a href="/da/emihis/page" target="content">
										<span class="blank-w"></span>
										<span class="">气象仪历史数据</span>
									</a>
								</li>
							</ul>
						</li>
					</ul>
					<div class="sidebar-line"></div>
					<div class="f-title"><span>基础设置</span></div>
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="menu-click" id="userconf">
							<a href="/da/charts/conf" target="content">
								<img src="/static/images/p-10.png" class="t-icon">
								<span class="hidden-tablet">个人设置</span>
							</a>
						</li>
						<li>
							<a class="dropmenu" href="#">
								<img src="/static/images/p-11.png" class="t-icon">
								<span class="hidden-tablet">配置管理</span>
								<i class="icon-chevron-right sid-rit"></i>
							</a>
							<ul class="has-cil">
								<li class="menu-click">
									<a href="/base/mergep/page" target="content">
										<span class="blank-w"></span>
										<span class="">并网点配置</span>
									</a>
								</li>
								<li class="menu-click">
									<a href="/base/btf/page" target="content">
										<span class="blank-w"></span>
										<span class="">箱变配置</span>
									</a>
								</li>
								<li class="menu-click">
									<a href="/base/inverter/page" target="content">
										<span class="blank-w"></span>
										<span class="">逆变器配置</span>
									</a>
								</li>
								<li class="menu-click">
									<a href="/base/elm/page" target="content">
										<span class="blank-w"></span>
										<span class="">电表配置</span>
									</a>
								</li>
								<li class="menu-click">
									<a href="/base/emi/page" target="content">
										<span class="blank-w"></span>
										<span class="">气象仪配置</span>
									</a>
								</li>
							</ul>
						</li>
					</ul>
					<div class="f-title p-bar bar-title"><span>实时功率</span><span class="deg-bar" id="acpowernum">0%</span>
						<div class="progress custom-bar">
						  <div id="acpowerbar" class="bar" style="width: 0%;background-color: #4dafe3;background-image: linear-gradient(to bottom,#4dafe3,#4dafe3);"></div>
						</div>
					</div>
					<div class="f-title p-bar bar-title" style="height: 100px;"><span>逆变器效率</span><span id="inverternum" class="deg-bar">0%</span>
						<div class="progress custom-bar">
						  <div id="inverterbar" class="bar" style="width: 0%;background-color: #695bb5;background-image: linear-gradient(to bottom,#695bb5,#695bb5);"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- end: Main Menu -->
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<!-- start: Content -->
			<div class="span10 h100" style="margin: 0;padding-top: 50px;">
			<iframe src="/realdata/home/page" name="content" id="iframe" style="border: 0;width: 100%;height: 100%;">
			</iframe>

			</div>
			<!--/.fluid-container-->
	
			<!-- end: Content -->
		</div><!--/#content.span10-->
		</div><!--/fluid-row-->
		
	<div class="modal hide fade" id="myModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>Settings</h3>
		</div>
		<div class="modal-body">
			<p>Here settings can be configured...</p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">Close</a>
			<a href="#" class="btn btn-primary">Save changes</a>
		</div>
	</div>
	
	<div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<ul class="list-inline item-details">
				<li><a href="#">Admin templates</a></li>
				<li><a href="http://themescloud.org">Bootstrap themes</a></li>
			</ul>
		</div>
	</div>
	
	<div class="clearfix"></div>
	
	
	<!-- start: JavaScript-->

		<script src="/static/js/jquery-1.9.1.min.js"></script>
	<script src="/static/js/jquery-migrate-1.0.0.min.js"></script>
	
		<script src="/static/js/jquery-ui-1.10.0.custom.min.js"></script>
	
		<script src="/static/js/jquery.ui.touch-punch.js"></script>
	
		<script src="/static/js/modernizr.js"></script>
	
		<script src="/static/js/bootstrap.min.js"></script>
	
		<script src="/static/js/jquery.cookie.js"></script>
	
		<script src='/static/js/fullcalendar.min.js'></script>
	
		<script src='/static/js/jquery.dataTables.min.js'></script>

		<script src="/static/js/excanvas.js"></script>
	<script src="/static/js/jquery.flot.js"></script>
	<script src="/static/js/jquery.flot.pie.js"></script>
	<script src="/static/js/jquery.flot.stack.js"></script>
	<script src="/static/js/jquery.flot.resize.min.js"></script>
	
		<script src="/static/js/jquery.chosen.min.js"></script>
	
		<script src="/static/js/jquery.uniform.min.js"></script>
		
		<script src="/static/js/jquery.cleditor.min.js"></script>
	
		<script src="/static/js/jquery.noty.js"></script>
	
		<script src="/static/js/jquery.elfinder.min.js"></script>
	
		<script src="/static/js/jquery.raty.min.js"></script>
	
		<script src="/static/js/jquery.iphone.toggle.js"></script>
	
		<script src="/static/js/jquery.uploadify-3.1.min.js"></script>
	
		<script src="/static/js/jquery.gritter.min.js"></script>
	
		<script src="/static/js/jquery.imagesloaded.js"></script>
	
		<script src="/static/js/jquery.masonry.min.js"></script>
	
		<script src="/static/js/jquery.knob.modified.js"></script>
	
		<script src="/static/js/jquery.sparkline.min.js"></script>
	
		<script src="/static/js/counter.js"></script>
	
		<script src="/static/js/retina.js"></script>

		<script src="/static/js/jquery/jquery.nicescroll.min.js"></script>
		<script type="text/javascript" src="/static/js/easyUI/jquery.easyui.min.js"></script>
		<script src="/static/js/ligerui.all.js"></script>
		<script src="/static/js/custom.js"></script>
	<!-- end: JavaScript-->
	
</body>
</html>
