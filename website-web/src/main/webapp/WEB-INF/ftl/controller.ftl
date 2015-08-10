package ${packageName}.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jessrun.common.service.Service;
import com.jessrun.common.web.BaseController;
import com.jessrun.common.web.ValueObject;

import ${packageName}.domain.${className}VO;
import ${packageName}.service.${className}Service;
<#assign  lowclassName="${className[0]?lower_case+className[1..]}">
<#assign  lowServiceName="${lowclassName}Service">

@Controller
@RequestMapping(value = "/${lowclassName}")
public class ${className}Controller  extends  BaseController{

    @Autowired
    private ${className}Service ${lowServiceName};
    
    public void init() {
    	this.setListView("/system/${lowclassName}_list");
    	this.setClazz(${className}VO.class);
    }
    
    @Override
    public Service getService() {
        return ${lowServiceName};
    }
    
    
   @Override
    public Object[] isUniqueExist(ValueObject vo) {
       boolean flag =  ${lowServiceName}.isUniqueExist(vo);
       return new Object[]{flag,"【字典代码】必须唯一！"};
    }
    
 
 

}
