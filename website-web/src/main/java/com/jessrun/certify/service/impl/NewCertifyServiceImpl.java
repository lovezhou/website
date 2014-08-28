package com.jessrun.certify.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.certify.dao.NewCertifyMapper;
import com.jessrun.certify.service.NewCertifyService;
import com.jessrun.certify.vo.ResourceItem;
import com.jessrun.certify.vo.UserInfo;
import com.jessrun.platform.util.MD5Util;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
public class NewCertifyServiceImpl implements NewCertifyService {
	@Autowired
	private NewCertifyMapper newCertifyMapper;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public UserInfo login(String companySIM, String account, String password,String loginIP) throws Exception {
		//这里要校验登陆
		UserInfo userInfo = newCertifyMapper.selectUserInfoByLogin(companySIM, account, password);
		if(userInfo == null){
			throw new Exception("用户不存在或密码错误");
		}
		
		userInfo.setLoginIP(loginIP);
		String loginID = MD5Util.getMD5String16(userInfo.getUserId() + userInfo.getAccountNum() + userInfo.getLoginIP() + System.currentTimeMillis());
		userInfo.setLoginId(loginID);
		
		//更新ACCOUNT信息
		newCertifyMapper.updateLoginInfo(userInfo.getId(), loginIP, loginID,userInfo.getUserId(),userInfo.getUserName());
		
		//记录登陆日志
		newCertifyMapper.insertLoginLog(userInfo);
		
		
		//返回用户信息
		return userInfo;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public void modifyPW(int id, String oldPW,String newPW,int userId,String userName) {
		
		newCertifyMapper.updatePassword(id, oldPW, newPW,userId,userName);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
	public boolean validateCurrentAccount(String companySIM,String account, String loginId) {
		return newCertifyMapper.validateAccount(companySIM, loginId, account) > 0;
	}
	

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
	public boolean hasCompanySim(String companySIM) {
		return newCertifyMapper.exisCompanySIM(companySIM) > 0;
	}
	

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS) 
	public List<ResourceItem> getResourceItems(Integer id) {
		return newCertifyMapper.selectResourceItemByAccountId(id);
	}

	public NewCertifyMapper getNewCertifyMapper() {
		return newCertifyMapper;
	}

	public void setNewCertifyMapper(NewCertifyMapper newCertifyMapper) {
		this.newCertifyMapper = newCertifyMapper;
	}
	
}
