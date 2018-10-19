<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>


<form id="ff" method="post">
	<table cellpadding="5" style="margin: 0 auto;margin-top: 10px">
		<tr>
			<td>角色代码:</td>
			<td><input class="easyui-textbox" type="text" name="code"
				style="width:100%;" data-options="required:true"></input>
			</td>
		</tr>
		<tr>
			<td>角色名称:</td>
			<td><input class="easyui-textbox" type="text" name="name"
				style="width:100%;" data-options="required:true"></input>
			</td>
		</tr>
		<tr>
			<td>描述:</td>
			<td>
		<input class="easyui-textbox" name="description" data-options="multiline:true" style="width:300px;height:100px">
			</td>
		</tr>
	</table>
</form>
