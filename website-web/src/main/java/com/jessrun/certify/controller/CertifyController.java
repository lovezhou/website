package com.jessrun.certify.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.certify.common.CertifyUtilByCookie;
import com.jessrun.certify.service.CertifyService;
import com.jessrun.certify.vo.Role;
import com.jessrun.certify.vo.TestExcelOutput;
import com.jessrun.certify.vo.UserInfo;
import com.jessrun.common.pagination.Pagination;
import com.jessrun.common.support.spring.view.ExcelView;
import com.jessrun.exception.JessrunException;
import com.jessrun.platform.util.JavaBeanUtil;

/**
 * 登陆认证的Controller
 * 
 * @author Awen
 */

@Controller
public class CertifyController {

    /**
     * COOKIE的操作类
     */
    @Autowired
    protected CertifyUtilByCookie certifyUtilByCookie;

    /**
     * 认证的SERVICE
     */
    @Autowired
    protected CertifyService      certifyService;

    /**
     * 处理登陆的ACTION，POST提交
     * 
     * @param arg0 request请求
     * @param accountNum 登陆帐号
     * @param password 登陆密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "/login.do" }, method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest arg0, @RequestParam("s") String companyNameSIM,
                              @RequestParam("accountNum") String accountNum, @RequestParam("password") String password)
                                                                                                                       throws Exception {
        UserInfo userInfo = null;
        ModelAndView mav = null;

        try {
            // 输入帐号密码和登陆IP进行登陆，并返回用户信息，如果用户登陆错误就会抛出异常
            userInfo = certifyService.login(companyNameSIM, accountNum, password, arg0.getRemoteAddr());
        } catch (Exception e) {
            e.printStackTrace();
            mav = new ModelAndView("/index.jsp");
            mav.addObject("errorMessage", e.getMessage());
            mav.addObject("s", companyNameSIM);
            return mav;
        }

        // 移除现在的COOKIE
        certifyUtilByCookie.removeCookie();

        // 将现在登陆的用户信息写入COOKIE
        certifyUtilByCookie.writeCookie(userInfo);

        // 指定重定向跳转URL
        mav = new ModelAndView("redirect:/default.jsp?s=" + companyNameSIM);

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
    @RequestMapping(value = { "gotoModifyPW.action" }, method = RequestMethod.GET)
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
    @RequestMapping(value = { "modifyPW.action" }, method = RequestMethod.POST)
    public ModelAndView modifyPW(HttpServletRequest arg0, @RequestParam("oldPW") String oldPW,
                                 @RequestParam("newPW") String newPW) throws Exception {

        try {
            certifyService.modifyPW(Integer.valueOf(arg0.getAttribute("userId").toString()), oldPW, newPW);
        } catch (Exception e) {
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
    @RequestMapping(value = { "/logout.do" }, method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest arg0) {
        // String companySim = arg0.get

        // 移除COOKIE
        certifyUtilByCookie.removeCookie();

        // 指定跳转到首页
        ModelAndView mav = new ModelAndView("redirect:/index.jsp?s=" + arg0.getAttribute("userCompanyNameSim"));
        return mav;
    }

    /**
     * 处理登出的ACTION
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "/admin/user/gotoList.action" }, method = RequestMethod.GET)
    public ModelAndView gotoUserList(HttpServletRequest arg0) throws Exception {
        return getUserListByPage(new UserInfo(), arg0, new Pagination(10, 1), "");
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    @RequestMapping(value = { "/admin/user/getListByPage.action" }, method = RequestMethod.POST)
    public ModelAndView getUserListByPage(UserInfo userInfo,
                                          HttpServletRequest request,
                                          Pagination pagination,
                                          @RequestParam(value = "uName", defaultValue = "", required = false) String uName)
                                                                                                                           throws Exception {
        Integer userOrgaType = (Integer) request.getAttribute("userOrgaType");
        Integer companyId = (Integer) request.getAttribute("userCompanyId");
        Integer stateId = (Integer) request.getAttribute("userStateId");
        if (userOrgaType >= 3) {
            userInfo.setDefaultCompanyId(companyId);
            userInfo.setDefaultStateId(stateId);
            userInfo.setMaxOrgaType(userOrgaType);
        }
        if (userOrgaType >= 2) {
            userInfo.setDefaultCompanyId(companyId);
            userInfo.setMaxOrgaType(userOrgaType);
        }

        userInfo.setUserName(uName);

        Map model = JavaBeanUtil.convertBean(userInfo);

        model.put("pagination", pagination);

        List<UserInfo> resultList = certifyService.getAllAdminListByPage(model);

        Map companyModel = new HashMap();

        ModelAndView mav = new ModelAndView("/admin/user/list.jsp");
        mav.addObject("resultList", resultList);
        mav.addObject("uName", uName);
        mav.addAllObjects(model);

        return mav;
    }

    /**
     * 跳转系统管理页面
     * 
     * @author 柯欢
     * @createTime 2012-04-22
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/admin/user/gotoAddFromStaff.action", method = RequestMethod.GET)
    public ModelAndView gotoAddFromStaff(HttpServletRequest request) throws Exception {

        @SuppressWarnings("unused")
        Integer userOrgaType = (Integer) request.getAttribute("userOrgaType");
        Map<String, Object> RoleMap = JavaBeanUtil.convertBean(new Role());
        List<Role> roleList = certifyService.getAllRole(RoleMap);

        ModelAndView mav = new ModelAndView("/admin/user/addFromStaff.jsp");
        mav.addObject("roleList", roleList);
        return mav;
    }

    /**
     * 跳转系统管理页面
     * 
     * @author 柯欢
     * @createTime 2012-04-22
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/admin/user/gotoAdd.action", method = RequestMethod.GET)
    public ModelAndView gotoAdd(HttpServletRequest request) throws Exception {
        @SuppressWarnings("unused")
        Integer userOrgaType = (Integer) request.getAttribute("userOrgaType");

        Map<String, Object> RoleMap = JavaBeanUtil.convertBean(new Role());
        List<Role> roleList = certifyService.getAllRole(RoleMap);

        ModelAndView mav = new ModelAndView("/admin/user/add.jsp");
        mav.addObject("roleList", roleList);
        return mav;
    }

    /**
     * 员工添加管理员
     * 
     * @param request
     * @param companyId
     * @param accountNum
     * @param password
     * @param stateId
     * @param staffId
     * @param roleId
     * @param state
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/admin/user/addStaff.action", method = RequestMethod.POST)
    public ModelAndView addStaff(HttpServletRequest request,
                                 @RequestParam(value = "companyId", required = false) Integer companyId,
                                 @RequestParam(value = "accountNum", required = false) String accountNum,
                                 @RequestParam(value = "password", required = false) String password,
                                 @RequestParam(value = "stateId", required = false) Integer stateId,
                                 @RequestParam(value = "staffId", required = false) Integer staffId,
                                 @RequestParam(value = "roleId", required = false) String roleId,
                                 @RequestParam(value = "state", required = false) Integer state) throws Exception {

        // 提取当前登录用户信息
        Integer userId = (Integer) request.getAttribute("userId");
        String userName = (String) request.getAttribute("userName");
        // Integer userOrgaType = (Integer)request.getAttribute("userOrgaType");
        // try {
        // } catch (Exception e) {
        // JessrunException je = new JessrunException("信息已存在，不能重复添加",e);
        // je.setGotoURL("admin/user/gotoList.action");
        // throw je;
        //
        // }

        ModelAndView mav = new ModelAndView("/common/message.jsp");
        String url = "admin/user/gotoList.action";
        mav.addObject("url", url);
        return mav;
    }

    @RequestMapping(value = "/admin/user/add.action", method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request,
                            @RequestParam(value = "companyId", required = false) Integer companyId,
                            @RequestParam(value = "accountNum", required = false) String accountNum,
                            @RequestParam(value = "password", required = false) String password,
                            @RequestParam(value = "stateId", required = false) Integer stateId,
                            @RequestParam(value = "staffName", required = false) String staffName,
                            @RequestParam(value = "staffCarNum", required = false) String staffCarNum,
                            @RequestParam(value = "staffBirth", required = false) Date staffBirth,
                            @RequestParam(value = "staffNation", required = false) String staffNation,
                            @RequestParam(value = "staffmobileNum1", required = false) String staffmobileNum1,
                            @RequestParam(value = "staffmobileNum2", required = false) String staffmobileNum2,
                            @RequestParam(value = "phoneNum", required = false) String phoneNum,
                            @RequestParam(value = "address", required = false) String address,
                            @RequestParam(value = "nowaddress", required = false) String nowaddress,
                            @RequestParam(value = "roleId", required = false) String roleId,
                            @RequestParam(value = "state", required = false) Integer state) throws Exception {

        Integer userId = (Integer) request.getAttribute("userId");
        String userName = (String) request.getAttribute("userName");

        // try {
        certifyService.insertMangerDate(companyId, accountNum, password, stateId, staffName, roleId, state, userId,
                                        userName, staffCarNum, staffBirth, staffNation, staffmobileNum1,
                                        staffmobileNum2, phoneNum, address, nowaddress);
        // } catch (Exception e) {
        // // TODO: handle exception
        // JessrunException je = new JessrunException("信息已存在，不能重复添加",e);
        // je.setGotoURL("admin/user/gotoList.action");
        // throw je;
        // }

        ModelAndView mav = new ModelAndView("/common/message.jsp");
        String url = "admin/user/gotoList.action";
        mav.addObject("url", url);
        return mav;
    }

    /**
     * 用于验证数据
     * 
     * @param accountNum
     * @param defaultCompanyNameSim
     * @return
     */
    @RequestMapping(value = "admin/user/verificationDate.action", method = RequestMethod.POST)
    @ResponseBody
    public String verificationDate(@RequestParam(value = "accountNum", required = false) String accountNum,
                                   @RequestParam(value = "defaultCompanyNameSim", required = false) String defaultCompanyNameSim) {
        Integer result = certifyService.checkDateIsExits(accountNum, defaultCompanyNameSim);
        return result.toString();
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

    // 日期装换
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 处理登出的ACTION
     * 
     * @return
     */
    @RequestMapping(value = { "testExcelOutput.do" }, method = RequestMethod.GET)
    public ModelAndView testExcelOutput() {

        ExcelView ev = new ExcelView(TestExcelOutput.class);

        List<TestExcelOutput> teoList = new LinkedList<TestExcelOutput>();

        TestExcelOutput teo = new TestExcelOutput();
        teo.setName("张三");
        teo.setAge(20);
        teo.setDeptName("质检部");
        teo.setNation("汉");
        teo.setOvertime(10);
        teoList.add(teo);

        teo = new TestExcelOutput();
        teo.setName("李四");
        teo.setAge(22);
        teo.setDeptName("财务部");
        teo.setNation("汉");
        teo.setOvertime(12);
        teoList.add(teo);

        teo = new TestExcelOutput();
        teo.setName("王五");
        teo.setAge(19);
        teo.setDeptName("后勤部");
        teo.setNation("蒙古");
        teo.setOvertime(20);
        teoList.add(teo);

        teo = new TestExcelOutput();
        teo.setName("赵六");
        teo.setAge(25);
        teo.setDeptName("经理部");
        teo.setNation("汉");
        teo.setOvertime(2);
        teoList.add(teo);

        ev.loadData(teoList);

        // 指定跳转到首页
        ModelAndView mav = new ModelAndView(ev);
        return mav;
    }
}
