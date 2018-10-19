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
<title>菜单管理</title>

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
		<div region="west" split="true" title="菜单列表" style="width:300px;">
			<ul id="tree">
			</ul>
			<div id="mm" class="easyui-menu" style="width:120px;">
				<div onclick="doAdd()" data-options="iconCls:'icon-add'">新增子菜单</div>
				<div class="menu-sep"></div>
				<div onclick="doEdit()" data-options="iconCls:'icon-edit'">编辑菜单</div>
				<div class="menu-sep"></div>
				<div onclick="doRemove()" data-options="iconCls:'icon-remove'">删除</div>
			</div>
		</div>
		<div id="content" region="center" title="详细信息" style="padding:5px;">
			<div class="easyui-panel" title="菜单信息" style="width:400px">
				<div style="padding:10px 60px 20px 60px">
					<form id="form" method="post">
						<table cellpadding="5">
							<tr>
								<td>菜单代码:</td>
								<td><input class="easyui-textbox" type="text" name="code" readonly="true"
									style="width:100%;"></input></td>
							</tr>
							<tr>
								<td>菜单名称:</td>
								<td><input class="easyui-textbox" type="text" name="name" readonly="true"
									style="width:100%;"></input></td>
							</tr>
							<tr>
								<td>菜单描述:</td>
								<td><input class="easyui-textbox" type="text" readonly="true"
									name="description" style="width:100%;" name="subject"></input>
								</td>
							</tr>
							<tr>
								<td>菜单路径:</td>
								<td><input class="easyui-textbox" name="url" readonly="true"
									style="width:100%;" style="height:60px"></input></td>
							</tr>
							<tr>
								<td>菜单图标:</td>
								<td><input class="easyui-textbox" name="icon" readonly="true"
									style="width:100%;" style="height:60px"></input></td>
							</tr>
							<tr>
								<td>类型:</td>
								<td><select class="easyui-combobox" name="menutype" readonly="true"
									style="width:100%;">
										<option value="1">菜单</option>
										<option value="2">操作</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>排序:</td>
								<td><input class="easyui-textbox" name="priority" readonly="true"
									style="width:100%;" style="height:60px"></input></td>
							</tr>
						</table>
					</form>
				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
	var tree=$('#tree');
			tree.tree({
			url : "${pageContext.request.contextPath}/menu/getAllMenuList.do",
			idField   : "menuid",
			textField : "name",
			parentField : "parentmenuid",
			method : 'get',
			animate : true,
			lines : true,
			onClick : function(node) {
				$('#form').form('load', node);
			},
			onContextMenu : function(e, node) {
				e.preventDefault();
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
				title : '新增菜单',
				width : 580,
				height : 400,
				modal: true,
				href:'${pageContext.request.contextPath}/pages/menu/menu_add.jsp',
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						$('#ff').form('submit', {    
							url : "${pageContext.request.contextPath}/menu/add.do",  
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
								parentmenuid : node.menuid,
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
				title : '编辑菜单',
				width : 580,
				height : 400,
				modal: true,
				href:'${pageContext.request.contextPath}/pages/menu/menu_edit.jsp',
				onLoad:function(){
					var f = $("#ff");
					f.form("load", node);
				},	
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						$('#ff').form('submit', {    
							url : "${pageContext.request.contextPath}/menu/edit.do",  
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
								menuid : node.menuid,
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
										code:result.data.code,
										description:result.data.description,
										url:result.data.url,
										menutype:result.data.menutype,
										priority:result.data.priority,
										icon:result.data.icon
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
			    	$.messager.progress({
						title : '提示',
						text : '数据加载中，请稍候....'
					});
			    	$.ajax({
						url:"${pageContext.request.contextPath}/menu/remove.do",
						type:"post",
						data:{menuid:node.menuid},
						dataType:"json",
						
						success:function(result){
							
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
	</script>
</body>
</html>
