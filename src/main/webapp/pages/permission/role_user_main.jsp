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
	
	
		<div region="west" split="true" title="机构信息列表" style="width:300px;">
			<ul id="tree">
			</ul>
		</div>
		
		<div id="content" region="center" title="详细信息" style="overflow:hidden;">
	
		<table id="datagrid1"  style="width:100%;height:100%"  idField="userid" 
		data-options="url:'${pageContext.request.contextPath}/user/getUserByOrgan.do',fitColumns:true,
		pagination:true,rownumbers:true,border:false,striped:true,singleSelect:true,pageSize : 20,pageList : [10,20,30,40,50]">
		<thead>
			<tr>
				<th data-options="field:'name',width:150">用户名</th>
				<th data-options="field:'loginname',width:100">登陆名</th>
				<th data-options="field:'qq',width:100">QQ</th>
				<th data-options="field:'email',width:100">EMAIL</th>
				<th data-options="field:'status',width:100,formatter:statusformatter">用户状态</th>
			</tr>
		</thead>
		</table>
	
				
		</div>
		
		<div id="east" region="east" title="角色列表" style="width: 480px">
		
		<table id="datagrid2" class="easyui-datagrid" style="width:100%;height:100%" toolbar='#tb' idField="roleid" 
		data-options="url:'${pageContext.request.contextPath}/permission/getRoleListByUserid.do',fitColumns:false,
		pagination:true,rownumbers:true,border:false,striped:true,pageSize : 20,pageList : [10,20,30,40,50]">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'code',width:100">角色代码</th>
				<th data-options="field:'name',width:200">角色名称</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<a href="javascript:void(0);" class="easyui-linkbutton" onClick="doAdd()"
			data-options="iconCls:'icon-add',plain:true">添加</a>
		<a
			href="javascript:void(0);" class="easyui-linkbutton" onClick="doRemove()"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
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
				$('#datagrid1').datagrid('clearSelections');
				$('#datagrid1').datagrid('load',{
					organid: node.organid
				});
			}
		});

			$('#datagrid1').datagrid({
				onClickRow:function(rowIndex, rowData){
					
					$('#datagrid2').datagrid('load',{
						userid: rowData.userid
					});
					
				}
			});
	
			
			function doAdd(){
				
				var row = $('#datagrid1').datagrid('getSelected');
				if (!row){
					$.messager.alert({
						title : '提示',
						msg : "请选择用户！",
						icon:"warning"
					});
					return;
				}
				
				openDialog({
					title : '新增用户角色',
					width : 780,
					height : 500,
					modal: true,
					href:'${pageContext.request.contextPath}/pages/permission/role_user_add.jsp',
					onLoad:function(){
						$('#datagrid3').datagrid({
						    url:'${pageContext.request.contextPath}/permission/getRoleByUserid.do',
						    fitColumns:false,
							rownumbers:true,
							border:false,
							striped:true,
							queryParams: {
								userid: row.userid
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
								msg : "请选择角色！"
							});
							return;
						}
						
						var ids=[];
						for(var i=0;i<rows.length;i++){
							ids.push(rows[i].roleid);
						}
						
						$.messager.progress({
							title : '提示',
							text : '数据加载中，请稍候....'
						});
						$.ajax({
							url:"${pageContext.request.contextPath}/permission/role_user_save.do",
							type:"post",
							data:{userid:row.userid,ids:ids.join(",")},
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
									
									$('#datagrid2').datagrid('reload');
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
	
			
			function doRemove(){
				
				var rows = $('#datagrid2').datagrid('getSelections');
				
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
						arrFileid.push(rows[i].roleid);
				}
				var row = $('#datagrid1').datagrid('getSelected');
				
				
				var data={ids:arrFileid.join("','"),userid:row.userid};
				
				$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
				    if (r){    
				    	
				    	$.messager.progress({
							title : '提示',
							text : '数据加载中，请稍候....'
						});
				    	
				    	$.ajax({
							url:"${pageContext.request.contextPath}/permission/remove_role_user.do",
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
									reloaddatagrid('datagrid2');
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
