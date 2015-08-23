package com.jessrun.rbac.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jessrun.common.service.Service;
import com.jessrun.common.web.BaseController;
import com.jessrun.common.web.Message;
import com.jessrun.common.web.ValueObject;
import com.jessrun.platform.util.SerializeUtils;
import com.jessrun.rbac.domain.SysResourcesVO;
import com.jessrun.rbac.service.SysResourcesService;

@Controller
@RequestMapping(value = "/sysResources")
public class SysResourcesController  extends  BaseController{

    @Autowired
    private SysResourcesService sysResourcesService;
    
    public void init() {
    	this.setListView("/rbac/sysResources_list");
    	this.setClazz(SysResourcesVO.class);
    }
    
    @Override
    public Service getService() {
        return sysResourcesService;
    }
    
    
    
    @SuppressWarnings("all")
    @ResponseBody
    @RequestMapping(value = "/queryTree.json", method = RequestMethod.POST)
    public String query(HttpServletRequest req,HttpServletResponse response)  {
        try{
            //ValueObject vo = SerializeUtils.jsonToObj(json, clazz);
            //Map<String,Object> model = JavaBeanUtil.convertBean(vo);
            List<SysResourcesVO> list= sysResourcesService.selectListTree();
            return SerializeUtils.toJson(list);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return SerializeUtils.toJson(Message.newInstance(TITLE,e.getMessage()));
        }
    }
    
    
    
   @Override
    public Object[] isUniqueExist(ValueObject vo) {
       boolean flag =  sysResourcesService.isUniqueExist(vo);
       return new Object[]{flag,"【url】必须唯一！"};
    }
    
 
 

}
