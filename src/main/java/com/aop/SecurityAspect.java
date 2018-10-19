package com.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.vo.SessionConstants;
import com.vo.WebResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 安全认证的aop拦截器 Created by Raye on 2017/3/29.
 */
@Aspect
@Component
public class SecurityAspect {
	
	private static Logger logger = Logger.getLogger(SecurityAspect.class);  
	
	/** 登录的地址，不对登录进行拦截 */
	private static final String LOGINURL = "/login/doLogin.do";
	/** 注销的地址，不对注销进行拦截 */
	private static final String LOGINOUTURL = "/loginout/doLoginOut.do";

	@Pointcut("execution(public * com.controller..*.*(..))")
	public void verification() {
	}


	@Around("verification()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();

		HttpServletRequest request = attributes.getRequest();
		HttpSession session = request.getSession();
		
		HttpServletResponse response=attributes.getResponse();

		//String url = request.getScheme() + "://" + request.getServerName()+ request.getRequestURI() + "?" + request.getQueryString();
		//System.out.println("获取全路径（协议类型：//域名/项目名/命名空间/action名称?其他参数）url=" + url);
		//String url2 = request.getScheme() + "://" + request.getServerName();// +request.getRequestURI();
		//System.out.println("协议名：//域名=" + url2);

		//System.out.println("获取项目名=" + request.getContextPath());
		//System.out.println("获取参数=" + request.getQueryString());
		//System.out.println("获取全路径=" + request.getRequestURL());
		//request.getSession().getServletContext().getRealPath("/");// 获取web项目的路径
		
		//logger.info(request.getContextPath());
		
		String workname=request.getContextPath();

		String uri = request.getRequestURI();
		if (uri.lastIndexOf("/") == uri.length() - 1) {
			uri = uri.substring(0, uri.length() - 1);
		}
		System.out.println("进入拦截器:" + uri);
		if ((workname+LOGINURL).equals(uri) || (workname+LOGINOUTURL).equals(uri)) {
			// log.info("不拦截");
			// 不拦截的
			return pjp.proceed();
		}
		
		
		if ((session.getAttribute(SessionConstants.WSSIP_OPERATOR) == null || session.getAttribute("topMenuList") == null)) {
			
			if( request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals("XMLHttpRequest")  ) {
				   response.setHeader("sessionstatus", "timeout");
				   return null;

			}
			session.setAttribute("errmsg", WebResult.NOLOGINMSG);
			return "redirect:/pages/login.jsp";
		}
		
		return pjp.proceed();

	}
}
