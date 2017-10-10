function del(url) {
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows.length==0){
		alert("请选择要删除的记录！");
	}else if(confirm("确认删除？")){
		var arr = [];
		for(var i = 0; i < rows.length; i++) {
			arr.push(rows[i].id);
		}
		
		$.ajax({
	         type: "post",
	         async: false, //同步执行
	         url: url,
	         dataType: "json", //返回数据形式为json
	         data:{ids:arr+""}, 
	         success: function(result) {
	        	 qc.pageNo=1;
	        	 $search(qc);
	        	 }
	         });
	}
}
function edit(url) {
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows.length==0) {
		alert("请选择要修改的记录！");
	}else if(rows.length>1) {
		alert("请选择一条进行修改");
	}else{
		location.href=''+url+'?id='+rows[0].id;/* 跳转到要编辑的页面路径 */
	}
}
function add(url) {
	location.href=url;
}
/**
 * 查询通用方法
 * @returns
 */
function $search(otherParam){
	var paramArr = $("form").serializeArray();
	var param = {};
	$.each(paramArr,function(){
		param[this.name] = this.value;
	});
	
	if(otherParam){
		var key;
		for(key in otherParam){
			param[key] = otherParam[key];
		}
	}
	$('#datagrid').datagrid('reload',param);
}
/**
 * 导出方法
 * @param url
 * @returns
 */
function _exp(url){
	$("form").attr("action",url);
	$("form").submit();
}	
		
function search(){
	 qc.pageNo=1;
	 $search(qc);
}
function num2(value,row,index){
	if(value != 0 && (value==null||value=='')){
		return '';
	}
	return (+value).toFixed(2);
}