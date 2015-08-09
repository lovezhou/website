package ${packageName}.domain;

import java.util.Date;

import lombok.Data;

import com.jessrun.common.web.ValueObject;

@Data
public class ${className}VO  implements  ValueObject {

 	private static final long serialVersionUID = 1L;
    <#list list as vo>
    private <#if vo.dataType=="VARCHAR2" >String<#elseif vo.dataType=="DATE" >Date<#elseif vo.dataType=="NUMBER" >Integer</#if> ${vo.propertyName};//${vo.comments}
    </#list>
    
    public ${className}VO(){
        super();
    }
	
}
