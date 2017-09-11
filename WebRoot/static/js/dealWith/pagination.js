
var qc={
    "pageNo":1,
    "rowCount":1,
    "pageCount":1,
    "pageSize":2
};
var pageSub=1;

var pageIndex=10;
function calPageNum(){
    if((qc.pageNo-1)*qc.pageSize>qc.rowCount){
        qc.pageNo=1;
    }
    if (qc.pageSize < 1) {
        qc.pageCount = 0;
    }
    if (qc.rowCount %qc. pageSize == 0) {
        qc.pageCount=parseInt(qc.rowCount / qc.pageSize);
    } else {
        qc.pageCount=parseInt(qc.rowCount / qc.pageSize) + 1;
    }
    var leftset = "";
    var rightset = "";
    if(pageIndex%2==0){//偶数
        leftset = pageIndex/2-1;
        rightset = pageIndex/2;
    }else{//奇数
        leftset = rightset = parseInt(pageIndex/2);
    }
    $("#pagebutton").html("");
    var pageHtml="";
    //首页
    pageHtml += "<li id='pagenoStart'> <a id='pagenoStart' href='javascript:void(0)' title='首页' onclick=\"if(pageSub!=null){if(qc.pageNo!=1)pageSubmit();qc.pageNo=1;}\">&lt;&lt;</a></li>";
    //上一页
    pageHtml += "<li id='pagenoBack' class='prev disabled'> <a id='pagenoBack' href='javascript:void(0)' title='前一页' onclick=\"if(pageSub!=null){if(qc.pageNo!=1)pageSubmit();qc.pageNo="+(qc.pageNo == 1 ? 1 : (parseInt(qc.pageNo) - 1) )+";}\">&lt;</a> </li>";
    
    if(qc.pageCount<=pageIndex){
        for(var j=1;j<=qc.pageCount;j++){
        	pageHtml+="<li id='pageno"+j+"' onclick=\"qc.pageNo="+j+";if(pageSub!=null){pageSubmit();}\"> <a id='pageno"+j+"' href='javascript:void(0)'>"+j+"</a> </li>";
        }
    }else{
        if(qc.pageNo<=leftset){
            for(var j=1;j<=pageIndex;j++){
            	pageHtml+="<li id='pageno"+j+"' onclick=\"qc.pageNo="+j+";if(pageSub!=null){pageSubmit();}\"> <a id='pageno"+j+"' href='javascript:void(0)'>"+j+"</a> </li>";
            }
        }else if(qc.pageNo>=(qc.pageCount-rightset+1)){
            for(var j=(qc.pageCount-pageIndex+1);j<=qc.pageCount;j++){
            	pageHtml+="<li id='pageno"+j+"' onclick=\"qc.pageNo="+j+";if(pageSub!=null){pageSubmit();}\"> <a id='pageno"+j+"' href='javascript:void(0)'>"+j+"</a> </li>";
            }
        }else{
            for(var j=qc.pageNo-leftset;j<=((qc.pageNo+rightset)<=qc.pageCount?(qc.pageNo+rightset):qc.pageCount);j++){
            	pageHtml+="<li id='pageno"+j+"' onclick=\"qc.pageNo="+j+";if(pageSub!=null){pageSubmit();}\"> <a id='pageno"+j+"' href='javascript:void(0)'>"+j+"</a> </li>";
            }
        }
    
    }
    //下一页
    pageHtml += "<li id='pagenoNext'> <a id='pagenoNext' href='javascript:void(0)' title='后一页' onclick=\"if(pageSub!=null){if(qc.pageNo!=qc.pageCount)pageSubmit();qc.pageNo="+(qc.pageNo == qc.pageCount ? qc.pageCount : (parseInt(qc.pageNo) + 1))+";}\">&gt" +
    		";</a></li>";
    //末页
    pageHtml += "<li id='pagenoEnd' class='prev disabled'> <a id='pagenoEnd' href='javascript:void(0)' title='末页' onclick=\"if(pageSub!=null){if(qc.pageNo!=qc.pageCount)pageSubmit();qc.pageNo="+qc.pageCount+";}\">&gt;&gt;</a> </li>";
    
    $("#pagebutton").append(pageHtml);
    $("li[id='pageno"+qc.pageNo+"']").addClass("active");
    $("#pagebutton").css("float","none");
    
    /*$("#pagebutton").find("a").css("color","#FFFFFF");*/
    if(qc.pageNo==1){
        $("li[id=pagenoStart]").addClass("prev disabled");
        $("li[id=pagenoStart]").removeClass("active");
        
        $("li[id=pagenoBack]").addClass("prev disabled");
        $("li[id=pagenoBack]").removeClass("active");
    }else{
    	$("li[id=pagenoStart]").removeClass("prev disabled");
        $("li[id=pagenoBack]").removeClass("prev disabled");
    }
    if(qc.pageNo==qc.pageCount){
        $("li[id=pagenoNext]").addClass("prev disabled");
        $("li[id=pagenoNext]").removeClass("active");
        
        $("li[id=pagenoEnd]").addClass("prev disabled");
        $("li[id=pagenoEnd]").removeClass("active");
    }else{
    	$("li[id=pagenoNext]").removeClass("prev disabled");
        $("li[id=pagenoEnd]").removeClass("prev disabled");
    }
}
function paging(qcdata){
	qc.rowCount = qcdata;
	 if (qc.rowCount %qc. pageSize == 0) {
	        qc.pageCount=parseInt(qc.rowCount / qc.pageSize);
	    } else {
	        qc.pageCount=parseInt(qc.rowCount / qc.pageSize) + 1;
	    }
    var html = "";
    var color = '#FFF';
    if(qc.rowCount !=0){
    	html += '<nav class="text-center">';
    	html += '<ul class="pagination pagination-sm" id=\"pagebutton\">';
    	html += '</ul>';
    	html += '</nav>';
    	html += '<div class="dataTables_length pull-right list-inline">';
    	html += '<label>每页显示 '+qc.pageSize;
    	html += ' 条</label>';
    	html += '</div>';
    	html += '<div class="dataTables_length total-info pull-right list-inline">';
    	html += '<label>总共<span>'+qc.rowCount+'</span>条，</label>';
    	html += '</div>';
    	html += '<div class="clear"></div>';
    }else{
    	 html+="<div class='dataTables_empty text-center'>";
         html+="<span style='display:inline-block;padding-top:20px;'>没有可展示的数据</span>";
         html+="</div>";
    }
    $("div[id='next-page']").html(html);
    calPageNum();
}


//分页提交数据
function pageSubmit(){
	$search(qc);
}

