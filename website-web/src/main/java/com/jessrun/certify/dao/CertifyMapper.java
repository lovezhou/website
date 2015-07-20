package com.jessrun.certify.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jessrun.certify.vo.AccoUser;
import com.jessrun.certify.vo.CertifyAccount;
import com.jessrun.certify.vo.Page;
import com.jessrun.certify.vo.Role;
import com.jessrun.certify.vo.RoleAccount;
import com.jessrun.certify.vo.User;
import com.jessrun.certify.vo.UserInfo;
import com.jessrun.common.dao.mybatis.OracleMapper;

/**
 * 操作认证数据库的DAO
 * @author Awen
 *
 */
@Repository
public interface CertifyMapper extends OracleMapper  {
	/**
	 * 根据用户的登陆编号，查询用户是否被顶下
	 * @param userInfo
	 * @return
	 */
	public UserInfo getUserInfoByLoginId(UserInfo userInfo);
	
	/**
	 * 修改密码
	 */
	public int modifyPW(@Param(value="userId")Integer userId,@Param(value="oldPW")String oldPW,@Param(value="newPW")String newPW);
	
	/**
	 * 插入一条登陆日志
	 * @param userInfo
	 * @return
	 */
	public int insertLoginLog(UserInfo userInfo);
	
	/**
	 * 更新用户信息
	 * @param userInfo
	 * @return
	 */
	public int updateUserInfoForLogin(UserInfo userInfo);
	
	/**
	 * 根据用户编号获得用户信息
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoByUserId(Integer userId);
    
	/**
	 * 根据用户和密码查询用户信息
	 * @param accountNum
	 * @param password
	 * @return
	 */
    public UserInfo getUserInfoByPassword(@Param(value="companyNameSIM")String companyNameSIM,@Param(value="accountNum")String accountNum,@Param(value="password")String password);

    /**
     * 根据用户编号获得模块权限
     * @param userId
     * @return
     */
    public List<Page> selectTitleItemsByUserId(Integer userId);
    
    /**
     * 查询用户是否可以用这个URL
     * @param userId
     *  @param url
     * @return
     */
    public List<Page> selectTitleItemsByURL(@Param(value="userId")Integer userId,@Param(value="url")String url);

    

    public List<UserInfo> getAllAdminListByPage(Map<String,Object> model);

    
    /**
     * 查询 所有 Role 表
     * @param map
     * @return
     */
    public  List<com.jessrun.certify.vo.Role> getAllRole(Map<String,Object> map);
    
    
    public int insertCertifyAccount(CertifyAccount certifyAccount);
    
    public int addCertifyUser(User user);
    
    public int addCertifyUserByStaff(User user);
    
    public int addCertifyAccountUser(AccoUser accoUser);
    
    public int addCertifyRole(Role role);
    
    public int addRoleAccount(RoleAccount roleAccount);
    
    public Integer checkDateIsExits(@Param(value="accountNum")String accountNum,@Param(value="defaultCompanyNameSim")String defaultCompanyNameSim);
     
    public void deleteByPrimaryKey(Integer userID);

    public List<User> selectUserByStaffId(Integer staffId);
    
    /**
     * 根据传入的类型和ID号查询出系统组织结构表ID号
     * @param type
     * @param id
     * @return
     */
    public Integer getCertifyDeptIdByTypeAndTypeId(@Param(value="type")Integer type,@Param(value="id")Integer id);

}
