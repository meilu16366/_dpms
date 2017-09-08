<%--
/* 
 * @Description: 后台首页
 * @author: ml
 * @createDate: 2017-7
 * @company: 广东振森智能科技有限公司
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>

<!DOCTYPE html>
<html>
	<head>
	<title>广东振森智能科技有限公司后台管理</title>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="/sys/js/ligerUI/skins/Aqua/css/ligerui-all.css">
	<style type="text/css">
	.l-tab-content{height: 100%;}
	.top-menu{
		margin: 0;
	    padding: 0;
	    height: 31px;
	    line-height: 31px;
	    background: url('/sys/img/top.jpg') repeat-x bottom;
	    position: relative;
	    border-top: 1px solid #1D438B;
	}
	.main-title{
	    color: #E7E7E7;
	    padding-left: 35px;
	    line-height: 26px;
	    font-weight: 3px;
	    font-size: 14px;
	    float: left;
	}
	.logout{
		float: right;
		font-weight: 3px;
	    font-size: 14px;
		margin-right: 20px;
		line-height: 26px;
	}
	.logout a{
		color: #E7E7E7;
	    text-decoration: underline;
	}
	</style>
	</head>
	<body>
		<div id="top" class="top-menu">
			<div class="main-title">广东振森智能科技有限公司</div>
			<div class="logout"><a href="/logout">退出</a></div>
		</div>
		<div id="mainLayout">
		    <div position="left" id="accordion">
		    	<div title="角色管理">
		    		<ul id="role_tree"></ul>
		    	</div>
		    	<div title="用户管理">
		    		<ul id="user_tree"></ul>
		    	</div>
		    	<div title="用户功能">
		    		<ul id="modul_tree"></ul>
		    	</div>
		    	<div title="数据字典">
		    		<ul id="sysdata_tree"></ul>
		    	</div>
		    </div>
		    <div position="center" id="framecenter">
		    	<div tabid="tabitem1" title="欢迎"  >
		    		<h1>welcome</h1>
            	</div>
		    </div>
	    </div>
	</body>
	<script src="/sys/js/jquery/jquery-1.9.0.min.js"></script>
	<script src="/sys/js/ligerUI/js/ligerui.all.js"></script>
	<script>
	
	
	$(function(){
		var tab;
		$("#mainLayout").ligerLayout({ leftWidth: 250});
		$("#framecenter").ligerTab({
        	showSwitch:true,
        	contextmenu:true,
        	showSwitchInTab : true,
        });
        //面板
        $("#accordion").ligerAccordion();
        $("#role_tree").ligerTree({
        	checkbox: false,
            data: [
            	{id:1,pid:0,text:"角色管理",url:"/sys/role/list"}
            ],
            onSelect:function(node){
                $(node.target).attr("tabid", "tabitem1");
				f_tab("tabitem1", node.data.text, node.data.url);
            }
        });
        $("#user_tree").ligerTree({
        	checkbox: false,
            data: [
            	{id:1,pid:0,text:"用户管理",url:"/sys/user/list"}
            ],
            onSelect:function(node){
                $(node.target).attr("tabid", "tabitem1");
				f_tab("tabitem1", node.data.text, node.data.url);
            }
        });
        $("#sysdata_tree").ligerTree({
        	checkbox: false,
            data: [
            	{id:1,pid:0,text:"系统参数",url:"/sys/param/list"},
            	{id:2,pid:0,text:"调度作业",url:"/sys/task/list"},
            	{id:3,pid:0,text:"值列表管理",url:"/sys/listValue/list"}
            ],
            onSelect:function(node){
                $(node.target).attr("tabid", "tabitem1");
				f_tab("tabitem1", node.data.text, node.data.url);
            }
        });
        
        {
	        manager = $("#modul_tree").ligerTree({
	             data:${moduls},
	             checkbox: false,
	             idFieldName :'id',
	             slide : false,
	             parentIDFieldName :'pid',
	 			 isExpand: 2,
	             onSelect:function(node){
	                $(node.target).attr("tabid", "tabitem1");
					f_tab("tabitem1", node.data.text, node.data.url);
	            }
	        });
	        treeManager = $("#modul_tree").ligerGetTreeManager();
        }
        tab = liger.get("framecenter");

       	function f_tab(tabid, text, url){
       		tab.removeTabItem("tabitem1");
            tab.addTabItem({
                 tabid: tabid,
                 text: text,
                 url: url,
                 callback: function (){}
            });
        }
	});
	
	//添加节点
	function addTreeItem(nodes){
         var node = manager.getSelected();
         if (node){
             manager.append(node.target, nodes); 
         }else{
             manager.append(null, nodes);
         }
         selectNode(nodes[0].id);
    }
    //选择节点
	function selectNode(id){
        var parm = function (data){
            return data.id == id;
        };
        manager.selectNode(parm);
    }
    //删除节点
	function removeTreeItem(){
    	var node = manager.getSelected();
    	if (node)
             manager.remove(node.target);
    	else
             alert('请先选择节点');
    }
	//session过期退出方法
	function logout(){
		location.href = '/logout';
	}
	</script>
</html>
