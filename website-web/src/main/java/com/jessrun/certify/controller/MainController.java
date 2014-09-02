package com.jessrun.certify.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.certify.service.NewCertifyService;

/**
 * 登陆认证的Controller
 * 
 * @author Awen
 */
@Controller
public class MainController {

    @RequestMapping(value = { "/top.do" }, method = RequestMethod.GET)
    public ModelAndView top() throws Exception {
        return new ModelAndView("/comm/frame_top");
    }

    @RequestMapping(value = { "/menu.do" }, method = RequestMethod.GET)
    public ModelAndView menu(HttpServletRequest arg0) throws Exception {

        ModelAndView mav = new ModelAndView("/main/menu");

        return mav;
    }

    @RequestMapping(value = { "/menu-xtgn.do" }, method = RequestMethod.GET)
    public ModelAndView menuXtgn(HttpServletRequest arg0) throws Exception {

        ModelAndView mav = new ModelAndView("/admin/menu-xtgn");

        return mav;
    }

    /**
     * 后台主页面控制器
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "", "/", "/main.do" }, method = RequestMethod.GET)
    public ModelAndView main() throws Exception {
        return new ModelAndView("/main/main");
    }

}
