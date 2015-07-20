package com.jessrun.system.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SysDictVO  implements  Serializable  {

 	private static final long serialVersionUID = 1L;
    private String id;//
    private String name;//
    private String code;//
    private String remark;//
    private Date createTime;//
    
    public SysDictVO(){
        super();
    }
	
}
