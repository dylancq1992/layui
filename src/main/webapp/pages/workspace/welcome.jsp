<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
    
   		 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/font.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/js/layui/css/xadmin.css">
		 <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/start.js"></script>
		 <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/utils/navCal.js"></script>
		 <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/echarts/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/echarts/macarons.js"></script>	
  </head>
  
 <body>
    <div class="x-body layui-anim layui-anim-up">
        <blockquote class="layui-elem-quote">
            <p id="yldate"></p><!-- 2016年04月21日     星期一      -->
            <p id="nldate"></p><!-- 农历丙申（猴）年    四月初一    -->
            
            </blockquote>
        <fieldset class="layui-elem-field">
            <legend>数据统计</legend>
            <div class="layui-field-box">
              <div id="chart1" style="width:80%;height:200px;"></div>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>通知公告</legend>
            <div class="layui-field-box">
                <table class="layui-table" lay-skin="line">
                    <tbody id="tbody">
                      
                    </tbody>
                </table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>系统信息</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th>xxx版本</th>
                            <td>1.0.180420</td></tr>
                        <tr>
                            <th>服务器地址</th>
                            <td>x.xuebingsi.com</td></tr>
                        <tr>
                            <th>操作系统</th>
                            <td>WINNT</td></tr>
                        <tr>
                            <th>运行环境</th>
                            <td>Apache/2.4.23 (Win32) OpenSSL/1.0.2j mod_fcgid/2.3.9</td></tr>
                        <tr>
                            <th>PHP版本</th>
                            <td>5.6.27</td></tr>
                        <tr>
                            <th>PHP运行方式</th>
                            <td>cgi-fcgi</td></tr>
                        <tr>
                            <th>MYSQL版本</th>
                            <td>5.5.53</td></tr>
                        <tr>
                            <th>ThinkPHP</th>
                            <td>5.0.18</td></tr>
                        <tr>
                            <th>上传附件限制</th>
                            <td>2M</td></tr>
                        <tr>
                            <th>执行时间限制</th>
                            <td>30s</td></tr>
                        <tr>
                            <th>剩余空间</th>
                            <td>86015.2M</td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>开发团队</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th>版权所有</th>
                            <td>xxxxx(xxxx)
                                <a href="http://www.xxx.com/" class='x-a' target="_blank">访问官网</a></td>
                        </tr>
                        <tr>
                            <th>开发者</th>
                            <td>马志斌(113664000@qq.com)</td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <blockquote class="layui-elem-quote layui-quote-nm">感谢layui,百度Echarts,jquery,本系统由x-admin提供技术支持。</blockquote>
    </div>
        <script>
        var nldate=showCal();
    	var yldate=GetCurrentDateTime();
    	$("#nldate").html(nldate);
    	$("#yldate").html(yldate);
    	
    	var chart1 = echarts.init(document.getElementById('chart1'),'macarons');
    	getLoginCount();
    	function getLoginCount(){
    		$.ajax({
    			url:"${pageContext.request.contextPath}/workspace/getLoginCount.do",
    			type:"post",
    			data:"",
    			dataType:"json",
    			success:function(result){
    				
    				if(result.success){
    					shwoChart1(result.data);
    				}
    				
    			},error:function(jqXHR, textStatus, errorThrown){
    				//关闭遮罩
    				$.messager.progress('close');
    				alert(jqXHR.responseText);
    			}
    		});
    	}

    	function shwoChart1(data){
    		var option = {
    				    tooltip : {
    				        trigger: 'axis',
    				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    				            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
    				        }
    				    },
    				    toolbox: {
    						show : true,
    						feature : {
    							mark : {show: true},
    							magicType : {show: true, type: ['line', 'bar']},
    							saveAsImage : {show: true}
    						}
    					},
    			    xAxis: {
    			        type: 'category',
    			        data: data.categories,
    			        "axisLabel":{
    			    		interval: 0 //横坐标全部显示
    			    	},
    			    	boundaryGap:false //boundaryGap属性，设置为false代表是零刻度开始，设置为true代表离零刻度间隔一段距离
    			    },
    			    yAxis: {
    			        type: 'value'
    			    },
    			    series: [{
    			        data: data.data,
    			        type: 'line'
    			    }]
    			};
    		chart1.clear();
    		chart1.setOption(option);
    	}
    	
    	getNotice();
    	function getNotice(){
    		$.ajax({
    			url:"${pageContext.request.contextPath}/workspace/getNotice.do",
    			type:"post",
    			data:"",
    			dataType:"json",
    			success:function(result){
    				
    				if(result.success){
    					//console.log(result.data);
    					var str="";
    					for(var i=0;i<result.data.length;i++){
    						str+="<tr><td ><a class='x-a' href='javascript:void(0)' onclick=openNotice('"+(result.data)[i].noticeid+"') target='_blank'>"+(result.data)[i].title+"</a></td> </tr>";
    					}
    					$("#tbody").html(str);
    				}
    				
    			},error:function(jqXHR, textStatus, errorThrown){
    				
    				alert(jqXHR.responseText);
    			}
    		});
    	}
    	
    	function openNotice(noticeid){
    		alert(noticeid);
    	}
        </script>
    </body>
</html>
