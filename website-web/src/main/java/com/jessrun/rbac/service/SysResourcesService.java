package com.jessrun.rbac.service;

import java.util.List;

import com.jessrun.common.service.Service;
import com.jessrun.common.web.ValueObject;
import com.jessrun.rbac.domain.SysResourcesVO;



public interface SysResourcesService extends Service {
         
	/**
     * 唯一性校验
     * @param vo
     * @return
     */
    boolean isUniqueExist(ValueObject vo);

    List<SysResourcesVO> selectListTree();
         
}