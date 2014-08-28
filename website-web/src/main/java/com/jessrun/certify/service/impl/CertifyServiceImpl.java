package com.jessrun.certify.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.certify.dao.CertifyMapper;
import com.jessrun.certify.service.CertifyService;
import com.jessrun.certify.vo.CertifyAccount;
import com.jessrun.certify.vo.Page;
import com.jessrun.certify.vo.Role;
import com.jessrun.certify.vo.UserInfo;
import com.jessrun.platform.util.MD5Util;

/**
 * 认证操作SERVICE
 * 
 * @author Awen
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CertifyServiceImpl implements CertifyService {

    /**
     * 认证数据库操作DAO
     */
    @Autowired
    protected CertifyMapper certifyMapper;

    /**
     * 判断用户是否是当前合法用户，判断是否被顶掉
     * 
     * @param userInfo
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public boolean isCurrentUser(UserInfo userInfo) {
        return certifyMapper.getUserInfoByLoginId(userInfo) != null;
    }

    /**
     * 根据用户编号查询用户信息，如果查不到返回为空
     * 
     * @param userId
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserInfo getUserInfoByUserId(Integer userId) {
        return certifyMapper.getUserInfoByUserId(userId);
    }

    /**
     * 登陆操作
     * 
     * @param accountNum 帐号
     * @param password 密码
     * @param loginIP 登陆IP
     * @return
     * @throws Exception
     */
    @Transactional(value = "OracletransactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserInfo login(String companyNameSIM, String accountNum, String password, String loginIP) throws Exception {
        // 根据帐号密码查询用户信息，查得到就是帐号密码输入正确，查不到返回为空
        UserInfo userInfo = certifyMapper.getUserInfoByPassword(companyNameSIM, accountNum, password);

        // 判断是否登陆失败
        if (userInfo == null) {// 失败
            // 抛出异常
            // throw new Exception("CERTIFY.LOGIN.ACCOUNT_OR_PASSWORD_ERROR");
            throw new Exception("用户名或密码错误");
        }

        userInfo.setLoginTime(new Date());
        userInfo.setLoginIP(loginIP);

        // 算出用户此次登陆的登陆编号
        String loginId = null;

        for (int i = 0; i < 3; i++) {
            loginId = MD5Util.getMD5String16(userInfo.getUserId() + userInfo.getAccountNum() + userInfo.getLoginIP()
                                             + System.currentTimeMillis());

            userInfo.setLoginId(loginId);
            try {
                // 插入一条用户的登陆日志
                certifyMapper.insertLoginLog(userInfo);
            } catch (Exception e) {
                if (i == 2) {
                    throw e;
                }
                try {
                    Thread.sleep(10);
                } catch (Exception ex) {
                }
            }
        }

        // 更新用户的登陆编号
        certifyMapper.updateUserInfoForLogin(userInfo);

        // 返回用户信息
        return userInfo;
    }

    /**
     * 根据用户编号，获得用户的模块权限
     * 
     * @param userId
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Page> getTitleItemsByUserId(Integer userId) {
        return certifyMapper.selectTitleItemsByUserId(userId);
    }

    @Override
    @Transactional(value = "OracletransactionManager", readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public void modifyPW(Integer userId, String oldPW, String newPW) throws Exception {
        if (certifyMapper.modifyPW(userId, oldPW, newPW) == 0) {
            throw new Exception("修改密码失败");
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public boolean passURL(Integer userId, String url) {
        return certifyMapper.selectTitleItemsByURL(userId, url).size() > 0;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public int addCertifyAccount(CertifyAccount certifyAccount) throws Exception {
        return certifyMapper.insertCertifyAccount(certifyAccount);
    }

    @Override
    public Integer checkDateIsExits(String accountNum, String defaultCompanyNameSim) {
        return certifyMapper.checkDateIsExits(accountNum, defaultCompanyNameSim);
    }

    @Override
    public Integer getCertifyDeptIdByTypeAndTypeId(@Param("type") Integer type, @Param("id") Integer id) {
        return certifyMapper.getCertifyDeptIdByTypeAndTypeId(type, id);
    }

    @Override
    public List<UserInfo> getAllAdminListByPage(Map<String, Object> model) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Role> getAllRole(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insertMangerDate(Integer companyId, String accountNum, String password, Integer stateId,
                                 String staffName, String roleId, Integer state, Integer userId, String userName,
                                 String staffCarNum, Date staffBirth, String staffNation, String staffmobileNum1,
                                 String staffmobileNum2, String phoneNum, String address, String nowaddress)
                                                                                                            throws Exception {
        // TODO Auto-generated method stub

    }

}
