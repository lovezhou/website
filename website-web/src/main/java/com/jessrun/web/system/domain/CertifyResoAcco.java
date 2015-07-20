package com.jessrun.web.system.domain;

import java.io.Serializable;

/**
 * @className CertifyResoAcco
 * @depiction 用户资源表
 * @createTime 2013-5-27
 * @author huanko
 */
public class CertifyResoAcco implements Serializable{

	private static final long serialVersionUID = -2004983592287995289L;
	
	private Integer			resourceId;		//资源编号
	private Integer			accountId;		//帐号编号
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
}
