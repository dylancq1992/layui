package com.vo;

public class WebResult {

	public static final int SUCCESSCODE = 1;
	public static final String SUCCESS = "请求成功";

	public static final int ERROR = 2;

	public static final int NOAUTHORITY = 303;
	public static final String NOAUTHORITYMSG = "没有权限";

	public static final int NOLOGIN = 304;
	public static final String NOLOGINMSG = "登录失效，请重新登陆！";

	public static final int NEEDPARAMSCODE = 1002;
	public static final String NEEDPARAMS = "请输入参数：";

	public static final int UNKNOWNCODE = 1001;
	public static final String UNKNOWN = "未知异常";

	public static final int NODATACODE = 2000;
	public static final String NODATA = "没有数据了";

	public static final int ERRORSIGN = 3000;
	public static final String ERRORSIGNSTR = "请不要尝试破解接口";

	public static final String UNKNOWN_SESSION_EXCEPTION = "异常会话!";
	public static final String UNKNOWN_ACCOUNT_EXCEPTION = "没有授权!";
	public static final String INCORRECT_CREDENTIALS_EXCEPTION = "密码错误!";
	public static final String LOCKED_IP_EXCEPTION = "用户IP限制访问，请与系统管理员联系!";
	public static final String LOCKED_ACCOUNT_EXCEPTION = "账号已被锁定，请与系统管理员联系!";
	public static final String INCORRECT_NULL_EXCEPTION = "验证码不能为空!";
	public static final String INCORRECT_CAPTCHA_EXCEPTION = "验证码错误!";
	public static final String AUTHENTICATION_EXCEPTION = "您的账号密码错误!";
	public static final String UNKNOWN_EXCEPTION = "出现未知异常,请与系统管理员联系!";
	public static final String INCORRECT_NULL_USER = "账号不存在!";
	public static final String ERROR_IP = "登录失败:本机IP与账号绑定IP不一致!";
	public static final String ERROR_STATUS = "登录失败:用户未启用，请联系管理员!";

}
