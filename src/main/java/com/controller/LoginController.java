package com.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.BaseLoginLogs;
import com.bean.BaseMenu;
import com.bean.BaseUser;
import com.service.LoginLogsService;
import com.service.LoginService;
import com.sun.xml.internal.bind.v2.TODO;
import com.utils.IPSeeker;
import com.utils.SystemUtils;
import com.vo.SessionConstants;
import com.vo.UserVO;
import com.vo.WebResult;
import com.wondersgroup.framework.core5.business.context.BusinessContext;
import com.wondersgroup.framework.core5.business.context.Environment;
import com.wondersgroup.framework.core5.business.context.UserContext;
import com.wondersgroup.framework.core5.business.context.support.BusinessContextUtils;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static HashMap<String, Long> errorCountMap = new HashMap<String, Long>();
	private static HashMap<String, Long> errorTimeMap = new HashMap<String, Long>();
	private static HashMap<String, Long> lockTimeMap = new HashMap<String, Long>();

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Resource
	private LoginLogsService loginLogsService;

	@Resource
	private LoginService loginService;
	

	/**
	 * 进入登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/doLogin")
	public ModelAndView login(HttpServletRequest request, UserVO vo) {

		String certCode = (String) request.getSession()
				.getAttribute("certCode");

		if (StringUtils.isBlank(vo.getCode())) {
			request.getSession().setAttribute("errmsg",
					WebResult.INCORRECT_NULL_EXCEPTION);
			return new ModelAndView("login").addObject("errmsg", WebResult.INCORRECT_NULL_EXCEPTION);
		} else {
			if (!vo.getCode().equalsIgnoreCase(certCode)) {
				request.getSession().setAttribute("errmsg",
						WebResult.INCORRECT_CAPTCHA_EXCEPTION);
				return new ModelAndView("login").addObject("errmsg", WebResult.INCORRECT_CAPTCHA_EXCEPTION);
				
			}
		}
		
		//定时清理hashmap
		if(errorTimeMap.size()>10000){
			Iterator it = errorTimeMap.entrySet().iterator();
			while(it.hasNext()){
				it=errorTimeMap.entrySet().iterator();
				Entry<String,Long> entry = (Entry<String,Long>)it.next();
				if((System.currentTimeMillis() - entry.getValue())>=1200000L){
					errorTimeMap.remove(entry.getKey());
					errorCountMap.remove(entry.getKey());
				}
				if(lockTimeMap.containsKey(entry.getKey())){
					if((System.currentTimeMillis()-lockTimeMap.get(entry.getKey()))>=1200000L){
						lockTimeMap.remove(entry.getKey());
					}
				}
			}
		}
		
		if(lockTimeMap.containsKey(vo.getLoginname())){
			if((System.currentTimeMillis()-lockTimeMap.get(vo.getLoginname()))<1200000L){
				
				String errMessage="该用户名错误次数过多，请等"+(1200000L-System.currentTimeMillis()+lockTimeMap.get(vo.getLoginname()))/100+"秒";
				logger.error(errMessage);
				return new ModelAndView("login").addObject("errmsg", errMessage);
			}else{
				lockTimeMap.remove(vo.getLoginname());
			}
		}

		BaseUser baseUser = null;
		
		try {
			
			baseUser = loginService.getByLoginName(vo);

		} catch (Exception e) {
			
			if(!checkErrorCount(vo.getLoginname())){
				String errMessage="该用户名错误次数过多，请等"+1200000L/100+"秒";
				logger.error(errMessage);
				return new ModelAndView("login").addObject("errmsg", errMessage);
			}
			
			if(errorCountMap.get(vo.getLoginname())>25L){
				String errMessage="用户名错误次数过多，还剩最后"+(30L-errorCountMap.get(vo.getLoginname()))+"次";
				logger.error(errMessage);
				return new ModelAndView("login").addObject("errmsg", errMessage);
			}
			
			return new ModelAndView("login").addObject("errmsg", e.getMessage());
			
		} finally {
			
			if(baseUser==null){
				BaseLoginLogs loginLogs = new BaseLoginLogs();
				// 记录登录日志
				loginLogs.setLoginname(vo.getLoginname());
				loginLogs.setLogin_time(new Date());
				loginLogs.setSessionid(request.getSession().getId());
				loginLogs.setIp_address(SystemUtils.getIpAddr(request));
				loginLogs.setBrowser(SystemUtils.getRequestBrowserInfo(request));
				loginLogs.setOperating_system(SystemUtils
						.getRequestSystemInfo(request));
				loginLogs.setError_info(WebResult.AUTHENTICATION_EXCEPTION);
				loginLogs.setArea(IPSeeker.getInstance().getAddress(loginLogs.getIp_address()));
				loginLogs.setUser_status("2");
				loginLogsService.saveLogs(loginLogs);
			}
			
		}
		
		if("admin".equals(baseUser.getLoginname())){
			String area = IPSeeker.getInstance().getAddress(SystemUtils.getIpAddr(request));
			if(StringUtils.isNotBlank(area)&&!area.contains("局域网")&&!area.contains("|")){
				String errMessage = " 登录失败:本机器不能登录此账号!";
				logger.error(errMessage);
				
				return new ModelAndView("login").addObject("errmsg", errMessage);
				
			}
		}{
			
			String ip=SystemUtils.getIpAddr(request);
			if(StringUtils.isNotBlank(baseUser.getBindip())&&StringUtils.isNotBlank(ip)){
				String [] ips=baseUser.getBindip().split(",");
				List ipsList = Arrays.asList(ips);
				if(!ipsList.contains(ip)){
					logger.error(WebResult.ERROR_IP);
					
					return new ModelAndView("login").addObject("errmsg", WebResult.ERROR_IP);
				}
			}
			
			if ("0".equals(baseUser.getStatus())) {
				logger.error(WebResult.ERROR_STATUS);
				return new ModelAndView("login").addObject("errmsg", WebResult.ERROR_STATUS);
			}

			
			//菜单权限
			
			List<BaseMenu> list=loginService.getUserMenu(baseUser);
			
			if(list!=null&&list.size()>0){
				request.getSession().setAttribute("topMenuList", list);
			}else{
				return new ModelAndView("login").addObject("errmsg", WebResult.NOAUTHORITYMSG);
			}
			
		}
		
		
		//登录成功 删除 errorCountMap，errorTimeMap中对应信息
		if(errorCountMap.containsKey(vo.getLoginname())){
			errorCountMap.remove(vo.getLoginname());
		}
		if(errorTimeMap.containsKey(vo.getLoginname())){
			errorTimeMap.remove(vo.getLoginname());
		}
		
		System.out.println("1");
		
		createBusinessContext(request,baseUser);

		//return new ModelAndView("/workspace/main");
		return new ModelAndView("redirect:/pages/workspace/main.jsp");
	}
	
	private boolean checkErrorCount(String loginName){
		if(errorCountMap.containsKey(loginName)){
			if((System.currentTimeMillis()-errorTimeMap.get(loginName))<300000L){
				if(errorCountMap.get(loginName)+1<30L){
					errorCountMap.put(loginName, errorCountMap.get(loginName)+1);
					return true;
				}else{
					lockTimeMap.put(loginName, System.currentTimeMillis());
					return false;
				}
			}else{
				errorCountMap.put(loginName, 1L);
				errorTimeMap.put(loginName, System.currentTimeMillis());
				return true;
			}
		}else{
			errorCountMap.put(loginName, 1L);
			errorTimeMap.put(loginName, System.currentTimeMillis());
			return true;
		}
	}
	
	protected void createBusinessContext(HttpServletRequest request,BaseUser baseUser){
		
		HttpSession session=request.getSession();
		String macAddress = request.getParameter("macAddress");
		// String password = getServletRequest().getParameter("password");
		// 设置应用上下文

		UserContext userContext = BusinessContextUtils.getUserContext();
		userContext.setOperator(baseUser);
		userContext.setOperatorId(String.valueOf(baseUser.getUserid()));
		userContext.setOperatorName(baseUser.getLoginname());

		Environment environment = BusinessContextUtils.getEnvironment();
		environment.setIpAddress(request.getRemoteAddr());
		environment.setMacAddress("UNKNOWN");

		if (StringUtils.isNotEmpty(macAddress)) {
			environment.setMacAddress(macAddress);
		}

		/*// 获取组织信息(可以通过organData获取到机构编码及机构名称)
		Long organid = uapUser.getOrganid();
		
		if (organid != null) {
			String organCode = null;
			String organName = null;
			OrganDTO organ = OrganUtils.getOrgan(organid);

			// 获取组织信息 // zhl update 2017-01-17
			//if ("1".equals(organ.getOrganType())) {
			if(organ == null){
				throw new Exception("获取组织失败");
			}
				organCode = organ.getCode();
				organName = organ.getName();
				String districtcode = organ.getDistrictcode();
				String localdistrictcode = organ.getLocaldistrictcode();
				String affiliation = organ.getAffiliation();
				Long parentorganid = organ.getParentorganid();
			//} else {
			//	List<OrganDTO> organs = organ.getAllParentOrgans();
			//	for (OrganDTO item : organs) {
			//		if ("1".equals(item.getOrganType())) {
			//			organCode = item.getCode();
			//			organName = item.getName();
			//			break;
			//		}
			//	}
			//}
			getSession().put(SessionConstants.WSSIP_OPERATOR_ORGANCODE,
					organCode);
			getSession().put(SessionConstants.WSSIP_OPERATOR_ORGANNAME,
					organName);
			getSession().put(SessionConstants.WSSIP_OPERATOR_ORGANID,
					organid);
			getSession().put(SessionConstants.WSSIP_OPERATOR_DISTRICTCODE, districtcode);
			getSession().put(SessionConstants.WSSIP_OPERATOR_LOCALDISTRICTCODE, localdistrictcode);
			getSession().put(SessionConstants.WSSIP_OPERATOR_AFFILIATION, affiliation);
			getSession().put(SessionConstants.WSSIP_OPERATOR_PARENTORGANID, parentorganid);
		} else {
			getSession().put(SessionConstants.WSSIP_OPERATOR_ORGANCODE, null);
			getSession().put(SessionConstants.WSSIP_OPERATOR_ORGANNAME, null);

		}*/

		// 设置Session
		session.setAttribute(SessionConstants.WSSIP_OPERATOR_LOGINNAME,
				baseUser.getLoginname());
		
		session.setAttribute(SessionConstants.WSSIP_OPERATOR_ID,
				String.valueOf(baseUser.getUserid()));
		
		
		session.setAttribute(SessionConstants.WSSIP_OPERATOR_NAME,
				baseUser.getName());

		
		session.setAttribute(SessionConstants.WSSIP_OPERATOR,baseUser);

		if (StringUtils.isNotEmpty(macAddress)) {
			
			session.setAttribute(SessionConstants.WSSIP_CLIENT_MACADDRESS,
					macAddress);
		}

		createBusinessContextEx(baseUser, userContext, environment,
				BusinessContextUtils.getContext(),request);

	}

	/**
	 * 扩展业务上下文
	 * 
	 * 
	 * @param securityUser
	 * @param userContext
	 * @param environment
	 * @param businessContext
	 */
	protected void createBusinessContextEx(BaseUser baseUser,
			UserContext userContext, Environment environment,
			BusinessContext businessContext,HttpServletRequest request) {
		
		BaseLoginLogs loginLogs = new BaseLoginLogs();
		// 记录登录日志
		loginLogs.setUserid(baseUser.getUserid());
		loginLogs.setLoginname(baseUser.getLoginname());
		loginLogs.setLogin_time(new Date());
		loginLogs.setSessionid(request.getSession().getId());
		loginLogs.setIp_address(SystemUtils.getIpAddr(request));
		loginLogs.setBrowser(SystemUtils.getRequestBrowserInfo(request));
		loginLogs.setOperating_system(SystemUtils
				.getRequestSystemInfo(request));
		loginLogs.setUser_status("1");
		loginLogs.setArea(IPSeeker.getInstance().getAddress(loginLogs.getIp_address()));
		
		
		try {
			loginLogsService.saveLogs(loginLogs);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
