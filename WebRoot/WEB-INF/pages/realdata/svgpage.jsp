<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>

<!DOCTYPE html>
<html>
	<head>
	<title></title>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="/static/css/ligerui/ligerui-all.css">
	<link rel="stylesheet" type="text/css" href="/static/css/public.css">
	<link rel="stylesheet" type="text/css" href="/static/css/halflings.css">
	<script src="/static/js/jquery-1.9.1.min.js"></script>
	<script src="/static/js/ligerui.all.js"></script>
	<style type="text/css">
		.position-div {
			position: absolute;
		    top: 0px;
		    right: 0px;
		}
		.nav-nbq {
			float: right;
			margin: 20px 30px 0 0;
		}
		li {
			display: inline-block;
			cursor: pointer;
		}
		.nbqTab:hover {
			color: #0AB5E1;
			background: #0E465F;
		}
		.nbqTab {
			min-width: 100px;
			display: inline-block;
			color: #FFFFFF;
			padding: 6px 8px;
			background: #114f6a;
		}
		.nbq-on {
			color: #0AB5E1;
			background: #0E465F;
		}
		@media only screen and (min-width:1600px) {
			.nbqTab {
				min-width: 100px;
				padding: 6px 8px;
				font-size: 14px;
			}
		}
		@media only screen and (max-width:1599px){
			.nbqTab {
				min-width: 70px;
				padding: 4px 7px;
				font-size: 12px;
			}
		}
	</style>
	</head>
	<body style="overflow: hidden;background-color: black;">

	<iframe scrolling="auto" rameborder="0" src="" style="border: 0;" name="svgs" width="100%" height="100%" id="svgdiv"></iframe>
	</body>
	<script>
	var code = '${param.nbqid}',psid='1',nbqmodel = '${param.nbqmodel}'||'${param.svgurl}';
	var num = '${param.num}', paramname = '${param.name}';
	</script>
	<script src="/static/js/svg/Concurrent.Thread.js"></script>
	<script src="/static/js/svg/snap.svg-min.js"></script>
	<script src="/static/js/svg/svg.js"></script>
</html>
