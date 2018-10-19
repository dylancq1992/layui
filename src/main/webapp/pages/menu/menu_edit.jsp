<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>


<form id="ff" method="post">
	<table cellpadding="5" style="margin: 0 auto;margin-top: 10px">
		<tr>
			<td>菜单代码:</td>
			<td><input class="easyui-textbox" type="text" name="code"
				style="width:100%;" data-options="required:true"></input>
			</td>
		</tr>
		<tr>
			<td>菜单名称:</td>
			<td><input class="easyui-textbox" type="text" name="name" id="name"
				style="width:100%;" data-options="required:true"></input>
			</td>
		</tr>
		<tr>
			<td>菜单描述:</td>
			<td><input class="easyui-textbox" type="text" name="description"
				style="width:100%;" name="subject"></input></td>
		</tr>
		<tr>
			<td>菜单路径:</td>
			<td><input class="easyui-textbox" name="url"
				 style="width:100%;" style="height:60px"></input>
			</td>
		</tr>
		<tr>
			<td>菜单图标:</td>
			<td><input class="easyui-textbox" name="icon"
				 style="width:100%;" style="height:60px"></input>
			</td>
		</tr>
		<tr>
			<td>类型:</td>
			<td><select class="easyui-combobox" name="menutype"
				data-options="required:true" style="width:100%;">
					<option value="1">菜单</option>
					<option value="2">按钮</option>
			</select></td>
		</tr>
		<tr>
			<td>排序:</td>
			<td><input class="easyui-numberbox" name="priority"
				style="width:100%;" style="height:60px"></input>
			</td>
		</tr>
	</table>
</form>
