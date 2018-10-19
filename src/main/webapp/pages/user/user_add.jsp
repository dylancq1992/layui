<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<style>
.easyui-textbox {
	height: 20px;
	width: 170px;
	line-height: 16px;
	border-radius: 3px 3px 3px 3px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
}

textarea:focus,input[type="text"]:focus {
	border-color: rgba(82, 168, 236, 0.8);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px
		rgba(82, 168, 236, 0.6);
	outline: 0 none;
}

fieldset {
	border: 1px solid #E3E3E3;
}

legend {
	font-weight: Bold;
}

input,textarea {
	font-weight: normal;
}

.table {
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	border: 1px solid #AED0EA;
	width: 750px;
}

.table th {
	width: 100px;
	height: 30px;
	text-align: left;
	border: 1px solid #AED0EA;
	padding: 5px 8px 5px 8px;
	font-weight: normal;
}

.table td {
	text-align: left;
	border: 1px solid #AED0EA;
	padding: 5px 8px 5px 8px;
}


</style>
<div data-options="region:'center',border:false" title="">
	<form id="ff" method="post">
		<fieldset>
			<legend>基础信息</legend>
			<table id='table40' class='table'>
				<tr>
					<th style="width:100px;" ><span style="color: red;">*</span>姓名：</th>
					<td style="width:200px;"><input name='name' id='name' data-options="required:true"
						class='easyui-textbox' type='text' /></td>
					<th style="width:100px;"><span style="color: red;">*</span>登录名：</th>
					<td ><input name='loginname' id='loginname' data-options="required:true"
						class='easyui-textbox' type='text' /></td>

				</tr>
				<tr>
					<th ><span style="color: red;">*</span>性别：</th>
					<td >
						<input  class="easyui-combobox" name="sex"   
   					 data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/resource/dictionary/sex.json'" />  
						</td>
						<th><span style="color: red;">*</span>移动电话：</th>
					<td><input name='mobile_phone' id='mobile_phone' data-options="required:true,validType:['mobilephone']" 
						class='easyui-textbox easyui-validatebox' type='text' /></td>

				</tr>
				<tr>
					<th><span style="color: red;">*</span>密码：</th>
					<td><input name='password' id='password' data-options="required:true,validType:['pwdvalidate','length[6,12]']"  
						class='easyui-passwordbox easyui-validatebox' type='text' /></td>
					<th><span style="color: red;">*</span>确认密码：</th>
					<td><input name='password1' id='password1' data-options="required:true" validType="equals['#password']"
						class='easyui-passwordbox easyui-validatebox' type='text' />
						</td>
				</tr>

				<tr>
					<th>所在单位</th>
					<td colspan='3'>
						<input  name="organid" class="easyui-combobox"
						  data-options="width:'500px',mode:'remote',url:'${pageContext.request.contextPath}/organ/getOrganByName.do',
						   valueField:'organid',textField:'name' "/>
						</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset>
			<legend>其他信息</legend>
			<table id='table40' class='table'>
				<tr>
					<th style="width:100px;">电话(座机)：</th>
					<td style="width:200px;"><input name='telephone' id='telephone'
						class='easyui-textbox' type='text' /></td>
					<th style="width:100px;">QQ：</th>
					<td><input name='qq' id='qq'
						class='easyui-textbox' type='text' /></td>

				</tr>
				<tr>
					<th>传真：</th>
					<td><input name='fax' id='fax'
						class='easyui-textbox' type='text' /></td>
					<th>EMAIL：</th>
					<td><input name='email' id='email'
						class='easyui-textbox' type='text' data-options="validType:'email'" /></td>
				</tr>
				<tr>
					<th>绑定IP</th>
					<td colspan='3'><input name='bindip' id='bindip' validType="ipvalidate"
						class='easyui-textbox easyui-validatebox' type='text' invalidMessage="请输入正确的IP地址，若有多个IP请用英文逗号区分！"
						style="width:500px;" /></td>
				</tr>
				
				<tr>
					<th>地址</th>
					<td colspan='3'><input name='address' id='address'
						style="width:500px;" class='easyui-textbox'
						type='text' /></td>
				</tr>
			</table>
		</fieldset>

	</form>
</div>

