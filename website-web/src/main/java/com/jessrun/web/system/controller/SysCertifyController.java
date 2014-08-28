package com.jessrun.web.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.certify.vo.UserInfo;
import com.jessrun.common.pagination.Pagination;
import com.jessrun.platform.util.JavaBeanUtil;
import com.jessrun.platform.util.SerializeUtils;
import com.jessrun.web.system.domain.CertifyAccount;
import com.jessrun.web.system.domain.CertifyDept;
import com.jessrun.web.system.domain.CertifyResoAcco;
import com.jessrun.web.system.domain.CertifyResource;
import com.jessrun.web.system.domain.CertifyUser;
import com.jessrun.web.system.service.CertifyAccountService;
import com.jessrun.web.system.service.CertifyDeptService;
import com.jessrun.web.system.service.CertifyResoAccoService;
import com.jessrun.web.system.service.CertifyResourceService;
import com.jessrun.web.system.service.CertifyUserService;

@Controller
public class SysCertifyController {

    @Autowired
    CertifyAccountService  accountService;

    @Autowired
    CertifyDeptService     certifyDeptService;

    @Autowired
    CertifyUserService     userService;

    @Autowired
    CertifyResourceService resourceService;

    @Autowired
    CertifyResoAccoService accoService;

    /**
     * 跳转权限列表页面
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sysCertify/gotoList.action", method = RequestMethod.GET)
    public ModelAndView gotoCertifyIncome(HttpServletRequest request) throws Exception {
        return getListByPage(request, new UserInfo(), new Pagination(10, 1), "", 1);
    }

    /**
     * 跳转添加权限页面
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sysCertify/gotoAddCertifyList.action", method = RequestMethod.GET)
    public ModelAndView gotoAddCertify(HttpServletRequest request) throws Exception {
        List<CertifyDept> resultList = certifyDeptService.getCertifyDeptList((Integer) request.getAttribute("userOrgDeptId"));
        ModelAndView mav = new ModelAndView("/system/add-list.jsp");
        mav.addObject("resultList", resultList);
        return mav;
    }

    /**
     * 根据左边选择ID查询数据
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sysCertify/gotoSelectListById.action", method = RequestMethod.GET)
    public String gotoSelectList(HttpServletRequest request, Integer id) throws Exception {
        return getSelectList(request, id);
    }

    /**
     * 跳转分配权限页面
     * 
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sysCertify/authority.action", method = RequestMethod.GET)
    public ModelAndView gotoAssignAuthority(HttpServletRequest request, Integer id) throws Exception {
        return gotoAuthorityList(request, new Pagination(10, 1), id);
    }

    /**
     * @param request
     * @param userInfo
     * @param pagination
     * @param uName
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/sysCertify/getListByPage.action", method = RequestMethod.POST)
    public ModelAndView getListByPage(HttpServletRequest request,
                                      UserInfo userInfo,
                                      Pagination pagination,
                                      @RequestParam(value = "uName", defaultValue = "", required = false) String uName,
                                      @RequestParam(value = "status", defaultValue = "", required = false) Integer status)
                                                                                                                          throws Exception {
        Integer deptId = (Integer) request.getAttribute("userOrgDeptId");
        userInfo.setDeptId(deptId);
        userInfo.setUserName(uName);
        Map<String, Object> model = JavaBeanUtil.convertBean(userInfo);
        model.put("pagination", pagination);
        model.put("status", status);
        List<UserInfo> resultList = accountService.getCertifyAccountListByPage(model);
        ModelAndView mav = new ModelAndView("/system/list.jsp");
        mav.addObject("resultList", resultList);
        mav.addObject("uName", uName);
        mav.addAllObjects(model);
        return mav;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/sysCertify/selectList.action", method = RequestMethod.POST)
    @ResponseBody
    public String getSelectList(HttpServletRequest request, @RequestParam(value = "id", required = false) Integer id) {
        CertifyDept certifyDept = certifyDeptService.getCertifyDeptById(id);
        return SerializeUtils.toJson(certifyDeptService.getDeptList(certifyDept.getId()));
    }

    /*
     * 给员工添加登录账号
     */
    @RequestMapping(value = "/sysCertify/add.action", method = RequestMethod.POST)
    public ModelAndView addCertifyAccount(HttpServletRequest request,
                                          @RequestParam(value = "nodeId", required = false) Integer nodeId,
                                          @RequestParam(value = "username", required = false) Integer username,
                                          @RequestParam(value = "accountNum", required = false) String accountNum,
                                          @RequestParam(value = "accountPwd", required = false) String accountPwd) {
        Integer createdId = (Integer) request.getAttribute("userId");
        String createName = (String) request.getAttribute("userName");
        CertifyAccount account = new CertifyAccount();
        account.setUserId(username);
        account.setAccount(accountNum);
        account.setPassword(accountPwd);
        account.setDeptId(nodeId);
        account.setCreatedId(createdId);
        account.setCreatedName(createName);
        accountService.addCertifyAccount(account);
        ModelAndView mav = new ModelAndView("/common/message.jsp");
        String url = "sysCertify/gotoList.action";
        mav.addObject("url", url);
        return mav;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/sysCertify/gotoAuthority.action", method = RequestMethod.POST)
    public ModelAndView gotoAuthorityList(HttpServletRequest request, Pagination pagination,
                                          @RequestParam(value = "id", required = false) Integer id) throws Exception {

        // 根据用户ID查询该用户的所有资源及信息
        List<CertifyResoAcco> accosList = accoService.getCertifyResoAccoById(id);
        // 我所有的资源
        // List<CertifyResource> resultList =
        // resourceService.getCertifyResourceList((Integer)request.getAttribute("userAccountId"));
        List<CertifyResource> resultList = resourceService.getCertifyResourceList(0);
        String str = "";
        if (accosList.size() > 0) {
            for (int i = 0; i < accosList.size(); i++) {
                str += accosList.get(i).getResourceId() + ",";
            }
        }
        // 被分配资源人的账户信息
        CertifyAccount resultAccount = accountService.getCertifyAccountById(id);

        // 被分配资源人的员工信息
        CertifyUser resultUser = userService.getCertifyUserById(resultAccount.getUserId());

        ModelAndView mav = new ModelAndView("/system/authority-list.jsp");
        mav.addObject("resultAccount", resultAccount);
        mav.addObject("resultUser", resultUser);
        mav.addObject("resultList", resultList);
        mav.addObject("accosList", accosList);
        mav.addObject("str", str);
        return mav;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/sysCertify/addResoAcco.action", method = RequestMethod.POST)
    public ModelAndView addResoAcco(HttpServletRequest request, CertifyResoAcco acco,
                                    @RequestParam(value = "accountId", required = false) Integer accountId,
                                    @RequestParam(value = "id", required = false) String resoId) {
        accoService.addCertifyResoAcco(acco, resoId, accountId);
        ModelAndView mav = new ModelAndView("/common/message.jsp");
        String url = "sysCertify/gotoList.action";
        mav.addObject("url", url);
        return mav;
    }

    /**
     * 注销功能
     * 
     * @param request
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/sysCertify/LogOut.action", method = RequestMethod.POST)
    public ModelAndView gotoLogOut(HttpServletRequest request,
                                   @RequestParam(value = "hid_authorityId", required = false) Integer hid_authorityId) {
        // 根据帐号ID查询该帐号对应信息
        CertifyAccount resultAccount = accountService.getCertifyAccountById(hid_authorityId);
        resultAccount.setStatus(0);
        accountService.updateCertifyAccount(resultAccount);
        ModelAndView mav = new ModelAndView("/common/message.jsp");
        String url = "sysCertify/gotoList.action";
        mav.addObject("url", url);
        return mav;
    }

    public CertifyAccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(CertifyAccountService accountService) {
        this.accountService = accountService;
    }

    public CertifyDeptService getCertifyDeptService() {
        return certifyDeptService;
    }

    public void setCertifyDeptService(CertifyDeptService certifyDeptService) {
        this.certifyDeptService = certifyDeptService;
    }

    public CertifyUserService getUserService() {
        return userService;
    }

    public void setUserService(CertifyUserService userService) {
        this.userService = userService;
    }

    public CertifyResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(CertifyResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public CertifyResoAccoService getAccoService() {
        return accoService;
    }

    public void setAccoService(CertifyResoAccoService accoService) {
        this.accoService = accoService;
    }

}
