var paramMap = {};
var svg = null;
var svgJquery = null;
var ids = "";
var refresh_time = 9000;
var viewBox = null;
var allcollect = {};
var menu=null;
$(document).ready(function() {
	createJson("name",paramname);
	createJson("num",num);
	if(code!=null){
		createJson('code',code);
	}
	if(psid!=null){
		createJson('psid',psid);
	}
	$('#svgdiv').height($(window).height());
	$("#svgdiv").attr("src", "/static/images/svg/"+nbqmodel+".svg");
	$('#svgdiv').load(
		function() {
			var ite = [];
			ite.push({ id:'back', text: '返回',click:goback,icon: 'halflings-icon arrow-left' });
			ite.push({ id:'sureAll', text: '全部确认',click:function(){},icon: 'halflings-icon ok' });
			ite.push({ id:'yk', text: '遥控操作',click:ykMenu,icon: 'halflings-icon wrench' })
			menu = $.ligerMenu({ top: 100, left: 100, width: 120, items:ite});
			document.getElementById('svgdiv').contentWindow.document
			.addEventListener('contextmenu',  function(e) {
				initmenu(e,'_blank');
			}, true);
			document.getElementById('svgdiv').contentWindow.document
			.addEventListener('mousedown',  function(e) {
				menu.hide();
			}, false);
			svgJquery = $(document.getElementById('svgdiv').contentWindow.document).find("svg");
			svg = Snap(svgJquery.get(0));
			viewBox = "0 0 " + parseInt(svg.attr("width"))
						+ " " + parseInt(svg.attr("height"));
			
			svg.attr("width", $(window).width());
			svg.attr("height", $(window).height());
			svg.attr("viewBox", viewBox);
			svg.attr("preserveAspectRatio", "none");
			
			initSvg();
			if(svg.select("title")){
			    svg.select("title").remove();
			}
		});
	
});
function ykMenu(){
	yk(currentYKid,1);
}

var currentYKid = '';
var currentYKYXid = '';
function initmenu(e,type,tag,yktag){
	e.preventDefault();
	currentYKid = yktag;
	currentYKYXid = tag;
	if(type=='_blank'){
		menu.setDisable('yk');
	}else if(type=='yk'){
		menu.setEnable('yk');
	}
	if(nbqmodel=="bwd"){
		menu.setDisable('back');
	}
	menu.show({ top: e.pageY, left: e.pageX });
}

function initSvg() {
	ids = "";
	$("*[inkscape\\:label]", svgJquery).each(function() {	
		var element = Snap($(this).get(0));
		var nodeName = element.type;
		var mlndata =element.attr("inkscape:label");
		if(mlndata.indexOf("attr")<0){return;}
		var jsonarray= $.parseJSON("["+mlndata+"]");
		if (nodeName.toLowerCase() == "text" || nodeName.toLowerCase() == "tspan") {
			var tag = null;
			var mouseup = null;
			$.each(jsonarray, function(i, n) {
				var attr = n.attr;
				if (attr == "get") {
					tag = getcollect(n.tag);
					element.attr("inkscape:label",mlndata.replace(n.tag,tag));
					ids = ids + tag + ",";
					if(allcollect.hasOwnProperty(tag)){
					    allcollect[tag].body.push({"html":element,"type":'yc'});
	                }else{
	                    allcollect[tag]={"body":[{"html":element,"type":'yc'}],"value":"0.00","marks":"false","flares":"false","breaks":"false"};
	                }
				}else if(attr == "script"){
                	var scripts = n.list;
                	if(scripts.length>0){
                		for ( var x in scripts) {
							if(scripts[x].evt=="mouseup"){
								mouseup = scripts[x].param;
							}
						}
                	}
                }else if(attr == "set"){
                	text(element, getcollect(n.tag),false);
                }
			});
			if(mouseup){
				element.attr("cursor","pointer").click(function (){
				    var re = eval(mouseup);
                    if(re!=null){
                        re.apply(this);
                    }
				});
			}
			
		}else if(nodeName.toLowerCase() == "g"){
			var tag = null;
			var yktag = null;
			var mouseup = null;
			$.each(jsonarray, function  (i, n){
                var attr = n.attr;
                if(attr=="clone"){
                	var maps = n.map;
                	for(var i=0;i<maps.length;i++){ 
                		var gtype = maps[i].split("=")[0];
                		var gval = maps[i].split("=")[1];
                		if(gtype=="%tag"){
                			tag = getcollect(gval);
                			element.attr("inkscape:label",mlndata.replace(gval,tag));
        					ids = ids + tag + ",";
        					if(allcollect.hasOwnProperty(tag)){
                                allcollect[tag].body.push({"html":element,"type":'yx'});
                            }else{
                                allcollect[tag]={"body":[{"html":element,"type":'yx'}],"value":"0.00","marks":"false","flares":"false","breaks":"false"};
                            }
                		}else if(gtype=="%yk"){
                			yktag = getcollect(gval);
                			element.attr("inkscape:label",mlndata.replace(gval,yktag));
                		}
                	} 
                }else if(attr == "script"){
                	var scripts = n.list;
                	if(scripts.length>0){
                		for ( var x in scripts) {
							if(scripts[x].evt=="mouseup"){
								mouseup = scripts[x].param;
							}
						}
                	}
                }
			});
			if(mouseup){
				element.attr("cursor","pointer").click(function (){
				    var re = eval(mouseup);
                    if(re!=null){
                        re.apply(this);
                    }
				});
			}
			if(yktag){
				document.getElementById('svgdiv').contentWindow.document.getElementById(element.attr("id")).addEventListener('contextmenu',  function(e) {initmenu(e,"yk",tag,yktag,this)}, false);
			}
		}
	});
	refresh();
}
function refresh(){
    var count = 0;
    var i = 0;
    var idvalue0 = {};
    for (var prop in allcollect) {
        eval("idvalue"+count+"[prop]=1;");
        if(i==30){
            eval("Concurrent.Thread.create(refreshByCollect,idvalue"+count+");");
            count++;
            eval("var idvalue"+count+"= {};");
            i=0;
        }
        i++;
    }
    if((i%30-1)!=0){
        eval("Concurrent.Thread.create(refreshByCollect,idvalue"+count+");");
    }
} 
function refreshByCollect(collect){
    var idvalue= "";
    for (var prop in collect) {
        idvalue=idvalue+prop+"*-"+allcollect[prop].value+"*-"+allcollect[prop].marks+"*-"+allcollect[prop].flares+",";
    }
    $.ajax({
        type : "POST",
        url : "/monitor/svg/realdata", 
        data : "idvalue=" + idvalue,
        error : function(data, transport) {
        },
        success : function(data) {
            if(data){
                var list = eval("(" + data + ")");
                for(var k in list){
                   
                        for(var d=0;d<allcollect[k].body.length;d++){
                            Concurrent.Thread.create(getData,list[k],k,allcollect[k].body[d].html,allcollect[k].body[d].type);
                        }
                       allcollect[k].value=list[k].value;
                       allcollect[k].marks=list[k].marks;
                       allcollect[k].flares=list[k].flares;
                       allcollect[k].breaks=list[k].breaks;
                    
                } 
            }
            setTimeout(function(){refreshByCollect(collect);}, refresh_time);
        }
    });
}
function getData(list,id,element,flag){
    
        var value =list.value;
        var marks =list.marks;
        var flares = list.flares;
        var breaks = list.breaks;
        var mlndata =element.attr("inkscape:label");
        if(flag=='yc'){
            text(element, value,breaks);
        }else if(flag=='yx'){
            yxSwitch(element,value);
           
        }
}
function test(id,element,flag){
	//alert(element);
}
// 改变窗体大小时适应浏览器高度
$(window).resize(function() {
	// 模块尺寸
	$('#svgdiv').height($(window).height());
	svg.attr("width", $(window).width());
	svg.attr("height", $(window).height());
	svg.attr("preserveAspectRatio", "none");
});





// 遥信显示隐藏颜色变化
function yxSwitch(g,val){
	if (g.type == "g") {
		var group = g.selectAll("*");
		group.forEach(function(gi, i) {
			yxSwitch(gi, val);
		});
	} else {
		var mlndata =g.attr("inkscape:label");
// //判断更换颜色 还是 隐藏显示
		var jsonarray= $.parseJSON("["+mlndata+"]");
		$.each(jsonarray, function(i, n) {
			if(n!=null){
				var attr = n.attr;
				if(attr=="opac"){ // 显示隐藏
					// 判断val 是属于max 还是 min min 不显示 max显示
					var max = n.max;
					var min = n.min;
					
						if(parseInt(val) == parseInt(n.max)){ // 显示
							yxShowChange(g,true);
						}else if(parseInt(val) == parseInt(n.min)){ // 不显示
							yxShowChange(g,false);
						}
					
				}else if(attr=="color"){ // 更改颜色
					var colors = n.list;
                	for ( var x in colors) {
						if(colors[x].data==parseInt(val)){ // 配置值与实时值相同
							yxChange(g,colors[x].param);
						}
					}
				}
			}
		});
	}
}
// 显示隐藏
function yxShowChange(g,b){
	if(b){ // 显示
		g.attr({ visibility: "visible"});
	}else{ // 隐藏
		g.attr({ visibility: "hidden"});
	}
}
// 遥信值变化
function yxGChange(g,color){
	if (g.type == "g") {
		var group = g.selectAll("*");
		group.forEach(function(gi, i) {
			yxGChange(gi, color);
		});
	} else {
		yxChange(g, color);
	}
}
// 遥信颜色变化
function yxChange(obj,color){
	if (obj.attr("fill") == "none" || obj.attr("fill") == "#000000") {
		var e = obj.animate({
			stroke : color 
		}, 200);
	}else{
		var e = obj.animate({
			fill : color,
			stroke : color 
		}, 200);
	}
	
}
// 遥信值变化
function blinkChange(g,value){
    if (g.type == "g") {
        var group = g.selectAll("*");
        group.forEach(function(gi, i) {
            blinkChange(gi,value);
        });
    }else if (g.type == "text") {
        var tspan =g.select("tspan");
        if (tspan!=null) {
            svgflicker(tspan,value);
        } else {
            svgflicker(g,value);
        }
    }  else {
        svgflicker(g,value);
    }
}

function svgflicker(obj,value) {
    var fillco = obj.attr("fill-opacity");
    if(value!=null){
    	obj.animate({
            "fill-opacity":1,
               "stroke-opacity":1
               }, 300,null,null);
    }else{
        if (fillco === "1") {
            obj.animate({
                "fill-opacity":0.2,
                "stroke-opacity":0.2
                }, 300,null,null);
        }else{
            obj.animate({
                "fill-opacity":1,
                   "stroke-opacity":1
                   }, 300,null,null);
        }
    }
}



function text(obj, value,breaks) {
	if (value != "") {
		var tspan =obj.select("tspan");
		if (tspan!=null) {
			$("#"+tspan.attr("id"),svgJquery).text(value);
		} else {
			$("#"+obj.attr("id"),svgJquery).text(value);
		}
		if(breaks){
			tspan.attr({"fill":"#b5b5b4"});
		}else{
			tspan && obj && tspan.attr({"fill":obj.attr("fill")});
		}
	}
}
// 参数：prop = 属性，val = 值
function createJson(prop, val) {
	
	// 如果 val 被忽略
	if (typeof val === "undefined") {
		// 删除属性
		delete paramMap[prop];
	} else {
		// 添加 或 修改
		paramMap[prop] = val;
	}
}
// 转义${}类型参数
function getcollect(el) {
	var regexp = new RegExp("\\\$\\{(.*?)\\}", "g");
	var rs = null;
	while ((rs = regexp.exec(el)) != null) {
		el = eval("el.replace(rs[0],paramMap." + rs[1] + ")");
	}
	return el;
}


function show(url){
	
	location.href=encodeURI(getcollect(url)).replace("#","%23");
}

//0:开机选择，1：开机执行，2：关机选择，3：关机执行，4：遥调选择，5：遥调执行
function yk(_id,type){
	_id = getcollect(_id);
	var node = null
	if(parent.parent){
		node = parent.parent;
	}else{
		node = parent;
	}
	$.ajax({
	     type: "post",
	     url: '/remote/checkpoint?_id='+_id,
	     dataType: "json", //返回数据形式为json
	     data: {},
	     success: function(result) {
	    	 if(result.remote){
	    		   node.openRemote({
	    		        height:620,
	    		        width: 820,
	    		        title : '远程' + ([0,1,2,3].indexOf(type)>-1?"遥控":"遥调"),
	    		        url: '/remote/page?_id='+_id+"&type="+type, 
	    		        showMax: false,
	    		        showToggle: true,
	    		        showMin: false,
	    		        isResize: true,
	    		        slide: false,
	    		        onClosed:function(data){
	    		        	
	    		        }
	    		  });
	    	 }else{
	    		 alert("该点不可遥控");
	    	 }
	     }
	});
}

function goback(){
    history.back(-1);
}







