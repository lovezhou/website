package com.jessrun.system.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
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
	
}

