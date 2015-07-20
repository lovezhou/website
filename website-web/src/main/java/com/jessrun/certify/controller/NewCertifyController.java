package com.jessrun.certify.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.certify.common.NewCertifyUtilByCookie;
import com.jessrun.certify.constant.CookieNames;
import com.jessrun.certify.service.NewCertifyService;
import com.jessrun.certify.vo.UserInfo;
import com.jessrun.common.web.NewCookieUtils;
import com.jessrun.exception.JessrunException;

@Controller
public class NewCertifyController {

    private static final Log  LOG = LogFactory.getLog(NewCertifyController.class);

    @Autowired
    private NewCertifyService certifyService;

    /**
     * 处理登陆的ACTION，POST提交
     * 
     * @param arg0 request请求
     * @param accountNum 登陆帐号
     * @param password 登陆密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "/newlogin.do" }, method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest arg0, HttpServletResponse arg1,
                              @RequestParam("account") String account, @RequestParam("password") String password)
                                                                                                                 throws Exception {
        ModelAndView mav = null;

        boolean isSelfRewriteMode = NewCertifyUtilByCookie.getIsSelfRewriteMode(arg0);

        String companySIM = isSelfRewriteMode ? NewCertifyUtilByCookie.getCompanySIMFromCookie(arg0) : NewCertifyUtilByCookie.getCompanySIMFromRequest(arg0);

        UserInfo userInfo = null;
        try {
            if (account.equals("sys_admin")) {
                userInfo = certifyService.login("", account, password, arg0.getRemoteAddr());
            } else {
                userInfo = certifyService.login(companySIM, account, password, arg0.getRemoteAddr());
            }

        } catch (Exception e) {
            LOG.error(e);
            StackTraceElement[] trace = e.getStackTrace();
            for (int i = 0; i < trace.length; i++) {
                LOG.error("\tat " + trace[i]);
            }

            if (isSelfRewriteMode) {
                if (companySIM == null) {
                    mav = new ModelAndView("redirect:/index.jsp");
                } else {
                    mav = new ModelAndView("redirect:/" + companySIM + "/index.jsp");
                }

                NewCertifyUtilByCookie.setLoginErrorMessage(arg0, arg1, URLEncoder.encode(e.getMessage(), "UTF-8"));
            } else {
                mav = new ModelAndView("/index.jsp");
                mav.addObject("errorMessage", e.getMessage());
                mav.addObject("s", companySIM);
            }

            return mav;
        }

        NewCertifyUtilByCookie.createCookie(arg0, arg1, userInfo);

        if (isSelfRewriteMode) {
            if (companySIM == null) {
                mav = new ModelAndView("redirect:/default.jsp");
            } else {
                mav = new ModelAndView("redirect:/" + companySIM + "/default.jsp");
            }
        } else {
            mav = new ModelAndView("redirect:/default.jsp?s=" + companySIM);
        }

        return mav;
    }

    /**
     * 修改密码
     * 
     * @param arg0 request请求
     * @param oldPW 旧密码
     * @param newPW 新密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "gotoNewModifyPW.action" }, method = RequestMethod.GET)
    public ModelAndView gotoModifyPW() throws Exception {

        // 指定重定向跳转URL
        ModelAndView mav = new ModelAndView("/admin/password.jsp");
        return mav;
    }

    /**
     * 修改密码
     * 
     * @param arg0 request请求
     * @param oldPW 旧密码
     * @param newPW 新密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "newModifyPW.action" }, method = RequestMethod.POST)
    public ModelAndView modifyPW(HttpServletRequest arg0, @RequestParam("oldPW") String oldPW,
                                 @RequestParam("newPW") String newPW) throws Exception {
        UserInfo userInfo = NewCertifyUtilByCookie.getUserInfo(arg0);
        try {
            certifyService.modifyPW(userInfo.getId(), oldPW, newPW, userInfo.getUserId(), userInfo.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            JessrunException je = new JessrunException(e);
            je.setDesc(e.getMessage());
            je.setGotoURL("/gotoModifyPW.action");
            throw je;
        }

        // 指定重定向跳转URL
        ModelAndView mav = new ModelAndView("/common/message.jsp");
        mav.addObject("url", "gotoModifyPW.action");
        return mav;
    }

    /**
     * 处理登出的ACTION
     * 
     * @return
     */
    @RequestMapping(value = { "/newlogout.do" }, method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest arg0, HttpServletResponse arg1) {
        boolean isSelfRewriteMode = NewCertifyUtilByCookie.getIsSelfRewriteMode(arg0);

        String companySIM = isSelfRewriteMode ? NewCertifyUtilByCookie.getCompanySIMFromCookie(arg0) : NewCertifyUtilByCookie.getCompanySIMFromRequest(arg0);

        NewCookieUtils.deleteCookie(arg1, CookieNames.CERTIFY_COOKIE_NAME);

        // 指定跳转到首页
        ModelAndView mav = null;

        if (isSelfRewriteMode) {
            // 指定跳转到首页
            if (companySIM == null) {
                mav = new ModelAndView("redirect:/index.jsp");
            } else {
                mav = new ModelAndView("redirect:/" + companySIM + "/index.jsp");
            }

        } else {
            // 指定跳转到首页
            mav = new ModelAndView("redirect:/index.jsp?s=" + companySIM);
        }

        return mav;
    }

    public NewCertifyService getCertifyService() {
        return certifyService;
    }

    public void setCertifyService(NewCertifyService certifyService) {
        this.certifyService = certifyService;
    }
}
