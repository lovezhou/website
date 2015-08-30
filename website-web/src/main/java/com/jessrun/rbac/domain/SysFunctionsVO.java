package com.jessrun.rbac.domain;

import lombok.Data;

import com.jessrun.common.web.ValueObject;

@Data
public class SysFunctionsVO  implements  ValueObject {

 	private static final long serialVersionUID = 1L;
    private String id;//
    private String resourceId;//
    private String query;//查询
    private String add;//增加
    private String update;//编辑
    private String deleted;//删除
    private String view;//查看
    private String checked;//审核
    private String unchecked;//反审核
    private String exported;//导出
    private String upload;//上传
    private String download;//下载
  
    
    public SysFunctionsVO(){
        super();
    }
	
}
