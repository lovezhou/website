package com.jessrun.certify.vo;

import java.util.Date;

/**
 * @author kehuan
 * @createdTime 2013-04-22
 */
public class User {
	private Integer userID;
	private String userName;
	private String iDNum;
	private Date birthday;
	private String nation;
 	private String mobileNum1;
	private String mobileNum2;
	private String phoneNum;
	private String address;
	private String nowAddress;
	private Date createdTime;
	private Integer createdUserID;
	private String createdUserName;
	private Date modifyTime;
	private Integer modifyUserID;
	private String modifyUserName;
	private Integer staffId;
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getiDNum() {
		return iDNum;
	}
	public void setiDNum(String iDNum) {
		this.iDNum = iDNum;
	}
	
	public String getMobileNum1() {
		return mobileNum1;
	}
	public void setMobileNum1(String mobileNum1) {
		this.mobileNum1 = mobileNum1;
	}
	public String getMobileNum2() {
		return mobileNum2;
	}
	public void setMobileNum2(String mobileNum2) {
		this.mobileNum2 = mobileNum2;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNowAddress() {
		return nowAddress;
	}
	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getCreatedUserID() {
		return createdUserID;
	}
	public void setCreatedUserID(Integer createdUserID) {
		this.createdUserID = createdUserID;
	}
	public String getCreatedUserName() {
		return createdUserName;
	}
	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getModifyUserID() {
		return modifyUserID;
	}
	public void setModifyUserID(Integer modifyUserID) {
		this.modifyUserID = modifyUserID;
	}
	public String getModifyUserName() {
		return modifyUserName;
	}
	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	
}
