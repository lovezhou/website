package com.jessrun.web.system.domain;

public class ConfCostCompany {
	private Integer idx;
	private String costName;
	private String costCode;
	private String costType;
	private String costVal;
	private String aliasName;
    private Integer sort;
    private Integer companyId;
	
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	public String getCostCode() {
		return costCode;
	}
	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public String getCostVal() {
		return costVal;
	}
	public void setCostVal(String costVal) {
		this.costVal = costVal;
	}
	
    public Integer getSort() {
        return sort;
    }
    
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    
    public Integer getCompanyId() {
        return companyId;
    }
    
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    
    public String getAliasName() {
        return aliasName;
    }
    
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
    
    public boolean equals(Object obj){
		
		if(!(obj instanceof ConfCostCompany)){
			return false;
		}
		ConfCostCompany temp = (ConfCostCompany)obj;
		return this.costCode.equals(temp.getCostCode());
		
	}
}
