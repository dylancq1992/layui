<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>工作平台</title>
    <meta name="renderer" content="webkit">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/font.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/start.js"></script>
    


  </head>
  
  <body>
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="javascript:void(0);">权限系统</a></div>
        <div class="left_open">
            <i title="展开左侧栏" class="iconfont">&#xe699;</i>
        </div>
        <ul class="layui-nav left fast-add" lay-filter="" id="menulist">
          <li class="layui-nav-item">
            <a href="javascript:;">+新增</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a onclick="x_admin_show('资讯','http://www.baidu.com')"><i class="iconfont">&#xe6a2;</i>资讯</a></dd>
              <dd><a onclick="x_admin_show('图片','http://www.baidu.com')"><i class="iconfont">&#xe6a8;</i>图片</a></dd>
               <dd><a onclick="x_admin_show('用户','http://www.baidu.com')"><i class="iconfont">&#xe6b8;</i>用户</a></dd>
            </dl>
          </li>
          
           <c:forEach items="${topMenuList}" var="li">
 				<li class="layui-nav-item to-index"><a href="javascript:void(0);" onclick="doAuthLeft('${li.menuid }')">${li.name }</a></li>
			</c:forEach>
        </ul>
        
        <ul class="layui-nav right" lay-filter="" >
          <li class="layui-nav-item">
            <a href="javascript:;"><%= session.getAttribute(SessionConstants.WSSIP_OPERATOR_LOGINNAME) %></a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a onclick="x_admin_show('个人信息','http://www.baidu.com')">个人信息</a></dd>
              <dd><a onclick="x_admin_show('切换帐号','http://www.baidu.com')">切换帐号</a></dd>
              <dd><a href="javascript:void(0);"  onclick="doExit()">退出</a></dd>
            </dl>
          </li>
        </ul>
        
    </div>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
     <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">
           
           
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>首页</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='${pageContext.request.contextPath}/pages/workspace/welcome.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer">
        <div class="copyright">@Wanghongwei</div>  
    </div>
    <!-- 底部结束 -->
    <script>
    
    //默认加载第一个菜单
    $(function(){
		var first = $("#menulist li:nth-child(2) a");
			first.click();
	});
    
    //顶级菜单点击效果
   function doAuthLeft(menuid){
	   $.ajax({
			url:"${pageContext.request.contextPath}/workspace/getToLeft.do",
			type:"post",
			data:{menuid:menuid},
			dataType:"json",
			success:function(result){
			if (result.success) {
				var secondList=result.secondList;
				var thirdList=result.thirdList;
				var str="";
				for(var i=0;i<secondList.length;i++){
					str+="<li>";
					str+="<a href='javascript:void(0);'> <i class='iconfont'>"+secondList[i].icon+"</i> <cite>"+secondList[i].name+"</cite> <i class='iconfont nav_right'>&#xe697;</i> </a>";
					str+="<ul class='sub-menu'>";
						for(var j=0;j<thirdList.length;j++){
							if(secondList[i].menuid==thirdList[j].parentmenuid){
								str+="<li data-list="+thirdList[j].menuid+"> <a _href='"+thirdList[j].url+"'> <i class='iconfont'>&#xe6a7;</i> <cite>"+thirdList[j].name+"</cite> </a></li >";
							}
						}
					str+="</ul>";
					str+="</li>";
					
				}
				$("#nav").html(str);
			}  
				
			},error:function(jqXHR, textStatus, errorThrown){
				console.log(jqXHR+"----"+textStatus+"-----"+errorThrown);
			}
		});
    	
   }
    

   
   function doExit(){
	  // x_admin_show("确认退出","${pageContext.request.contextPath}/pages/workspace/login_out.jsp",450,260);
	 //页面层-自定义
	   layer.open({
			   type: 2,
			   area: ['450px', '260px'],
		       fix: false, //不固定
		       maxmin: false,
		       shadeClose: false,
		       shade:0.4,
		       title: "确认退出",
		       content: '${pageContext.request.contextPath}/pages/workspace/login_out.jsp'
		 });
   }
    </script>
</body>
</html>
