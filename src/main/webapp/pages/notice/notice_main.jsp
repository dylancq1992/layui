<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/js/boot.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/js/kindeditor/themes/default/default.css" />
<script src="${pageContext.request.contextPath}/resource/js/kindeditor/kindeditor-all.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/kindeditor/lang/zh-CN.js" type="text/javascript" ></script>

<style type="text/css">
html,body {
	margin: 0;
	padding: 0;
	border: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
}
</style>
</head>

<body>

	<table id="datagrid" class="easyui-datagrid" style="width:100%;height:100%" toolbar='#tb' idField="noticeid" 
		data-options="url:'${pageContext.request.contextPath}/notice/getAllNoticeList.do',fitColumns:true,
		pagination:true,rownumbers:true,border:false,striped:true,pageSize : 20,pageList : [10,20,30,40,50]">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'status',width:50,formatter:statusformatter">状态</th>
				<th data-options="field:'title',width:200">标题</th>
				<th data-options="field:'createdate',width:100">创建时间</th>
				<th data-options="field:'modifydate',width:100">修改时间</th>
				<th data-options="field:'pubtime',width:100">发布时间</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
	<div style="margin-top:5px">
	
	<input id="query"  />
	
	</div>
		
	<div >
		<a href="javascript:void(0);" class="easyui-linkbutton" onClick="doAdd()"
			data-options="iconCls:'icon-add',plain:true">新增</a>
		 <a
			href="javascript:void(0);" class="easyui-linkbutton" onClick="doEdit()"
			data-options="iconCls:'icon-edit',plain:true">编辑</a>
		<a
			href="javascript:void(0);" class="easyui-linkbutton" onClick="doRemove()"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
		<a
			href="javascript:void(0);" class="easyui-linkbutton" onClick="doCancel('0')"
			data-options="iconCls:'icon-no',plain:true">注销</a>
		<a
			href="javascript:void(0);" class="easyui-linkbutton" onClick="doCancel1('1')"
			data-options="iconCls:'icon-ok',plain:true">激活</a>
	</div>
	
	
	</div>
	
	

	<script type="text/javascript">
	
	$("#query").textbox({
		prompt: '请输入标题...',
		width:'300',
		icons:[{
			iconCls:'icon-search',
			handler: function(e){
				var query = $(e.data.target).textbox('getValue');
				$('#datagrid').datagrid('load',{
					query: query
				});
			}
		}]
		
	});
	
	var user_status=[{    
	    "id":"0",    
	    "text":"草稿"   
	},{    
	    "id":"1",    
	    "text":"已发布"   
	},{    
	    "id":"2",    
	    "text":"已撤销"   
	}] ;
	
	function statusformatter(value, rowData, rowIndex) {
	    for (var i = 0; i < user_status.length; i++) {
	        if (user_status[i].id == value) {
	            return user_status[i].text;
	        }
	    }
	}
	
	
	function doAdd(){
		openDialog({
			title : '新增通知公告',
			width : 980,
			height : 600,
			modal: true,
			resizable:true,
			href:'${pageContext.request.contextPath}/pages/notice/notice_add.jsp',
			onLoad:function(){
				
			},
			onBeforeClose: function (event, ui) {
				// 关闭Dialog前移除编辑器
				    //KindEditor.remove('#editor');
			},
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					$('#ff').form('submit', {    
						url : "${pageContext.request.contextPath}/notice/add.do",  
						onSubmit : function(param) {
							//alert(editor.html());
							$.messager.progress({
								title : '提示',
								text : '数据加载中，请稍候....'
							});
							
							var isValid = $(this).form('validate');
							if (!isValid) {
								$.messager.progress('close');
							}
							console.log(editor.html());
							param.content=editor.html();
							
							return isValid;
						},
					    success:function(data){    
					    	//关闭遮罩
							$.messager.progress('close');

							var result = eval("(" + data + ")");
							if (!result.success) {
								$.messager.alert({
									title : '提示',
									msg : "保存失败," + result.message,
									icon:"warning"
								});
							} else {
								$.messager.show({
									title : "提示",
									msg : "保存成功",
									timeout : 1000 * 2
								});
								reloaddatagrid('datagrid');
								closeDialog();
							}   
					    }    
					});  
				}
			}, {
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					closeDialog();
				}
			}
			]
			
		});
	}
	
	
	function doEdit(){
		var rows = $('#datagrid').datagrid('getSelections');
		
		if(rows.length==0){
			$.messager.alert({
				title:"提示",
				msg:"请选择一条记录！",
				icon:"warning"
			}); 
			return;
		}else if(rows.length>1){
			$.messager.alert({
				title:"提示",
				msg:"不能编辑多条记录！",
				icon:"warning"
			}); 
			return;
		}
		
		
		openDialog({
			title : '编辑用户',
			width : 780,
			height : 500,
			modal: true,
			resizable:true,
			href:'${pageContext.request.contextPath}/pages/user/user_edit.jsp',
			onLoad:function(){
				var f = $("#ff");
				f.form("load", rows[0]);
			},
			buttons : [{
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					$('#ff').form('submit', {    
						url : "${pageContext.request.contextPath}/user/edit.do",  
						queryParams : {
							userid : rows[0].userid
						},
						onSubmit : function(param) {
							
							$.messager.progress({
								title : '提示',
								text : '数据加载中，请稍候....'
							});
							
							var isValid = $(this).form('validate');
							if (!isValid) {
								$.messager.progress('close');
								return isValid;
							}
							
							if($("#newpassword").val()!=""){
								if($("#newpassword").val()!=$("#newpassword1").val()){
									$.messager.progress('close');
									$.messager.alert({
										title:"提示",
										msg:"两次输入密码不匹配！",
										icon:"warning"
									}); 
									return false;
								}
							}
							return true;
							
						},
					    success:function(data){    
					    	//关闭遮罩
							$.messager.progress('close');

							var result = eval("(" + data + ")");
							if (!result.success) {
								$.messager.alert({
									title : '提示',
									msg : "保存失败" + result.message,
									icon:"warning"
								});
							} else {
								$.messager.show({
									title : "提示",
									msg : "保存成功",
									timeout : 1000 * 2
								});
								reloaddatagrid('datagrid');  
								closeDialog();
							}   
					    }    
					});  
				}
			}, {
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					closeDialog();
				}
			}
			]
			
		});
	}
	
	function doRemove(){
		
		var rows = $('#datagrid').datagrid('getSelections');
		
		if(rows.length==0){
			$.messager.alert({
				title:"提示",
				msg:"请先选择删除的记录！",
				icon:"warning"
			}); 
			return;
		}
		
		var arrFileid=[];
		
		for(var i=0;i<rows.length;i++){
				arrFileid.push(rows[i].userid);
		}
		
		var data={ids:arrFileid.join("','")};
		
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		    	
		    	$.messager.progress({
					title : '提示',
					text : '数据加载中，请稍候....'
				});
		    	
		    	$.ajax({
					url:"${pageContext.request.contextPath}/user/remove.do",
					type:"post",
					data:data,
					dataType:"json",
					success:function(result){
						//关闭遮罩
						$.messager.progress('close');
						
						
						if (!result.success) {
							$.messager.alert({
								title : '提示',
								msg : "删除失败" + result.message,
								icon:"warning"
							});
						} else {
							$.messager.show({
								title : "提示",
								msg : "删除成功",
								timeout : 1000 * 2
							});
							reloaddatagrid('datagrid');
						}   
						
					},error:function(jqXHR, textStatus, errorThrown){
						//关闭遮罩
						$.messager.progress('close');
						alert(jqXHR.responseText);
					}
				});
		    }    
		});  
	}
	
	function doCancel(type){
		var rows = $('#datagrid').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert({
				title:"提示",
				msg:"请先选择注销的记录！",
				icon:"warning"
			}); 
			return;
		}
		
		var arrFileid=[];
		
		for(var i=0;i<rows.length;i++){
			
				arrFileid.push(rows[i].userid);
		}
		
		var data={status:type,ids:arrFileid.join("','")};
		
		$.messager.confirm('确认','您确认想要注销记录吗？',function(r){    
		    if (r){    
		    	$.messager.progress({
					title : '提示',
					text : '数据加载中，请稍候....'
				});
		    	
		    	$.ajax({
					url:"${pageContext.request.contextPath}/user/doCancel.do",
					type:"post",
					data:data,
					dataType:"json",
					success:function(result){
						//关闭遮罩
						$.messager.progress('close');
						
						if (!result.success) {
							$.messager.alert({
								title : '提示',
								msg : "注销失败" + result.message,
								icon:"warning"
							});
						} else {
							$.messager.show({
								title : "提示",
								msg : "注销成功",
								timeout : 1000 * 2
							});
							 $('#datagrid').datagrid('reload');  
						}   
						
					},error:function(jqXHR, textStatus, errorThrown){
						//关闭遮罩
						$.messager.progress('close');
						alert(jqXHR.responseText);
					}
				});
		    }    
		});  
	}
	
	
	function doCancel1(type){
		var rows = $('#datagrid').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert({
				title:"提示",
				msg:"请先选择激活的记录！",
				icon:"warning"
			}); 
			return;
		}
		
		var arrFileid=[];
		
		for(var i=0;i<rows.length;i++){
			
				arrFileid.push(rows[i].userid);
		}
		
		var data={status:type,ids:arrFileid.join("','")};
		
		$.messager.confirm('确认','您确认想要激活记录吗？',function(r){    
		    if (r){    
		    	$.messager.progress({
					title : '提示',
					text : '数据加载中，请稍候....'
				});
		    	
		    	$.ajax({
					url:"${pageContext.request.contextPath}/user/doCancel.do",
					type:"post",
					data:data,
					dataType:"json",
					success:function(result){
						//关闭遮罩
						$.messager.progress('close');
						
						if (!result.success) {
							$.messager.alert({
								title : '提示',
								msg : "激活失败" + result.message,
								icon:"warning"
							});
						} else {
							$.messager.show({
								title : "提示",
								msg : "激活成功",
								timeout : 1000 * 2
							});
							 $('#datagrid').datagrid('reload');  
						}   
						
					},error:function(jqXHR, textStatus, errorThrown){
						//关闭遮罩
						$.messager.progress('close');
						alert(jqXHR.responseText);
					}
				});
		    }    
		});  
	}
	</script>
</body>
</html>
