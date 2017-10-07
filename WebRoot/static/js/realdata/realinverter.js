
var refreshtime = 5000;

$(function(){
	refresh ();
})

function refresh (){
	totalData();
	setTimeout(refresh, refreshtime);
	setTimeout(search, refreshtime);
}
function clickState(state){
	$("#state").val(state);
	search();
}
function totalData (){
    $.ajax({
        type: "post",
        async: false, //同步执行
        url: "/realdata/inverter/data",
        dataType: "json", //返回数据形式为json
        data: {},
        success: function(data) {
        	if(data){
        		$("#acpower").text(data.acpower.toFixed(2));
        		$("#radian").text(data.radian.toFixed(2));
        		$("#daycap").text(data.daycap.toFixed(2));
        		$("#totalcap").text(data.totalcap.toFixed(2));
        		
        		var states = data.states;
        		$("#normal_run_num").text(data.states[1]+"台");
        		$("#normal_stop_num").text(data.states[2]+"台");
        		$("#error_run_num").text(data.states[3]+"台");
        		$("#error_stop_num").text(data.states[4]+"台");
        		$("#bread_num").text(data.states[5]+"台");
        		$("#error_dci_num").text(data.states[6]+"台");
        		
        		$("#normal_run_bar").css("width", data.states[1]*100/data.size + "%");
        		$("#normal_stop_bar").css("width", data.states[2]*100/data.size + "%");
        		$("#error_run_bar").css("width", data.states[3]*100/data.size + "%");
        		$("#error_stop_bar").css("width", data.states[4]*100/data.size + "%");
        		$("#bread_bar").css("width", data.states[5]*100/data.size + "%");
        		$("#error_dci_bar").css("width", data.states[6]*100/data.size + "%");
        	}
        	
        }
    });
}
