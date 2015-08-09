package com.jessrun.common.service;

import java.util.List;
import java.util.Map;

import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import com.jessrun.common.web.ValueObject;


public interface Service extends JessrunMonitor {

    public int saveObject(ValueObject  obj); 

    public int updateObject(ValueObject  obj); 

    public int deleteById(String  id);
    
    public int deleteByIds(List<String> ids);

    public ValueObject selectById(String id);
    
    public List<? extends ValueObject > selectListByPage(Map<String,Object> model);
    
}
