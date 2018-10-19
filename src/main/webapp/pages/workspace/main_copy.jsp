<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.vo.SessionConstants"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>后台</title>
<link href="${pageContext.request.contextPath}/resource/css/welcome.css?<%=System.currentTimeMillis() %>"
			rel="stylesheet" type="text/css" />
<!-- 引入JQuery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/js/boot.js"></script>
</head>

<body class="easyui-layout" >
	<div region="north" border="false" class="group wrap header"
		style="font-size:100%;overflow:hidden">
		
		<div id="hd">
    	<div class="hd-top">
            <h1 class="logo"></h1>
            <div class="user-info">
                <a href="javascript:;" class="user-avatar"><span><i class="info-num">2</i></span></a>
                <span class="user-name"><%= session.getAttribute(SessionConstants.WSSIP_OPERATOR_LOGINNAME) %></span>
                <a href="javascript:;" class="more-info"></a>
            </div>
            <div class="setting ue-clear">
            	<div class="setting-skin">
                	<input id="skin"  />
                </div>
                <ul class="setting-main ue-clear">
                	<li><img class="update-pwd" src="${pageContext.request.contextPath}/resource/images/key.png" /><a href="javascript:void(0);" onclick="updatePwd()">修改密码</a></li>
                    <li><img class="update-pwd" src="${pageContext.request.contextPath}/resource/images/cog.png" /><a href="javascript:void(0);">设置</a></li>
                    <li><img class="update-pwd" src="${pageContext.request.contextPath}/resource/images/help.png" /><a href="javascript:void(0);">帮助</a></li>
                    <li><a href="javascript:void(0);" class="close-btn exit" onclick="doExit()"></a></li>
                </ul>
            </div>
        </div>
        <div class="hd-bottom">
        	<i class="home"><a href="javascript:;"></a></i>
        	<div class="nav-wrap">
                <ul class="nav ue-clear" id="menulist">
                <c:forEach items="${topMenuList}" var="li">
 					<li><a href="javascript:void(0);" onclick="doAuthLeft('${li.menuid }')">${li.name }</a></li>
				</c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
		<div region="west" split="true" style="width:210px;">
		
	
			<iframe scrolling="auto" frameborder="0" id="leftIframe"   style="width:100%;height:100%;"></iframe>
	
		</div>
	<div region="center" border="false" border="false" style="width:100%;height: 100%;">
		<div class="easyui-tabs" fit="true" id="tt">
			<div title="首页" >
				<iframe scrolling="auto" frameborder="0" id="homeIframe"   style="width:100%;height:100%;"></iframe>
			</div>
		</div>
	</div>
	<div region="south" border="false"
		style="height:30px;">
		
		<div class="icon_welcome">
		欢迎登录：<%= session.getAttribute(SessionConstants.WSSIP_OPERATOR_LOGINNAME) %>
			
		</div>	
		<div class="copyright">版权所有 @wanghongwei</div>
	</div>

	<script type="text/javascript">
	
	$(function(){
		var first = $("#menulist li:first a");
        first.click();
        $("#homeIframe").attr("src","${pageContext.request.contextPath}/pages/workspace/home.jsp");
	});
	
	
	$('#skin').combobox({
		width:'100px',
		valueField: 'label',
		textField: 'value',
		data: [{
			label: 'default',
			value: 'default'
		},{
			label: 'bootstrap',
			value: 'bootstrap'
		},{
			label: 'black',
			value: 'black'
		}],
		onChange: function (n,o) {

			changeTheme(n);
		}
	});
	
	

	/**
	 * 更换EasyUI主题的方法
	 * @param themeName
	 * 主题名称
	 */
	changeTheme = function(themeName) {
		var $easyuiTheme = $('#easyuiTheme');
		var url = $easyuiTheme.attr('href');
		var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
		$easyuiTheme.attr('href', href);
		var $iframe = $('iframe');
		if ($iframe.length > 0) {
			for ( var i = 0; i < $iframe.length; i++) {
				var ifr = $iframe[i];
				$(ifr).contents().find('#easyuiTheme').attr('href', href);
			}
		}
		$.cookie('skin', themeName, {
			expires : 7
		});
	};
	
	if($.cookie('skin')){
		$('#skin').combobox('setValue', $.cookie('skin')); 
	}
	
	function doAuthLeft(menuid){
		$.ajaxSetup ({ cache: false });
		$("#leftIframe").attr("src","${pageContext.request.contextPath}/workspace/getToLeft.do?menuid="+menuid);
	}
	
	function open1(plugin,url){
		if ($('#tt').tabs('exists',plugin)){
			$('#tt').tabs('select', plugin);
		} else {
			var content = "<iframe scrolling='auto' frameborder='0' class='newTabFrameArea'   src='<c:url value='/workspace/doCenter.do?url="+url+"'/>' style='width:100%;height:100%;'></iframe>";
			$('#tt').tabs('add',{
				title:plugin,
				content:content,
				closable:true,
				bodyCls:'content-doc'
			});
		}
	}

	function setMenu(e) {
		var menu_num=document.getElementById("menu");
		var len_num=menu_num.getElementsByTagName("li");
		for(var i=0; i<len_num.length;i++){
			len_num[i].className="";
		}
		var obj = document.getElementById("li" + e);
		obj.className="current";
	}

	function showTab(menuid,url,menuname){
		open1(menuname,url);
	}
	
	
	function doExit(){
		openDialog({
			width : 400,
			height :200,
			modal: true,
			resizable:false,
			href:'${pageContext.request.contextPath}/pages/workspace/login_out.jsp'
		});
	}
	
	function doCancel(){
		closeDialog();
	}
	
	function doLoingout(){
		window.location.href = '<c:url value="/loginout/doLoginOut.do"/>';
	}
	
	
	function updatePwd(){
		openDialog({
			title:"修改密码",
			width : 400,
			height :320,
			modal: true,
			resizable:false,
			href:'${pageContext.request.contextPath}/pages/workspace/updatepwd.jsp',
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					$('#update_ff').form('submit', {    
						url : "${pageContext.request.contextPath}/workspace/updatepwd.do",  
						onSubmit : function(param) {
							
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
					    success:function(data){    
					    	//关闭遮罩
							$.messager.progress('close');

							var result = eval("(" + data + ")");
							if (!result.success) {
								$.messager.alert({
									title : '提示',
									msg : "保存失败," + result.message,
									icon:"warning"
								});
							} else {
								$.messager.show({
									title : "提示",
									msg : "保存成功",
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
	</script>
</body>
</html>
