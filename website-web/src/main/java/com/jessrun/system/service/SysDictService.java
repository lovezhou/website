package com.jessrun.system.service;

import com.jessrun.system.domain.SysDict;
import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import java.util.List;
import java.util.Map;


public interface SysDictService extends JessrunMonitor {
         
         public int saveObject(SysDict  obj); 

         public int updateObject(SysDict  obj); 

         public int deleteById(String  id);

         public SysDict selectById(String id);

         public List<SysDict> selectListByPage(Map<String,Object> model);

}

