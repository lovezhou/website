package com.jessrun.system.service;

import java.util.List;

import com.jessrun.common.service.Service;
import com.jessrun.common.web.ValueObject;
import com.jessrun.system.domain.SysDictVO;


public interface SysDictService extends Service {

    /**
     * 唯一性校验
     * @param vo
     * @return
     */
    boolean isUniqueExist(ValueObject vo);

    List<SysDictVO> queryDict();
         

}