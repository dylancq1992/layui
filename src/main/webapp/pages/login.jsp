<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>登录页面</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script
	src="${pageContext.request.contextPath}/resource/js/easyui/jquery.min.js"
	type="text/javascript"></script>
	
<script
	src="${pageContext.request.contextPath}/resource/js/easyui/jquery-jrumble.js"
	type="text/javascript"></script>
		
<link
	href="${pageContext.request.contextPath}/resource/css/login.css?<%=System.currentTimeMillis() %>"
	rel="stylesheet" type="text/css" />

</head>
<body>
	<script type="text/javascript">
	
	//控制登陆窗口不能在frame中显示 
	if(top.location!==self.location){ 
	top.location.href=self.location.href; 
	} 
	
		document.onkeydown=function(event){ 
		e = event ? event :(window.event ? window.event : null); 
		if(e.keyCode==13){ 
		//执行的方法 
			doLogin();
		} 
		}; 
	
		function reloadcode(){
		    var verify=document.getElementById("newcode");
		    verify.src = '${pageContext.request.contextPath}/getCode?id=' + Math.random();
		}
		
		function doLogin(){
			
			var loginname=$("#loginname").val();
			var password=$("#password").val();
			var code=$("#code").val();
			
			if(loginname==""){
				$("#msgDiv").html("请输入用户名");
				jrumble('form');
				return;
			}
			
			if(password==""){
				$("#msgDiv").html("请输入密码");
				jrumble('form');
				return;
			}
			
			if(code==""){
				$("#msgDiv").html("请输入验证码");
				jrumble('form');
				return;
			}
			
			$('form').fadeOut(500);
			$('.wrapper').addClass('form-success');
			var loginForm=document.getElementById("loginForm");
			loginForm.action="${pageContext.request.contextPath}/login/doLogin.do";
			loginForm.submit();
		}
		
		// 表单晃动

		function jrumble(obj) {

			$('.'+obj).jrumble({

				x : 4,

				y : 0,

				rotation : 0

			});

			$('.'+obj).trigger('startRumble');

			setTimeout(function(){
				$('.'+obj).trigger("stopRumble");
			}, 500);

		}
	</script>


	<div class="htmleaf-container">
		<div class="wrapper">
			<div class="container">
				<h1>Welcome</h1>

				<form class="form" id="loginForm" method="post">
				<table style="margin: 0 auto;">
				<tr>
				<td align="center" colspan="2"><input type="text" id="loginname" name="loginname" placeholder="Username"> </td>
				</tr>
				
				<tr>
				<td align="center" colspan="2"><input
						type="password" placeholder="Password" name="password" id="password"></td>
				</tr>
				
				<tr>
				<td align="right">
				<input
						type="text" placeholder="" style="width: 141px" id="code" name="code">	
						</td>
						<td>
							<img id="newcode"  src="${pageContext.request.contextPath}/getCode" onclick="reloadcode()" title="看不清楚,换一张"/>
						
						</td>
				</tr>
				
				<tr>
				<td align="center" colspan="2"><button type="button"  onclick="doLogin()">Login</button></td>
				</tr>
				
				</table>
					
					<div id="msgDiv" class="login_02 msgDiv" style="margin-top: 10px; ">
			                	${errmsg}
			        </div>
					
				</form>
			</div>

			<ul class="bg-bubbles">
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
	</div>
	<div
		style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
		<h1>后台管理系统</h1>
	</div>
</body>
</html>
