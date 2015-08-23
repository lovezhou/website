package com.jessrun.rbac.domain;

import lombok.Data;

import com.jessrun.common.web.ValueObject;

@Data
public class SysResourcesVO  implements  ValueObject {

 	private static final long serialVersionUID = 1L;
    private String id;//fid
    private String name;//菜单名称
    private String url;//菜单url
    private String pid;//父菜单项 （根节点pid为0）
    private SysFunctionsVO sysFunctionsVO;
    
    public SysResourcesVO(){
    }
	
}
