package com.jessrun.system.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
@Data
public class SysDict  implements  Serializable  {
		 private static final long serialVersionUID = 1L;
		 //字典编号
		 private String dictId;  

		 //字典名称
		 private String dictName;  

		 //字典代码
		 private String dictCode;  

		 //添加人id
		 private String creatorId;  

		 //添加时间
		 private String creatorTime;  

		 //修改人id
		 private String editorId;  

		 //修改时间
		 private String editorTime;  

		 //是否删除,0：不删除，1：表示删除
		 private String isDeleted;  

		 //备注
		 private String remark;  

}