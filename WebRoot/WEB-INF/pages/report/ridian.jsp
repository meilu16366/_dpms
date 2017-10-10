
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
			    <input type="text" style="width: 90px;" readonly="readonly" class="form-control input-sm input-time" name="date" id="date" value="${date}">
			</div>
			<button type="button" onclick="ajaxRidian()" class="btn btn-default btn-sm search-bnt da-searchcolor">查询</button>
			</div>
		</div>
		<div id="rcharts" style="width: 100%;height: 600px;" ></div>
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
	ajaxRidian();
})
function ajaxRidian(){
    $.ajax({
        type: "post",
        url: "/realdata/home/radian",
        dataType: "json", //返回数据形式为json
        data: {date:$("#date").val()},
        success: radian
    })
	
}
function isallnull(data){
	var bol = true;
	$.each(data,function(){
		if(this!="-"){
			bol = false;
			return false;
		}
	})
	if(bol){
		data[0] = 0;
	}
	return bol;
}
function radian(data){
	var xdata = data[0];
	var ydata1 = data[1];
	var ydata2 = data[2];
	isallnull(ydata1);
	isallnull(ydata2);
	var option = {
			/* title: {
		        text: '日负荷曲线',
		        textStyle: {
		            fontWeight: 'normal',
		            fontFamily:'Microsoft YaHei',
		            fontSize: 16,
		            color: '#5f5f5f'
		        },
		        left: -5,
		        top :10
		    }, */
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            lineStyle: {
		                color: '#57617B'
		            }
		        }
		    },
		    legend: {
		        icon: 'rect',
		        itemWidth: 14,
		        itemHeight: 5,
		        itemGap: 13,
		        data: ['功率', '辐射强度'],
		        right: '4%',
		        formatter:function(name){
		        	if(name=="功率"){
		        		return name + "(kW)";
		        	}
		        	
		            return name+"(W/m²)";
		        },
		        top:20,
		        textStyle: {
		            fontSize: 12,
		            color: '#000'
		        }
		    },
		    grid: {
		    	top :'10%',
		        left: '3%',
		        right: '3%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: [{
		        type: 'category',
		        boundaryGap: false,
		       
		        data: xdata
		    }],
		    yAxis: [{
		        type: 'value',
		        name: '',
		        axisTick: {
		            show: false
		        },
		        
		        splitLine: {
		            lineStyle: {
		                color: '#d2d2d2',
		                type:'dashed'
		            }
		        }
		    },{
		        type: 'value',
		        name: '',
		        axisTick: {
		            show: false
		        },
		        
		        splitLine: {
		            show:false,
		            lineStyle: {
		                color: '#57617B'
		            }
		        }
		    }],
		    series: [{
		        name: '功率',
		        type: 'line',
		        smooth: true,
		        symbol: 'circle',
		        symbolSize: 5,
		        showSymbol: false,
		        lineStyle: {
		            normal: {
		                color:'#1ca1cd',
		                width: 2
		            }
		        },
		        areaStyle: {
		            normal: {
		                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                    offset: 0,
		                    color: 'rgba(28, 161, 205, 0.3)'
		                }, {
		                    offset: 0.8,
		                    color: 'rgba(28, 161, 205, 0)'
		                }], false),
		                shadowColor: 'rgba(0, 0, 0, 0.1)',
		                shadowBlur: 10
		            }
		        },
		        itemStyle: {
		            normal: {
		                color: 'rgb(137,189,27)',
		                borderColor: 'rgba(137,189,2,0.27)',
		                borderWidth: 12

		            }
		        },
		        data: ydata1
		    }, {
		        name: '辐射强度',
		        type: 'line',
		        smooth: true,
		        symbol: 'circle',
		        symbolSize: 5,
		        showSymbol: false,
		        yAxisIndex:1,
		        lineStyle: {
		            normal: {
		                color:'#8ad503',
		                width: 2
		            }
		        },
		        areaStyle: {
		            normal: {
		                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                    offset: 0,
		                    color: 'rgba(138, 213, 3, 0.3)'
		                }, {
		                    offset: 0.8,
		                    color: 'rgba(138, 213, 3, 0)'
		                }], false),
		                shadowColor: 'rgba(0, 0, 0, 0.1)',
		                shadowBlur: 10
		            }
		        },
		        itemStyle: {
		            normal: {
		                color: 'rgb(0,136,212)',
		                borderColor: 'rgba(0,136,212,0.2)',
		                borderWidth: 12

		            }
		        },
		        data: ydata2
		    } ]
		};
	
	if(!mychart){
		mychart = echarts.init(document.getElementById('rcharts'));
	}
	mychart.setOption(option);
}
	
	$("#date").datetimepicker({
	    	format: 'yyyy-mm-dd',//时间显示样式
	    	autoclose: true,//自动关闭
	    	todayBtn: true,//当天时间按钮显示
	    	language: 'zh-CN',//语言设置，默认为英文
	    	startDate: "2015-01-01",//开始时间设置
	    	startView:2,
	    	minView:2
	});

	</script>
</html>
