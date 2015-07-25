package com.jessrun.common.support.spring.monitor;

import java.util.List;
import java.util.Map;

import com.jessrun.common.web.ValueObject;

/**
 * 公共监控接口，该接口将会被监控系统扫描，并记录每个Service方法的资源情况
 * 
 * @author zmyue 2013-5-4 下午8:49:22
 */
public interface JessrunMonitor {

	
	 public int saveObject(ValueObject  obj); 

	 public int updateObject(ValueObject  obj); 

	 public int deleteById(String  id);

	 public ValueObject selectById(String id);

	 public List<? extends ValueObject > selectListByPage(Map<String,Object> model);
	
}
