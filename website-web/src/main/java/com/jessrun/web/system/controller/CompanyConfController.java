package com.jessrun.web.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.web.system.domain.ConfCompany;
import com.jessrun.web.system.service.CompanyConfService;
import com.jessrun.web.system.vo.SaveCompanyConfFormVO;

@Controller
@RequestMapping(value = "/bic/admin")
public class CompanyConfController {

    @Autowired
    private CompanyConfService companyConfService;

    @RequestMapping(value = "gotoConfCompany.action", method = RequestMethod.GET)
    public ModelAndView gotoConfCompany(HttpServletRequest req,
                                        @RequestParam(value = "companyId", required = false) Integer companyId)
                                                                                                               throws Exception {
        ModelAndView mav = new ModelAndView("/admin/confCompany.jsp");
        Integer userOrgaType = (Integer) req.getAttribute("userOrgaType");

        if (userOrgaType == 1) {
            if (companyId == null) {
                return mav;
            }
        } else if (userOrgaType > 1) {
            companyId = (Integer) req.getAttribute("userCompanyId");
        }

        List<ConfCompany> resultList = companyConfService.getCompanyConfByCompanyId(companyId);

        mav.addObject("resultList", resultList);
        mav.addObject("companyId", companyId);
        return mav;
    }

    @RequestMapping(value = "saveCompanyConf.action", method = RequestMethod.POST)
    public ModelAndView saveCompanyConf(HttpServletRequest req, SaveCompanyConfFormVO saveCompanyConfFormVO)
                                                                                                            throws Exception {

        companyConfService.saveCompanyConf(saveCompanyConfFormVO.getCompanyId(), saveCompanyConfFormVO.getConfList());

        ModelAndView mav = new ModelAndView("/common/message.jsp");
        mav.addObject("url", "/bic/admin/gotoConfCompany.action?companyId=" + saveCompanyConfFormVO.getCompanyId());
        return mav;
    }

    public CompanyConfService getCompanyConfService() {
        return companyConfService;
    }

    public void setCompanyConfService(CompanyConfService companyConfService) {
        this.companyConfService = companyConfService;
    }

}
