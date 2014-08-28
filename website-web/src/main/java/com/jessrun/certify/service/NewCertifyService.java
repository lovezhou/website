package com.jessrun.certify.service;

import java.util.List;

import com.jessrun.certify.vo.ResourceItem;
import com.jessrun.certify.vo.UserInfo;

public interface NewCertifyService {
	public UserInfo login(String companySIM,String account,String password,String loginIP) throws Exception;
	
	public void modifyPW(int id, String oldPW,String newPW,int userId,String userName);
	
	public boolean validateCurrentAccount(String companySIM,String account,String loginId);
	
	public boolean hasCompanySim(String companySIM);
	
	public List<ResourceItem> getResourceItems(Integer id);
}
