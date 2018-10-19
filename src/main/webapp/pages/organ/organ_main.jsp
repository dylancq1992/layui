<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>组织管理</title>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/js/boot.js"></script>

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
	<div class="easyui-layout" style="width:100%;height:100%;">
		<div region="west" split="true" title="机构信息列表" style="width:30%;">
			<ul id="tree" >
			</ul>
			<div id="mm" class="easyui-menu" style="width:120px;">
				<div onclick="doAdd()" data-options="iconCls:'icon-add'">新增子机构</div>
				<div class="menu-sep"></div>
				<div onclick="doEdit()" data-options="iconCls:'icon-edit'">编辑机构</div>
				<div class="menu-sep"></div>
				<div onclick="doRemove()" data-options="iconCls:'icon-remove'">删除</div>
			</div>
		</div>
		<div id="content" region="center" title="详细信息" style="width: 100%;height: 100%">
		
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" >
		<form id="form" >
	<table cellpadding="5" style="margin-left:40px;margin-top: 5px">
		<tr>
			<td>组织代码:</td>
			<td><input class="easyui-textbox" type="text" name="code" readonly="true"
				style="width:240px;" ></input>
			</td>
			
			<td>组织名称:</td>
			<td><input class="easyui-textbox" type="text" name="name" readonly="true"
				style="width:100%;" ></input>
			</td>
		</tr>
		<tr>
			<td>机构等级:</td>
			<td>
				<input  class="easyui-combobox" name="grade"   style="width:100%;" readonly="true"
   					 data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/resource/dictionary/grade.json'" />  
				</td>
				
					<td>行政区划:</td>
			<td><input  name="districtc_code"  class="easyui-combotree" readonly="true"
				 style="width:100%;" style="height:60px"
			data-options="idField:'id',textField:'name',parentField:'parentid',method:'get',animate:true,lines:true,
			url:'${pageContext.request.contextPath}/districtc_code/getAllDistrictcCodeList.do'"	 
				 ></input>
			</td>
		</tr>
		<tr>
			<td>组织描述:</td>
			<td><input class="easyui-numberbox" name="description" readonly="true"
				style="width:100%;" style="height:60px"></input>
			</td>
		</tr>
	</table>
</form>
			</div>
			
			
			<div data-options="region:'south'" style="height: 340px;">
			
			
			<table id="datagrid" class="easyui-datagrid" style="width:100%;height:100%" toolbar='#tb' idField="userid" 
		data-options="url:'${pageContext.request.contextPath}/user/getUserByOrgan.do',fitColumns:true,
		pagination:true,rownumbers:true,border:false,striped:true,pageSize : 20,pageList : [10,20,30,40,50]">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'name',width:150">用户名</th>
				<th data-options="field:'loginname',width:100">登陆名</th>
				<th data-options="field:'qq',width:100">QQ</th>
				<th data-options="field:'email',width:100">EMAIL</th>
				<th data-options="field:'status',width:100,formatter:statusformatter">用户状态</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<a href="javascript:void(0);" class="easyui-linkbutton" onClick="doAddUser()"
			data-options="iconCls:'icon-add',plain:true">添加用户</a>
		<a
			href="javascript:void(0);" class="easyui-linkbutton" onClick="doRemoveUser()"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>

			
			</div>
			
		</div>
		
		</div>

		</div>
		
	<script type="text/javascript">
	
	var user_status=[{    
	    "id":"1",    
	    "text":"正常"   
	},{    
	    "id":"0",    
	    "text":"已注销"   
	}] ;
	
	function statusformatter(value, rowData, rowIndex) {
	    for (var i = 0; i < user_status.length; i++) {
	        if (user_status[i].id == value) {
	            return user_status[i].text;
	        }
	    }
	}
	var tree=$('#tree');
			tree.tree({
			url : "${pageContext.request.contextPath}/organ/getAllOrganList.do",
			idField   : "organid",
			textField : "name",
			parentField : "parentorganid",
			method : 'get',
			animate : true,
			lines : true,
			onClick : function(node) {
				$('#form').form('load', node);
				$('#datagrid').datagrid('load',{
					organid: node.organid
				});

			},
			onLoadSuccess: function (node, data) {
				
	        },
			onContextMenu : function(e, node) {
				//return false;
				
				e.preventDefault();
				// 查找节点
				
				// 显示快捷菜单
				$('#mm').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});

		 
		function doAdd() {
			var node = tree.tree('getSelected');
			
			var levels =  tree.tree("getLevel",node.target);
			
			openDialog({
				title : '新增机构',
				width : 580,
				height : 400,
				modal: true,
				href:'${pageContext.request.contextPath}/pages/organ/organ_add.jsp',
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						$('#ff').form('submit', {    
							url : "${pageContext.request.contextPath}/organ/add.do",  
							onSubmit : function() {
								$.messager.progress({
									title : '提示',
									text : '数据加载中，请稍候....'
								});
								var isValid = $(this).form('validate');
								if (!isValid) {
									$.messager.progress('close');
								}
								return isValid;
							},
							queryParams : {
								parentorganid : node.organid,
								levels:(levels+1)
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
									tree.tree('append', {
										parent: node.target,
										data: [result.data]
									});
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
			var node = tree.tree('getSelected');
			
			openDialog({
				title : '编辑机构',
				width : 580,
				height : 400,
				modal: true,
				href:'${pageContext.request.contextPath}/pages/organ/organ_edit.jsp',
				onLoad:function(){
					var f = $("#ff");
					f.form("load", node);
				},	
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						$('#ff').form('submit', {    
							url : "${pageContext.request.contextPath}/organ/edit.do",  
							onSubmit : function() {
								$.messager.progress({
									title : '提示',
									text : '数据加载中，请稍候....'
								});
								var isValid = $(this).form('validate');
								if (!isValid) {
									$.messager.progress('close');
								}
								return isValid;
							},
							queryParams : {
								organid : node.organid,
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
									tree.tree('update', {
										target: node.target,
										text: result.data.name,
										name: result.data.name,
										description:result.data.description,
										districtc_code:result.data.districtc_code,
										grade:result.data.grade
										
									});
									
									$.messager.show({
										title : "提示",
										msg : "更新成功",
										timeout : 1000 * 2
									});
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
			var node = tree.tree('getSelected');  
			
			var nodes = tree.tree('getChildren',node.target);
			
			if(nodes.length>0){
				$.messager.alert({
					title:"提示",
					msg:"请先删除子节点！",
					icon:"warning"
				}); 
				return;
			}
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			    	$.ajax({
						url:"${pageContext.request.contextPath}/organ/remove.do",
						type:"post",
						data:{organid:node.organid},
						dataType:"json",
						
						success:function(result){
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
								tree.tree("remove",node.target);
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
		
		
		function doAddUser(){
			var node = tree.tree('getSelected');  
			if(!node){
				$.messager.alert({
					title:"提示",
					msg:"请选择机构！",
					icon:"warning"
				}); 
				return;
			}
			
			openDialog({
				title : '新增机构用户',
				width : 780,
				height : 500,
				modal: true,
				href:'${pageContext.request.contextPath}/pages/organ/add_organ_user.jsp',
				onLoad:function(){
					$('#datagrid3').datagrid({
					    url:'${pageContext.request.contextPath}/organ/getUserByOrganid.do',
					    idField:"userid", 
					    width:'100%',
					    height:'100%',
					    pagination:true,
					    pageSize : 20,
					    pageList : [10,20,30,40,50],
					    fitColumns:false,
						rownumbers:true,
						border:false,
						striped:true,
						queryParams: {
							organid: node.organid
						}
					});
				},	
				buttons : [ {
					text : '添加',
					iconCls : 'icon-ok',
					handler : function() {
						
					var rows= $('#datagrid3').datagrid('getSelections');	
					
					if (rows.length==0){
						$.messager.alert({
							title : '提示',
							icon:"warning",
							msg : "请选择用户！"
						});
						return;
					}
					
					var ids=[];
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].userid);
					}
					
					$.messager.progress({
						title : '提示',
						text : '数据加载中，请稍候....'
					});
					$.ajax({
						url:"${pageContext.request.contextPath}/organ/add_user.do",
						type:"post",
						data:{organid:node.organid,ids:ids.join("','")},
						dataType:"json",
						success:function(result){
							$.messager.progress('close');
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
								
								$('#datagrid').datagrid('reload');
								closeDialog();
							}  
							
						},error:function(jqXHR, textStatus, errorThrown){
							//关闭遮罩
							$.messager.progress('close');
							alert(jqXHR.responseText);
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
		
		function doRemoveUser(){
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
						url:"${pageContext.request.contextPath}/organ/remove_user.do",
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
	</script>
</body>
</html>
