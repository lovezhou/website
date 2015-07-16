package com.jessrun.system.service;

import com.jessrun.system.domain.SysDictVO;
import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import java.util.List;
import java.util.Map;


public interface SysDictService extends JessrunMonitor {
         
		 public int saveObject(SysDictVO  obj); 

		 public int updateObject(SysDictVO  obj); 

		 public int deleteById(String  id);

		 public SysDictVO selectById(String id);

		 public List<SysDictVO> selectListByPage(Map<String,Object> model);

}