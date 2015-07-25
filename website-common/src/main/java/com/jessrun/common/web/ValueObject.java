package com.jessrun.common.web;

import java.io.Serializable;


/**
 * 数据库持久化顶级对象vo接口
 * 
 * @author zmy
 * @version 1.0, 2015-7-25
 * @since 1.0, 2015-7-25
 */
public interface ValueObject extends Serializable {

	
	public String getId();
	
	public void setId(String id);

}
