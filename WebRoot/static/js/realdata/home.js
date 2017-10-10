
var refreshtime = 30000;
var charts = [];
$(function (){
	refresh();
})
function refresh(){
	
	ajaxRidian();
	ajaxinverterState();
	ajaxcapChart();
	totaldata();
	setTimeout(refresh, refreshtime);
}

window.onresize = function() {
	charts[0].resize();
	charts[1].resize();
	charts[2].resize();
}

//总数据
function totaldata(){
    $.ajax({
        type: "post",
        url: "/realdata/home/data",
        dataType: "json", //返回数据形式为json
        data: {},
        success: function(data){
        	$("#acpower").text(data.acpower.toFixed(2));
        	$("#daycap").text(data.daycap.toFixed(2));
        	$("#monthcap").text(data.monthcap.toFixed(2));
        	$("#totalcap").text(data.totalcap.toFixed(2));
        	$("#ridian").text(data.ridian.toFixed(2));
        	$("#ridianq").text(data.ridianq.toFixed(2));
        	
        	$("#co2").text(data.co2.toFixed(2));
        	$("#coal").text(data.coal.toFixed(2));
        	$("#so2").text(data.so2.toFixed(2));
        	$("#cox").text(data.cox.toFixed(2));
        	
        }
    })
	
	
}


//逆变器状态

function ajaxinverterState(){
    $.ajax({
        type: "post",
        url: "/realdata/inverter/data",
        dataType: "json", //返回数据形式为json
        data: {},
        success: inverterState
    })
	
	
}


function inverterState(datad){
	var total = datad.size;
	var data = datad.states;
	data.shift();
	$("#normal_run").text(data[0]);
	$("#normal_stop").text(data[1]);
	$("#error_run").text(data[2]);
	$("#error_stop").text(data[3]);
	$("#bread").text(data[4]);
	var bold = 6;
	placeHolderStyle = {
		    normal: {
		        label: {
		            show: false,
		            position: "center"
		        },
		        labelLine: {
		            show: false
		        },
		        color: "#dedede",
		        borderColor: "#dedede",
		        borderWidth: 0,
		        borderType:'dashed'
		    },
		    emphasis: {
		        color: "#dedede",
		        borderColor: "#dedede",
		        borderWidth: 0
		    }
		};

		var wd = [['90%','89%'],
		        ['75%','74%'],
		        ['60%','59%'],
		        ['45%','44%'],
		        ['30%','29%'],
		        ['15%','14%']
		        ];
		wd.reverse();
		var option = {
		    backgroundColor: '#fff',
		    legend: [{
		    	show:false,
		        orient: '',
		        icon: 'roundRect',
		        left: 'right',
		        top: 'center',
		        data: ['正常运行', '正常停机', '告警运行','故障停机','通讯中断']
		    }],
		    series: [{
		        name: '值',//通讯中断
		        type: 'pie',
		        clockWise: true, //顺时加载
		        hoverAnimation: false, //鼠标移入变大
		        radius: wd[0],
		        itemStyle: {
		            normal: {
		                color:'#91c7ae',
		                label: {show: false },
		                labelLine: {show: false},
		                borderWidth: bold,
		                borderColor: "#91c7ae"
		            }
		        },
		        //及格率数据修改
		        data: [{
		            value: data[4],
		            name: '通讯中断'
		        }, {
		            value: total-data[4],
		            name: '',
		            itemStyle: placeHolderStyle
		        }]
		    }, 
		    
		    
		    
		    {
		        name: '值',
		        type: 'pie',
		        clockWise: true, //顺时加载
		        hoverAnimation: false, //鼠标移入变大
		        radius: wd[1],
		        itemStyle: {
		            normal: {
		                color:'#d48265',
		                label: {show: false },
		                labelLine: {show: false},
		                borderWidth: bold,
		                borderColor: "#d48265"
		            }
		        },
		        //及格率数据修改
		        data: [{
		            value: data[3],
		            name: '故障停机'
		        }, {
		            value: total-data[3],
		            name: '',
		            itemStyle: placeHolderStyle
		        }]
		    },
		    
		    
		        {
		        name: '值',
		        type: 'pie',
		        clockWise: true, //顺时加载
		        hoverAnimation: false, //鼠标移入变大
		        radius: wd[2],
		        itemStyle: {
		            normal: {
		                color:'#61a0a8',
		                label: {show: false },
		                labelLine: {show: false},
		                borderWidth: bold,
		                borderColor: "#61a0a8"
		            }
		        },
		        //及格率数据修改
		        data: [{
		            value: data[2],
		            name: '告警运行'
		        }, {
		            value: total-data[2],
		            name: '',
		            itemStyle: placeHolderStyle
		        }]
		    },
		    
		    
		    {
		        name: '值',
		        type: 'pie',
		        clockWise: true, //顺时加载
		        hoverAnimation: false, //鼠标移入变大
		        radius: wd[3],
		        itemStyle: {
		            normal: {
		                color:'#406f8f',
		                label: {show: false },
		                labelLine: {show: false},
		                borderWidth: bold,
		                borderColor: "#406f8f"
		            }
		        },
		        //及格率数据修改
		        data: [{
		            value: data[1],
		            name: '正常停机'
		        }, {
		            value: total-data[1],
		            name: '',
		            itemStyle: placeHolderStyle
		        }]
		    },
		    
		        {
		        name: '值',
		        type: 'pie',
		        clockWise: true, //顺时加载
		        hoverAnimation: false, //鼠标移入变大
		        radius: wd[4],
		        itemStyle: {
		            normal: {
		                color:'#d4534f',
		                label: {show: false },
		                labelLine: {show: false},
		                borderWidth: bold,
		                borderColor: "#d4534f"
		            }
		        },
		        //及格率数据修改
		        data: [{
		            value: data[0],
		            name: '正常运行'
		        }, {
		            value: total-data[0],
		            name: '',
		            itemStyle: placeHolderStyle
		        }]
		    },
		    
		    {
		        type: 'pie',
		        color: ['#d4534f','#406f8f', "#61a0a8","#d48265","#91c7ea"],
		        data: [{
		            value: '',
		            name: '正常运行'
		        }, {
		            value: '',
		            name: '正常停机'
		        }, {
		            value: '',
		            name: '告警运行'
		        }, {
		            value: '',
		            name: '故障停机'
		        }, {
		            value: '',
		            name: '通讯中断'
		        }]
		    }]
		};
		if(!charts[0]){
			var mychart = echarts.init(document.getElementById('statecharts'));
			charts[0] = mychart;
		}
		charts[0].clear();
		charts[0].setOption(option);
}


function ajaxRidian(){
    $.ajax({
        type: "post",
        url: "/realdata/home/radian",
        dataType: "json", //返回数据形式为json
        data: {},
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
//日负荷曲线
function radian(data){
	var xdata = data[0];
	var ydata1 = data[1];
	var ydata2 = data[2];
	isallnull(ydata1);
	isallnull(ydata2);
	var option = {
			title: {
		        text: '日负荷曲线',
		        textStyle: {
		            fontWeight: 'normal',
		            fontFamily:'Microsoft YaHei',
		            fontSize: 16,
		            color: '#5f5f5f'
		        },
		        left: -5,
		        top :10
		    },
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
		    	top :'30%',
		        left: 10,
		        right: 20,
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
	
	if(!charts[1]){
		var mychart = echarts.init(document.getElementById('rcharts'));
		charts[1] = mychart;
	}
	charts[1].clear();
	charts[1].setOption(option);
}

function ajaxcapChart(){
    $.ajax({
        type: "post",
        url: "/realdata/home/monthcap",
        dataType: "json", //返回数据形式为json
        data: {},
        success: capChart
    })
}


function capChart (data){
	var xdata = data[0];
	var ydata = data[1];
	var option = {
	    title: {
			        text: '发电量统计',
			        textStyle: {
			            fontWeight: 'normal',
			            fontFamily:'Microsoft YaHei',
			            fontSize: 16,
			            color: '#5f5f5f'
			        },
			        left: -5,
			        top :10
			    },
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
	        left: '3%',
	        right: '4%',
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
	            barWidth:13,
	            data:ydata
	        }
	    ]
	};
	if(!charts[2]){
		var mychart = echarts.init(document.getElementById('capchart'));
		charts[2] = mychart;
	}
	charts[2].clear();
	charts[2].setOption(option);
}
