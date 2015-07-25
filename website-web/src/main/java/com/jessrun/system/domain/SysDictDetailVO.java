package com.jessrun.system.domain;

import java.io.Serializable;
import java.util.Date;

import com.jessrun.common.web.ValueObject;

import lombok.Data;

@Data
public class SysDictDetailVO   implements  ValueObject  {

 	private static final long serialVersionUID = 1L;
    private String id;//
    private String dictId;//与t_sys_dict表id关联
    private String code;//
    private String name;//
    private Integer order;//
    private String remark;//
    private Date createTime;//
    
    public SysDictDetailVO(){
        super();
    }
	
}
