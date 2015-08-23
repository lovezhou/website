package com.jessrun.system.service;

import com.jessrun.common.service.Service;
import com.jessrun.common.web.ValueObject;



public interface SysUserService extends Service {
         
	/**
     * 唯一性校验
     * @param vo
     * @return
     */
    boolean isUniqueExist(ValueObject vo);
         
}