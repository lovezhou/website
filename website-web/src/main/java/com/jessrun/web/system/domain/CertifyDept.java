package com.jessrun.web.system.domain;

import java.io.Serializable;

/**
 * @className CertifyDept
 * @depiction 组织架构表
 * @createTime 2013-5-27
 * @author huanko
 */
public class CertifyDept implements Serializable{
	private static final long serialVersionUID = -4571295445573773359L;
	private Integer			id;					//编号
	private String 			name;				//单位名称
	private Integer			type;				//单位类型	0：其他,1:平台,2:公司,3:站点,4：部门
	private Integer			recordId;			//记录编号
	private Integer			companyId;			//所属公司ID
	private String			companyName;		//所属公司名称
	private String 			companyNameSim;		//所属公司缩写
	private String 			companyNameSimCn;	//所属公司简称
	private Integer			stateId;			//所属站点ID
	private String			stateName;			//所属站点名称
	private Integer			parentId;			//父编号
	private Integer			deptId;				//所属部门编号
	private String 			deptName;			//所属部门名称
	private Integer			status;				//记录状态	0：注销,1:有效
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyNameSim() {
		return companyNameSim;
	}
	public void setCompanyNameSim(String companyNameSim) {
		this.companyNameSim = companyNameSim;
	}
	public String getCompanyNameSimCn() {
		return companyNameSimCn;
	}
	public void setCompanyNameSimCn(String companyNameSimCn) {
		this.companyNameSimCn = companyNameSimCn;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
