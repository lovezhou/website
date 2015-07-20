package com.jessrun.web.system.controller;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.common.support.spring.view.JsonView;
import com.jessrun.web.system.domain.ConfCost;
import com.jessrun.web.system.domain.ConfCostCompany;
import com.jessrun.web.system.domain.ConfGrid;
import com.jessrun.web.system.service.ConfCostCompanyService;
import com.jessrun.web.system.service.SystemConfService;

@Controller
@RequestMapping(value = "/bic/sysconf")
public class SystemConfController {

    @Autowired
    private SystemConfService      systemConfService;

    @Autowired
    private ConfCostCompanyService confCostCompanyService;

    @RequestMapping(value = "gotoConfCarryCost.action", method = RequestMethod.GET)
    public ModelAndView gotoConfCarryCost(HttpServletRequest req,
                                          @RequestParam(value = "companyId", required = false) Integer companyId)
                                                                                                                 throws Exception {
        Integer userOrgaType = (Integer) req.getAttribute("userOrgaType");
        List<ConfCost> resultList = new ArrayList<ConfCost>();
        List<ConfCostCompany> confCostCompanyList = null;
        if (userOrgaType > 1) {
            companyId = (Integer) req.getAttribute("userCompanyId");
            confCostCompanyList = confCostCompanyService.getCompanyConfByCompanyId(companyId);// systemConfService.getCostConfList(companyId);
        } else if (companyId != null) {
            confCostCompanyList = confCostCompanyService.getCompanyConfByCompanyId(companyId);// systemConfService.getCostConfList(companyId);
        }
        for (int i = 0; i < confCostCompanyList.size(); i++) {
            ConfCostCompany tem = confCostCompanyList.get(i);
            ConfCost confCost = new ConfCost();
            confCost.setIndex(tem.getIdx());
            confCost.setCostName(tem.getCostName());
            confCost.setCostCode(tem.getCostCode());
            confCost.setCostType(tem.getCostType());
            confCost.setCostVal(tem.getCostVal());
            confCost.setAliasName(tem.getAliasName());
            resultList.add(confCost);
        }

        List<ConfCost> allConfCost = systemConfService.getAllCostConfList();

        if (resultList != null) {
            allConfCost.removeAll(resultList);
        }

        ModelAndView mav = new ModelAndView("/admin/confcost.jsp");
        mav.addObject("allConfCost", allConfCost);
        mav.addObject("companyId", companyId);
        mav.addObject("resultList", resultList);
        return mav;
    }

    @RequestMapping(value = "gotoConfGrid.action", method = RequestMethod.GET)
    public ModelAndView gotoConfGrid(HttpServletRequest req,
                                     @RequestParam(value = "companyId", required = false) Integer companyId,
                                     @RequestParam(value = "gridInfoId", required = false) Integer gridInfoId)
                                                                                                              throws Exception {
        Integer userOrgaType = (Integer) req.getAttribute("userOrgaType");
        ModelAndView mav = new ModelAndView("/admin/confGrid.jsp");

        if (userOrgaType > 1) {
            companyId = (Integer) req.getAttribute("userCompanyId");
        }

        List<ConfCost> allConfCost = systemConfService.getAllCostConfList();

        // 获取查询列表类型数据
        List gridInfoList = this.systemConfService.getAllGridInfoList();
        mav.addObject("gridInfoList", gridInfoList);
        mav.addObject("companyId", companyId);
        mav.addObject("gridInfoId", gridInfoId);
        return mav;
    }

    @RequestMapping(value = "queryConfGrid.action", method = RequestMethod.POST)
    public ModelAndView queryConfGrid(HttpServletRequest req,
                                      @RequestParam(value = "companyId", required = false) Integer companyId,
                                      @RequestParam(value = "gridInfoId", required = false) Integer gridInfoId) {
        Map datamap = new HashMap();
        if (gridInfoId != null && !"".equals(gridInfoId)) {
            List<ConfGrid> resultList = null;
            if (companyId != null) {
                resultList = systemConfService.getGridConfList(Integer.valueOf(companyId), Integer.valueOf(gridInfoId));
            }

            List<ConfGrid> allConfGrid = systemConfService.getAllGridConfList(Integer.valueOf(companyId),
                                                                              Integer.valueOf(gridInfoId));

            // if (resultList != null) {
            //
            // }
            datamap.put("allConfGrid", allConfGrid);
            datamap.put("resultList", resultList);
        }
        return new ModelAndView(new JsonView(datamap));
    }

    @RequestMapping(value = "saveConfGridAlias.action", method = RequestMethod.POST)
    public ModelAndView saveConfGridAlias(HttpServletRequest req,
                                          @RequestParam(value = "companyId", required = false) Integer companyId,
                                          @RequestParam(value = "itemId", required = false) Integer itemId,
                                          @RequestParam(value = "aliasName", required = false) String aliasName) {
        boolean bol = false;
        Map datamap = new HashMap();
        systemConfService.saveConfGridAlias(companyId, itemId, aliasName);
        bol = true;
        datamap.put("bol", bol);
        return new ModelAndView(new JsonView(datamap));
    }

    @RequestMapping(value = "saveConfCostAlias.action", method = RequestMethod.POST)
    public ModelAndView saveConfCostAlias(HttpServletRequest req,
                                          @RequestParam(value = "companyId", required = false) Integer companyId,
                                          @RequestParam(value = "itemId", required = false) Integer itemId,
                                          @RequestParam(value = "aliasName", required = false) String aliasName) {
        boolean bol = false;
        Map datamap = new HashMap();
        systemConfService.saveConfCostAlias(companyId, itemId, aliasName);
        bol = true;
        datamap.put("bol", bol);
        return new ModelAndView(new JsonView(datamap));
    }

    @RequestMapping(value = "rebackConfGridAlias.action", method = RequestMethod.POST)
    public ModelAndView rebackConfGridAlias(HttpServletRequest req,
                                            @RequestParam(value = "companyId", required = false) Integer companyId,
                                            @RequestParam(value = "itemId", required = false) Integer itemId) {
        boolean bol = false;
        Map datamap = new HashMap();
        systemConfService.rebackConfGridAlias(companyId, itemId);
        bol = true;
        datamap.put("bol", bol);
        return new ModelAndView(new JsonView(datamap));
    }

    @RequestMapping(value = "rebackConfCostAlias.action", method = RequestMethod.POST)
    public ModelAndView rebackConfCostAlias(HttpServletRequest req,
                                            @RequestParam(value = "companyId", required = false) Integer companyId,
                                            @RequestParam(value = "itemId", required = false) Integer itemId) {
        boolean bol = false;
        Map datamap = new HashMap();
        systemConfService.rebackConfCostAlias(companyId, itemId);
        bol = true;
        datamap.put("bol", bol);
        return new ModelAndView(new JsonView(datamap));
    }

    @RequestMapping(value = "saveConfCarryCost.action", method = RequestMethod.POST)
    public ModelAndView saveConfCarryCost(HttpServletRequest req,
                                          @RequestParam(value = "companyId", required = false) Integer companyId,
                                          @RequestParam(value = "indexs", required = false) String indexs)
                                                                                                          throws Exception {

        BitSet bs = new BitSet();
        int index = 0;
        int sort = 1;
        // 删除已配的
        confCostCompanyService.deleteByCompanyId(companyId);
        for (String str : indexs.split(",")) {
            if ((index = Integer.parseInt(str)) >= 0) {

                // 获取tb_conf_cost_item
                ConfCost item = systemConfService.getConfCostItemByIdx(Integer.valueOf(str));
                // 保存到tb_conf_cost_company
                ConfCostCompany confCostCompany = new ConfCostCompany();
                confCostCompany.setIdx(item.getIndex());
                confCostCompany.setCostName(item.getCostName());
                confCostCompany.setCostCode(item.getCostCode());
                confCostCompany.setCostType(item.getCostType());
                confCostCompany.setCostVal(item.getCostVal());
                confCostCompany.setSort(sort);
                confCostCompany.setCompanyId(companyId);
                confCostCompanyService.add(confCostCompany);
                sort++;

                bs.set(index, true);
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 32; i++) {
            if (bs.get(i)) {

            }
            sb.append(bs.get(i) ? '1' : '0');
        }

        systemConfService.setCostConf(companyId, sb.toString(), (Integer) req.getAttribute("userId"),
                                      (String) req.getAttribute("userName"));

        ModelAndView mav = new ModelAndView("/common/message.jsp");
        mav.addObject("url", "/bic/sysconf/gotoConfCarryCost.action");
        // mav.addObject("companyId", companyId);
        return mav;
    }

    @RequestMapping(value = "saveConfGrid.action", method = RequestMethod.POST)
    public ModelAndView saveConfGrid(HttpServletRequest req,
                                     @RequestParam(value = "companyId", required = false) Integer companyId,
                                     @RequestParam(value = "gridInfoId", required = false) Integer gridInfoId,
                                     @RequestParam(value = "indexs", required = false) String indexs) throws Exception {

        List<ConfGrid> list = new ArrayList();
        int index = 0;
        for (String str : indexs.split(",")) {
            if (!"".equals(str.trim())) {
                ConfGrid confGrid = new ConfGrid();
                confGrid.setId(Integer.valueOf(str));
                confGrid.setSort(index);
                list.add(confGrid);
            }
            index++;
        }
        systemConfService.updateGridConf(companyId, gridInfoId, list);

        ModelAndView mav = new ModelAndView("/common/message.jsp");
        mav.addObject("url", "/bic/sysconf/gotoConfGrid.action?gridInfoId=" + gridInfoId);
        // mav.addObject("companyId", companyId);
        // mav.addObject("gridInfoId", gridInfoId);
        return mav;
    }

    public SystemConfService getSystemConfService() {
        return systemConfService;
    }

    public void setSystemConfService(SystemConfService systemConfService) {
        this.systemConfService = systemConfService;
    }

}
