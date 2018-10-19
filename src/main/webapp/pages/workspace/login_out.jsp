<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>退出页</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/start.js"></script>
    <link href="${pageContext.request.contextPath}/resource/css/welcome.css?<%=System.currentTimeMillis() %>"
			rel="stylesheet" type="text/css" />
  </head>
  
  <body>
   <div class="exitDialog">
	<div class="content">
    	<div class="ui-dialog-icon"></div>
        <div class="ui-dialog-text">
        	<p class="dialog-content">你确定要退出系统？</p>
            <p class="tips">如果是请点击“确定”，否则点“取消”</p>
            
            <div class="buttons">
                <input type="button" class="button long2 ok" value="确定" onclick="doLoingout()" />
                <input type="button" class="button long2 normal" value="取消" onclick="doCancel()" />
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

function doCancel(){
	x_admin_close();
}

function doLoingout(){
	window.top.location.href = '<c:url value="/loginout/doLoginOut.do"/>';
}

</script>
  </body>
</html>
