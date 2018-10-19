<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<table id="datagrid3"  
		>
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'name',width:100">用户名</th>
				<th data-options="field:'user_type',width:100">用户类型</th>
				<th data-options="field:'loginname',width:100">登陆名</th>
				<th data-options="field:'qq',width:100">QQ</th>
				<th data-options="field:'email',width:100">EMAIL</th>
				<th data-options="field:'status',width:100,formatter:statusformatter">用户状态</th>
			</tr>
		</thead>
	</table>
