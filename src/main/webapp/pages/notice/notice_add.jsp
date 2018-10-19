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


.table {
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	border: 1px solid #AED0EA;
	width: 98%;
	margin:2px;
}

.table th {
	width: 70px;
	height: 30px;
	text-align: right;
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
		
			<table id='table40' class='table'>
				<tr>
					<th>标题</th>
					<td ><input name='title' id='title' data-options="required:true"
						class='easyui-textbox ' type='text' 
						style="width:98%;" /></td>
				</tr>
				
				<tr>
					<th>收件单位</th>
					<td >
						
						<input id="address" name='address' style="width:98%;">
						
						</td>
				</tr>
				
				<tr>
					<th>内容</th>
					<td >
					<textarea id="editor" name="content" style="width:98%;height:500px;"></textarea>
				</tr>
				
			</table>

	</form>
</div>

<script type="text/javascript">


	$('#address').combotree({
		required : true,
		url : "${pageContext.request.contextPath}/organ/getAllOrganList.do",
		idField   : "organid",
		textField : "name",
		parentField : "parentorganid",
		method : 'get',
		multiple:true, //这个选项设置多选功能
		checkbox:true,
		animate : true,
		lines : true
	});

	//实例化编辑器
	//简单模式初始化
	var editor;

	editor = KindEditor
			.create(
					'#editor',
					{
						resizeType : 1,
						allowPreviewEmoticons : false,
						allowImageUpload : true,
						uploadJson : "${pageContext.request.contextPath}/notice/uploadImg.do",//需要加上basePath, 不然批量上传调用action时会出现路径问题
						fileManagerJson : '${pageContext.request.contextPath}/resource/js/kindeditor/jsp/file_manager_json.jsp',
						items : [ 'undo', 'redo', '|', 'fontname', 'fontsize',
								'|', 'forecolor', 'hilitecolor', 'bold',
								'italic', 'underline', 'removeformat', '|',
								'justifyleft', 'justifycenter', 'justifyright',
								'insertorderedlist', 'insertunorderedlist',
								'|', 'emoticons', 'image', 'link', 'print' ]
					});
</script>

