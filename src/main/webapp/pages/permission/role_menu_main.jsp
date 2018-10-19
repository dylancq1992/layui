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
<title>角色管理</title>

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
		<div region="west" split="true" title="角色列表" style="width:70%;overflow:hidden;">
		<div style="width:100%;height:100%">
			<table id="datagrid"  style="width:100%;height:100%"  >
		<thead>
			<tr>
				<th data-options="field:'code',width:150">角色代码</th>
				<th data-options="field:'name',width:200">角色名称</th>
				<th data-options="field:'description',width:300">描述</th>
			</tr>
		</thead>
			</table>
			</div>
		</div>
		<div id="content" region="center" title="菜单信息" >
		<div style="height: 93%">
		<ul id="tree" class="easyui-tree" data-options="url : '${pageContext.request.contextPath}/permission/getMenuListByRoleid.do',
			idField  : 'menuid',textField : 'name',parentField : 'parentmenuid',checkbox:true,method : 'get',animate : true,lines : true" >
			</ul>
		</div><%--
			分配方式：
			<input  class="easyui-combobox" name="sex"   
   					 data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/resource/dictionary/grantable.json'" />  
   					 
			--%><a href="javascript:void(0);" class="easyui-linkbutton" onClick="doSave()"
			data-options="iconCls:'icon-save',plain:false">保存</a>
			
		</div>
	</div>
	

	<script type="text/javascript">
	
	$("#datagrid").datagrid({
		url:'${pageContext.request.contextPath}/role/getAllRoleList.do',
		fitColumns:false,
		pagination:true,
		rownumbers:true,
		idField:"roleid",
		border:0,
		striped:true,
		singleSelect:true,
		pageSize : 20,
		pageList : [10,20,30,40,50],
		onClickRow: function(rowIndex, rowData){
			var params = {
				    roleid: rowData.roleid
				};
			$("#tree").tree("options").queryParams = params;
			$("#tree").tree('reload');
		}
		
	});
	
	
	
	function doSave(){
		
		var nodes = $("#tree").tree('getChecked',['checked','indeterminate']);
		
		var row=$("#datagrid").datagrid('getSelected');
		if(!row){
			$.messager.alert({
				title:"提示",
				msg:"请选择角色！",
				icon:"warning"
			}); 
			return;
		}
        
		var ids=[];
		var isLeaf=[];
		for(var i=0;i<nodes.length;i++){
			ids.push(nodes[i].menuid);
			var isLeafs=$('#tree').tree('isLeaf', nodes[i].target);
			if(isLeafs){
				isLeaf.push(1);
			}else{
				isLeaf.push(0);
			}
		}
		$.messager.progress({
			title : '提示',
			text : '数据加载中，请稍候....'
		});
		$.ajax({
			url:"${pageContext.request.contextPath}/permission/role_menu_save.do",
			type:"post",
			data:{roleid:row.roleid,ids:ids.join(","),isLeaf:isLeaf.join(",")},
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
				}  
				
			},error:function(jqXHR, textStatus, errorThrown){
				//关闭遮罩
				$.messager.progress('close');
				alert(jqXHR.responseText);
			}
		});
		
	}
	
	</script>
</body>
</html>
