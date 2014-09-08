package com.jessrun.system.domain;

import java.io.Serializable;
import java.util.Date;

public class SysDict  implements  Serializable  {

 	private static final long serialVersionUID = 1L;
    private String dictId;//字典编号
    private String dictName;//字典名称
    private String dictCode;//字典代码
    private String creatorId;//添加人id
    private Date creatorTime;//添加时间
    private String editorId;//修改人id
    private Date editorTime;//修改时间
    private String isDeleted;//是否删除,0：不删除，1：表示删除
    private String remark;//备注
    
    public SysDict(){
        super();
    }

    
    public String getDictId() {
        return dictId;
    }

    
    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    
    public String getDictName() {
        return dictName;
    }

    
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    
    public String getDictCode() {
        return dictCode;
    }

    
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    
    public String getCreatorId() {
        return creatorId;
    }

    
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    
    public Date getCreatorTime() {
        return creatorTime;
    }

    
    public void setCreatorTime(Date creatorTime) {
        this.creatorTime = creatorTime;
    }

    
    public String getEditorId() {
        return editorId;
    }

    
    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    
    public Date getEditorTime() {
        return editorTime;
    }

    
    public void setEditorTime(Date editorTime) {
        this.editorTime = editorTime;
    }

    
    public String getIsDeleted() {
        return isDeleted;
    }

    
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark;
    }
	
}

