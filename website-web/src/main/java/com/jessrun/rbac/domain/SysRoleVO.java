package com.jessrun.rbac.domain;

import java.util.Date;

import lombok.Data;

import com.jessrun.common.web.ValueObject;

@Data
public class SysRoleVO  implements  ValueObject {

 	private static final long serialVersionUID = 1L;
    private String id;//fid
    private String roleName;//角色名称
    private String roleRamark;//备注
    
    public SysRoleVO(){
        super();
    }
	
}
