package com.jessrun.rbac.domain;

import java.util.List;

import lombok.Data;

import com.jessrun.common.web.ValueObject;

@Data
public class SysResourcesVO  implements  ValueObject {

 	private static final long serialVersionUID = 1L;
    private String id;//fid
    private String name;//菜单名称
    private String url;//菜单url
    private String pid;//父菜单项 （根节点pid为0）
    private String order;
    private String pname;//上级菜单名称
    private SysFunctionsVO sysFunctionsVO;
    
    private List<SysResourcesVO> children;
    
	
}
