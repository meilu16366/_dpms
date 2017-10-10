
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
			    <label>日期</label>
			    <input type="text" style="width: 75px;" readonly="readonly" class="form-control input-sm input-time" name="month" id="month" value="${month}">
			</div>
			<button type="button" onclick="ajaxcapChart()" class="btn btn-default btn-sm search-bnt da-searchcolor">查询</button>
			</div>
		</div>
		<div id="capchart" style="width: 100%;height: 600px;" ></div>
	</form>
	</div>
	</body>
	<script src="/static/js/dealWith/dealWith.js"></script>
	<script src="/static/js/dealWith/pagination.js"></script>
	<script type="text/javascript" src="/static/js/echarts/echarts.min.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script>

//日负荷曲线
var mychart;
$(function(){
	ajaxcapChart();
})

function ajaxcapChart(){
    $.ajax({
        type: "post",
        url: "/realdata/home/monthcap",
        dataType: "json", //返回数据形式为json
        data: {month:$('#month').val()},
        success: capChart
    })
}


function capChart (data){
	var xdata = data[0];
	var ydata = data[1];
	var option = {

			    legend: {
			        icon: 'rect',
			        itemWidth: 14,
			        itemHeight: 12,
			        itemGap: 13,
			        formatter:function(name){
			            return name+"(kWh)";
			        },
			        data: ['发电量'],
			        right: '4%',
			        top:20,
			        textStyle: {
			            fontSize: 12,
			            color: '#000'
			        }
			    },
	    color: ['#5986c7'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	    	top :'10%',
	        left: '3%',
	        right: '3%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data :xdata,
	            axisTick: {
	                alignWithLabel: true
	            },
	            
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
			        splitLine: {
			            lineStyle: {
			                color: '#d2d2d2',
			                type:'dashed'
			            }
			        }
	        }
	    ],
	    series : [
	        {
	            name:'发电量',
	            type:'bar',
	            barWidth: '60%',
	            barWidth:15,
	            data:ydata
	        }
	    ]
	};
	if(!mychart){
		mychart = echarts.init(document.getElementById('capchart'));
	}
	mychart.setOption(option);
}
	
$("#month").datetimepicker({
	format: 'yyyy-mm',//时间显示样式
	autoclose: true,//自动关闭
	todayBtn: true,//当天时间按钮显示
	language: 'zh-CN',//语言设置，默认为英文
	startDate: "2015-01-01",//开始时间设置
	startView:3,
	minView:3
});

	</script>
</html>
