package com.jessrun.system.domain;

import java.util.Date;

import lombok.Data;

import com.jessrun.common.web.ValueObject;

@Data
public class SysDictDetailVO  implements  ValueObject {

 	private static final long serialVersionUID = 1L;
    private String id;//id
    private String dictId;//与t_sys_dict表id关联
    private String dictText;
    private String code;//字典名称
    private String name;//字典代码
    private Integer order;//序号
    private String remark;//备注
    private Date createTime;//创建时间
    
    public SysDictDetailVO(){
        super();
    }
	
}
