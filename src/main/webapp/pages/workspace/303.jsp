<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>303 错误信息</title>
    

  </head>
  
  <body>
    	没有权限访问<a href="${pageContext.request.contextPath}/login/loginout.do" target="_top">切换账号登录？</a>
  </body>
</html>
