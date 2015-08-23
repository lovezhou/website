package com.jessrun.rbac.service;

import com.jessrun.common.service.Service;
import com.jessrun.common.web.ValueObject;



public interface SysFunctionsService extends Service {
         
	/**
     * 唯一性校验
     * @param vo
     * @return
     */
    boolean isUniqueExist(ValueObject vo);
         
}