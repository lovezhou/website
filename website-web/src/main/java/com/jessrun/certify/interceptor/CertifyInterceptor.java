package com.jessrun.certify.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.certify.common.CertifyUtilByCookie;
import com.jessrun.certify.service.CertifyService;
import com.jessrun.certify.vo.UserInfo;
import com.jessrun.exception.JessrunException;
import com.jessrun.exception.RequestType;
import com.jessrun.exception.ResultProcessType;

public class CertifyInterceptor implements HandlerInterceptor {

    /**
     * cookie的操作类
     */
    @Autowired
    private CertifyUtilByCookie certifyUtilByCookie;

    /**
     * 认证的service
     */
    @Autowired
    private CertifyService      certifyService;

    /**
     * 是否是开发模式
     */
    private boolean             isDevelopmentModel      = false;

    /**
     * 当发生异常时跳转回去的登陆页面
     */
    private String              loginPage               = DEFAULT_LOGIN_PAGE;

    /**
     * 需要权限认证的FORM跳转的URL后缀
     */
    private String              fromFormSuffix          = DEFAULT_FROM_FORM_SUFFIX;

    /**
     * 需要权限认证的JSON请求的URL后缀
     */
    private String              fromJSONSuffix          = DEFAULT_FROM_JSON_SUFFIX;

    /**
     * 不需要权限认证的FORM跳转的URL后缀
     */
    private String              fromNoCertifyFormSuffix = DEFAULT_NO_CERTIFY_FROM_FORM_SUFFIX;

    /**
     * 不需要权限认证的JSON请求的URL后缀
     */
    private String              fromNoCertifyJSONSuffix = DEFAULT_NO_CERTIFY_FROM_JSON_SUFFIX;

    /**
     * 拦截访问进行认证
     */
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {

        UserInfo userInfo = null;
        if (isDevelopmentModel) {
            userInfo = certifyService.getUserInfoByUserId(-1);
            arg0.setAttribute(UserInfo.USERINFO_KEY, userInfo);
            setUserInfoToRequest(arg0, userInfo);
            return true;
        }

        // 请求是否是需要认证的FORM请求
        boolean isFromForm = arg0.getRequestURI().endsWith(fromFormSuffix);

        // 请求是否是需要认证的JSON请求
        boolean isFromJSON = arg0.getRequestURI().endsWith(fromJSONSuffix);

        // 请求是否是来自FORM
        if (isFromForm || arg0.getRequestURI().endsWith(DEFAULT_NO_CERTIFY_FROM_FORM_SUFFIX)) {
            // 设置请求类型为来自FORM
            arg0.setAttribute(RequestType.REQUEST_TYPE_KEY, RequestType.FORM);
        } else {// 来自其他
                // 设置请求类型为来自JSON
            arg0.setAttribute(RequestType.REQUEST_TYPE_KEY, RequestType.JSON);
        }

        // 从COOKIE中获得用户信息
        userInfo = certifyUtilByCookie.getUserInfoFromCookie();

        // 判断是否需要权限认证
        if (isFromForm || isFromJSON) {// 需要权限认证
            // TODO 路径判断操作合法
            System.out.println("arg0.getRequestURI():" + arg0.getRequestURI());

            // 判断是否未认证
            if (userInfo == null) {

                JessrunException je = new JessrunException("用户尚未登录或登录超时，请登录！");

                // 判断是否是form提交
                if (isFromForm) {
                    je.setRequestType(RequestType.FORM);
                    // 设置错误处理类型为等待3秒自动重定向到登陆页面
                    // arg0.setAttribute(ResultProcessType.RESULT_PROCESS_TYPE_KEY,
                    // ResultProcessType.AUTO_REDIRECT_FOR_WAIT);

                    // 设置自动重定向的路径
                    // arg0.setAttribute(ResultProcessType.RESULT_GOTO_URL_KEY, loginPage);
                    je.setResultProcessType(ResultProcessType.AUTO_REDIRECT_FOR_WAIT);
                    je.setGotoURL(loginPage);
                }

                // 抛出异常
                throw je;
            } else if (!certifyService.isCurrentUser(userInfo)) {// 判断当前用户是否被顶替
                // 已经有用户将其顶替
                JessrunException je = new JessrunException("用户已在其他地方登录");
                // 判断是否是form提交
                if (isFromForm) {
                    je.setRequestType(RequestType.FORM);
                    // 设置错误处理类型为等待3秒自动重定向到登陆页面
                    // arg0.setAttribute(ResultProcessType.RESULT_PROCESS_TYPE_KEY,
                    // ResultProcessType.AUTO_REDIRECT_FOR_WAIT);
                    // 设置自动重定向的路径
                    // arg0.setAttribute(ResultProcessType.RESULT_GOTO_URL_KEY, loginPage);
                    je.setResultProcessType(ResultProcessType.AUTO_REDIRECT_FOR_WAIT);
                    je.setGotoURL(loginPage);
                }

                // 抛出异常
                throw je;
            }
            // 将用户信息放入REQUEST
            arg0.setAttribute(UserInfo.USERINFO_KEY, userInfo);
        }

        if (userInfo != null) {
            setUserInfoToRequest(arg0, userInfo);
        }

        return true;
    }

    private void setUserInfoToRequest(HttpServletRequest arg0, UserInfo userInfo) {
        arg0.setAttribute("userId", userInfo.getUserId());
        arg0.setAttribute("userName", userInfo.getUserName());
        arg0.setAttribute("userLoginId", userInfo.getLoginId());
        arg0.setAttribute("userLoginIP", userInfo.getLoginIP());
        arg0.setAttribute("userLoginTime",
                          userInfo.getLoginTime() == null ? System.currentTimeMillis() : userInfo.getLoginTime().getTime());
        arg0.setAttribute("userOrgaId", userInfo.getOrgaId());
        arg0.setAttribute("userOrgaType", userInfo.getOrgaType());

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
                                                                                                             throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
                                                                                                               throws Exception {
    }

    public CertifyUtilByCookie getCertifyUtilByCookie() {
        return certifyUtilByCookie;
    }

    public void setCertifyUtilByCookie(CertifyUtilByCookie certifyUtilByCookie) {
        this.certifyUtilByCookie = certifyUtilByCookie;
    }

    public CertifyService getCertifyService() {
        return certifyService;
    }

    public void setCertifyService(CertifyService certifyService) {
        this.certifyService = certifyService;
    }

    public String getFromFormSuffix() {
        return fromFormSuffix;
    }

    public void setFromFormSuffix(String fromFormSuffix) {
        this.fromFormSuffix = fromFormSuffix;
    }

    public String getFromJSONSuffix() {
        return fromJSONSuffix;
    }

    public void setFromJSONSuffix(String fromJSONSuffix) {
        this.fromJSONSuffix = fromJSONSuffix;
    }

    public String getFromNoCertifyFormSuffix() {
        return fromNoCertifyFormSuffix;
    }

    public void setFromNoCertifyFormSuffix(String fromNoCertifyFormSuffix) {
        this.fromNoCertifyFormSuffix = fromNoCertifyFormSuffix;
    }

    public String getFromNoCertifyJSONSuffix() {
        return fromNoCertifyJSONSuffix;
    }

    public void setFromNoCertifyJSONSuffix(String fromNoCertifyJSONSuffix) {
        this.fromNoCertifyJSONSuffix = fromNoCertifyJSONSuffix;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public boolean isDevelopmentModel() {
        return isDevelopmentModel;
    }

    // public String getIsDevelopmentModel() {
    // return Boolean.toString(isDevelopmentModel);
    // }

    public void setIsDevelopmentModel(boolean isDevelopmentModel) {
        this.isDevelopmentModel = isDevelopmentModel;
    }

    // public void setDevelopmentModel(String isDevelopmentModel) {
    // this.isDevelopmentModel = Boolean.parseBoolean(isDevelopmentModel.toLowerCase());
    // }

    private static final String DEFAULT_LOGIN_PAGE                  = "/logout.do";

    private static final String DEFAULT_FROM_FORM_SUFFIX            = ".action";

    private static final String DEFAULT_FROM_JSON_SUFFIX            = ".act";

    private static final String DEFAULT_NO_CERTIFY_FROM_FORM_SUFFIX = ".do";

    private static final String DEFAULT_NO_CERTIFY_FROM_JSON_SUFFIX = ".json";
}
