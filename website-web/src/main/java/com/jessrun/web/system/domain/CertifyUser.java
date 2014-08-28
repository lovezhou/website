package com.jessrun.web.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @className CertifyUser
 * @depiction 用户表
 * @createTime 2013-5-27
 * @author huanko
 */
public class CertifyUser implements Serializable{

	private static final long serialVersionUID = 3270848059989036753L;

	private Integer				id;			//编号
	private String				name;		//姓名
	private String 				idNum;		//身份证号
	private String 				phoneNum1;	//联系电话1
	private String 				phoneNum2;	//联系电话2
	private Date				createdTime;//创建时间
	private Integer				createdId;	//创建人编号
	private String 				createdName;//创建人姓名
	private Date				modifyTime;	//修改时间
	private Integer				modifyId;	//修改人编号
	private String 				modifyName;	//修改人姓名
	private Integer				deptId;		//所在单位
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getCreatedId() {
		return createdId;
	}
	public void setCreatedId(Integer createdId) {
		this.createdId = createdId;
	}
	public String getCreatedName() {
		return createdName;
	}
	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getModifyId() {
		return modifyId;
	}
	public void setModifyId(Integer modifyId) {
		this.modifyId = modifyId;
	}
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getPhoneNum1() {
		return phoneNum1;
	}
	public void setPhoneNum1(String phoneNum1) {
		this.phoneNum1 = phoneNum1;
	}
	public String getPhoneNum2() {
		return phoneNum2;
	}
	public void setPhoneNum2(String phoneNum2) {
		this.phoneNum2 = phoneNum2;
	}
	
	
}
