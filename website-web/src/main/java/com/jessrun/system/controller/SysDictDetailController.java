
package com.jessrun.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jessrun.common.support.spring.monitor.JessrunMonitor;
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
        //设置toListView返回的页面
    	this.setListView("/system/sysDictDetail_list");
    	//设置vo的类型class
    	this.setClazz(SysDictDetailVO.class);
    	//设置vo的服务器service
    }
    
    
    @Override
    public JessrunMonitor getService() {
        return sysDictDetailService;
    }

}
