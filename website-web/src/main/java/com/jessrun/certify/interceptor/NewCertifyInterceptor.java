package com.jessrun.certify.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.certify.common.NewCertifyUtilByCookie;
import com.jessrun.certify.constant.ParamKeys;
import com.jessrun.certify.service.NewCertifyService;
import com.jessrun.certify.vo.UserInfo;
import com.jessrun.exception.JessrunException;
import com.jessrun.exception.RequestType;
import com.jessrun.exception.ResultProcessType;

public class NewCertifyInterceptor implements HandlerInterceptor {

	@Autowired
	private NewCertifyService certifyService;
	
	private String certifySuffix = ".action";

	/**
	 * 当发生异常时跳转回去的登陆页面
	 */
	private String loginPage = DEFAULT_LOGIN_PAGE;

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		
		boolean isSelfRewriteMode = NewCertifyUtilByCookie.getIsSelfRewriteMode(arg0);
		
		String companySIM = isSelfRewriteMode ? NewCertifyUtilByCookie.getCompanySIMFromCookie(arg0) : NewCertifyUtilByCookie.getCompanySIMFromRequest(arg0);
		
		

		// 从COOKIE中获得用户信息
		UserInfo userInfo = NewCertifyUtilByCookie.getUserInfo(arg0);
		
		if(userInfo != null){
			if(userInfo.getAccountNum().equals("sys_admin")){
				companySIM = null;
			}
		}

		// TODO 路径判断操作合法
		//System.out.println("arg0.getRequestURI():" + arg0.getRequestURI());
		
		

		if(arg0.getRequestURI().toLowerCase().endsWith(certifySuffix.toLowerCase())){
			// 判断是否未认证
			if (userInfo == null) {
	
				JessrunException je = new JessrunException("用户尚未登录或登录超时，请登录！");
	
				je.setRequestType(RequestType.FORM);
	
	
				// 设置自动重定向的路径
				je.setResultProcessType(ResultProcessType.AUTO_REDIRECT_FOR_WAIT);
				je.setGotoURL(loginPage);
				
	
				// 抛出异常
				throw je;
			} else if (!certifyService.validateCurrentAccount(companySIM,
					userInfo.getAccountNum(), userInfo.getLoginId())) {// 判断当前用户是否被顶替
				// 已经有用户将其顶替
				JessrunException je = new JessrunException("用户已在其他地方登录");
				// 判断是否是form提交
				je.setRequestType(RequestType.FORM);
				
				// 设置自动重定向的路径
				je.setResultProcessType(ResultProcessType.AUTO_REDIRECT_FOR_WAIT);
				je.setGotoURL(loginPage);
	
				// 抛出异常
				throw je;
			}
		
		}
		// 将用户信息放入REQUEST
		arg0.setAttribute(UserInfo.USERINFO_KEY, userInfo);

		if (userInfo != null) {
			setUserInfoToRequest(arg0, userInfo);
			
			arg0.setAttribute(ParamKeys.RESOURCE_IDS_KEY, certifyService.getResourceItems(userInfo.getId()));
		}
		

		return true;
	}

	private void setUserInfoToRequest(HttpServletRequest arg0, UserInfo userInfo) {
		arg0.setAttribute("userId", userInfo.getUserId());
		arg0.setAttribute("userName", userInfo.getUserName());
		arg0.setAttribute("userAccountId", userInfo.getId());
		arg0.setAttribute("userAccount", userInfo.getAccountNum());
		arg0.setAttribute("userLoginId", userInfo.getLoginId());
		arg0.setAttribute("userLoginIP", userInfo.getLoginIP());
		arg0.setAttribute("userLoginTime",userInfo.getLoginTime() == null ? System.currentTimeMillis()
						: userInfo.getLoginTime().getTime());
		arg0.setAttribute("userOrgaId", userInfo.getOrgaId());
		arg0.setAttribute("userOrgaType", userInfo.getOrgaType());
		
		arg0.setAttribute("userOrgDeptId", userInfo.getDeptId());
		
		arg0.setAttribute("userCompanyId", userInfo.getDefaultCompanyId());
		arg0.setAttribute("userCompanyName", userInfo.getDefaultCompanyName());
		arg0.setAttribute("userCompanyNameSim", userInfo.getDefaultCompanyNameSIM());
		arg0.setAttribute("userCompanyNameSimCn", userInfo.getDefaultCompanyNameSIMCN());
		
		arg0.setAttribute("userStateId", userInfo.getDefaultStateId());
		arg0.setAttribute("userStateName", userInfo.getDefaultStateName());
		
		arg0.setAttribute("userDeptId", userInfo.getDefaultDeptId());
		arg0.setAttribute("userDeptName", userInfo.getDefaultDeptName());
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	public NewCertifyService getCertifyService() {
		return certifyService;
	}

	public void setCertifyService(NewCertifyService certifyService) {
		this.certifyService = certifyService;
	}

	public String getCertifySuffix() {
		return certifySuffix;
	}

	public void setCertifySuffix(String certifySuffix) {
		this.certifySuffix = certifySuffix;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}



	private static final String DEFAULT_LOGIN_PAGE = "/newlogout.do";
}
