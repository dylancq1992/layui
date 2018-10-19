<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>404错误</title>
    
<link href="${pageContext.request.contextPath}/resource/css/error.css?<%=System.currentTimeMillis() %>"rel="stylesheet" type="text/css" />

  </head>
  
  <body>
    <!-- 代码 开始 -->
<div id="container"><img class="png" src="${pageContext.request.contextPath}/resource/images/404.png" /> <img class="png msg" src="${pageContext.request.contextPath}/resource/images/404_msg.png" />
  <p> </p>
</div>
<div id="cloud" class="png"></div>
<!-- 代码 结束 -->
  </body>
</html>
