package com.jessrun.certify.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jessrun.certify.vo.CertifyAccount;
import com.jessrun.certify.vo.Page;
import com.jessrun.certify.vo.UserInfo;

public interface CertifyService {

    public boolean isCurrentUser(UserInfo userInfo);

    public UserInfo getUserInfoByUserId(Integer userId);

    public UserInfo login(String companyNameSIM, String accountNum, String password, String loginIP) throws Exception;

    public void modifyPW(Integer userId, String oldPW, String newPW) throws Exception;

    public List<Page> getTitleItemsByUserId(Integer userId);

    public boolean passURL(Integer userId, String url);

    public List<UserInfo> getAllAdminListByPage(Map<String, Object> model);

    public List<com.jessrun.certify.vo.Role> getAllRole(Map<String, Object> map);

    public int addCertifyAccount(CertifyAccount certifyAccount) throws Exception;

    public void insertMangerDate(Integer companyId, String accountNum, String password, Integer stateId,
                                 String staffName, String roleId, Integer state, Integer userId, String userName,
                                 String staffCarNum, Date staffBirth, String staffNation, String staffmobileNum1,
                                 String staffmobileNum2, String phoneNum, String address, String nowaddress)
                                                                                                            throws Exception;

    public Integer checkDateIsExits(@Param(value = "accountNum") String accountNum,
                                    @Param(value = "defaultCompanyNameSim") String defaultCompanyNameSim);

    /**
     * 根据传入的类型和ID号查询出系统组织结构表ID号
     * 
     * @param type
     * @param id
     * @return
     */
    public Integer getCertifyDeptIdByTypeAndTypeId(@Param(value = "type") Integer type, @Param(value = "id") Integer id);
}
