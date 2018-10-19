<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>


<form id="update_ff" method="post">
	<table cellpadding="5" style="margin: 0 auto;margin-top: 10px">
		<tr>
			<td>旧密码:</td>
			<td><input class="easyui-passwordbox" type="text" name="password"
				style="width:100%;" data-options="required:true"></input>
			</td>
		</tr>
		<tr>
			<td>新密码：</td>
					<td><input name='newpassword' id='newpassword' style="width:100%;" data-options="required:true,validType:['pwdvalidate','length[6,12]']"  
						class='easyui-passwordbox easyui-validatebox' type='text' /></td>
		</tr>
		<tr>
			<td>确认密码：</td>
					<td><input name='password1' id='password1' style="width:100%;" data-options="required:true" validType="equals['#newpassword']"
						class='easyui-passwordbox easyui-validatebox' type='text' />
						</td>
		</tr>
		<tr>
		<td >
		</td>
		<td>
		温馨提示：<br>
1. 必须大于等于6位；<br>
2. 必须包含数字、英文字母、<br>特殊符号中的两种或两种以上；<br>
3. 特殊符号包括: !@#^&*<br>
		</td>
		</tr>
	</table>
</form>
