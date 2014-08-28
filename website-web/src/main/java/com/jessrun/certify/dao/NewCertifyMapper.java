package com.jessrun.certify.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jessrun.certify.vo.ResourceItem;
import com.jessrun.certify.vo.UserInfo;
import com.jessrun.common.dao.mybatis.OracleMapper;

@Repository
public interface NewCertifyMapper extends OracleMapper {
	
	public UserInfo selectUserInfoByLogin(@Param(value="companySIM")String companySIM,@Param(value="account")String account,@Param(value="pw")String pw);

	public int updateLoginInfo(@Param(value="id")Integer id,@Param(value="loginIP")String loginIP,@Param(value="loginID")String loginID,@Param(value="userID")Integer userID,@Param(value="userName")String userName);
	
	public int insertLoginLog(UserInfo userInfo);
	
	public int exisAccount(@Param(value="companySIM")String companySIM,@Param(value="account")String account);
	
	public int exisCompanySIM(@Param(value="companySIM")String companySIM);
	
	public int updatePassword(@Param(value="id")Integer id,@Param(value="oldpass")String oldpass,@Param(value="newpass")String newpass,@Param(value="userID")Integer userID,@Param(value="userName")String userName);
	
	public int validateAccount(@Param(value="companySIM")String companySIM,@Param(value="loginId")String loginId,@Param(value="account")String account);

	public List<ResourceItem> selectResourceItemByAccountId(@Param(value="id")Integer id);
}
