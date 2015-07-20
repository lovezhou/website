package com.jessrun.web.system.domain;

public class ConfCost {
	private Integer index;
	private String costName;
	private String costCode;
	private String costType;
	private String costVal;
	private String aliasName;
	
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
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
	
    public String getAliasName() {
        return aliasName;
    }
    
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
    
    
    public boolean equals(Object obj){
		
		if(!(obj instanceof ConfCost)){
			return false;
		}
		ConfCost temp = (ConfCost)obj;
		return this.costCode.equals(temp.getCostCode());
		
	}
}
