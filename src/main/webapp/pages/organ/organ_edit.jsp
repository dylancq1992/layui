<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>


<form id="ff" method="post">
	<table cellpadding="5" style="margin: 0 auto;margin-top: 10px">
		<tr>
			<td>组织代码:</td>
			<td><input class="easyui-textbox" type="text" name="code" disabled="true"
				style="width:100%;"></input>
			</td>
		</tr>
		<tr>
			<td>组织名称:</td>
			<td><input class="easyui-textbox" type="text" name="name"
				style="width:100%;" data-options="required:true"></input>
			</td>
		</tr>
		<tr>
			<td>机构等级:</td>
			<td>
				<input  class="easyui-combobox" name="grade"   style="width:100%;"
   					 data-options="required:true,valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/resource/dictionary/grade.json'" />  
				
				</td>
		</tr>
		<tr>
			<td>行政区划:</td>
			<td><input  name="districtc_code" id="districtc_code" class="easyui-combotree"
				 style="width:100%;" style="height:60px"
			data-options="required:true,idField:'id',textField:'name',parentField:'parentid',method:'get',animate:true,lines:true,
			url:'${pageContext.request.contextPath}/districtc_code/getAllDistrictcCodeList.do'"	 
				 ></input>
			</td>
		</tr>
		<tr>
			<td>组织描述:</td>
			<td><input class="easyui-numberbox" name="description"
				style="width:100%;" style="height:60px"></input>
			</td>
		</tr>
	</table>
</form>

