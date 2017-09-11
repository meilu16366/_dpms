

$(function (){
	inverterState();
	radian();
	capChart();
})

//逆变器状态
function inverterState(){
	var total = 55;
	var data = [20,10,10,10,5];
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
		var mychart = echarts.init(document.getElementById('statecharts'));
		mychart.setOption(option);
}

//日负荷曲线
function radian(){
	var xdata = ['13:00', '13:05', '13:10', '13:15', '13:20', '13:25', '13:30', '13:35', '13:40', '13:45', '13:50', '13:55'];
	var ydata1 = [120, 110, 125, 145, 122, 165, 122, 220, 182, 191, 134, 150];
	var ydata2 = [220, 182, 191, 134, 150, 120, 110, 125, 145, 122, 165, 122];
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
		        axisLabel:{
		        	textStyle: {
		                fontSize: 13,
		                color: '#cecece'
		            }
		        },
		        axisLine: {
		            lineStyle: {
		                color: '#d7d7d7'
		            }
		        },
		        data: xdata
		    }],
		    yAxis: [{
		        type: 'value',
		        name: '',
		        axisTick: {
		            show: false
		        },
		        axisLine: {
		            lineStyle: {
		                color: '#d7d7d7'
		            }
		        },
		        axisLabel: {
		            margin: 10,
		            textStyle: {
		                fontSize: 13,
		                color: '#cecece'
		            }
		        },
		        nameTextStyle:{
		        	color: '#cecece'
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
		        axisLine: {
		            lineStyle: {
		                color: '#d7d7d7'
		            }
		        },nameTextStyle:{
		        	color: '#cecece'
		        },
		        axisLabel: {
		            margin: 10,
		            textStyle: {
		                fontSize: 13,
		                color: '#cecece'
		            }
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
	
	var mychart = echarts.init(document.getElementById('rcharts'));
	mychart.setOption(option);
}

function capChart (){
	var ydata = [10, 52, 200, 334, 390, 330, 220];
	var xdata = (function(){
        var arr = [];
        for(var a=1;a<=15;a++){
            arr.push(a+"日");
        }
        return arr;
     }());
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
	            axisLabel: {
			            textStyle: {
			                fontSize: 13,
			                color: '#5f5f5f'
			            }
			        },
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
	var mychart = echarts.init(document.getElementById('capchart'));
	mychart.setOption(option);
}
