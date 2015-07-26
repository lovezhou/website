package com.jessrun.system.domain;

import java.util.Date;

import lombok.Data;

import com.jessrun.common.web.ValueObject;

@Data
public class SysDictVO implements  ValueObject  {

 	private static final long serialVersionUID = 1L;
    private String id;//
    private String dictName;//
    private String dictCode;//
    private String remark;//
    private Date createTime;//
    
    
    
    
}
