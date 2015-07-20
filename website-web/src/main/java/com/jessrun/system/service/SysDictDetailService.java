package com.jessrun.system.service;

import com.jessrun.system.domain.SysDictDetailVO;
import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import java.util.List;
import java.util.Map;


public interface SysDictDetailService extends JessrunMonitor {
         
		 public int saveObject(SysDictDetailVO  obj); 

		 public int updateObject(SysDictDetailVO  obj); 

		 public int deleteById(String  id);

		 public SysDictDetailVO selectById(String id);

		 public List<SysDictDetailVO> selectListByPage(Map<String,Object> model);

}