
package com.jessrun.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jessrun.common.web.BaseController;
import com.jessrun.system.domain.SysDictDetailVO;
import com.jessrun.system.service.SysDictDetailService;

@Controller
@RequestMapping(value = "/sysDictDetail")
public class SysDictDetailController extends BaseController {

	private SysDictDetailVO vo ;
    @Autowired
    private SysDictDetailService sysDictDetailService;
    
    public void init() {
    
    	this.setListView("/system/sysDictDetail_list");
    	this.setClazz(vo.getClass());
    	
    }

}
