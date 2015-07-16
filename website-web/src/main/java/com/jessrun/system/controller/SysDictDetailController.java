package com.jessrun.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.system.domain.SysDictDetailVO;
import com.jessrun.system.service.SysDictDetailService;

@Controller
@RequestMapping(value = "/sysDictDetail")
public class SysDictDetailController {

    @Autowired
    private SysDictDetailService sysDictDetailService;

    @RequestMapping(value = "/toListView.do", method = RequestMethod.GET)
    public ModelAndView toSysDictDetailPage(HttpServletRequest req)  throws Exception {
        ModelAndView mav = new ModelAndView("/sysDictDetail_list.jsp");
        return mav;
    }
 

}
