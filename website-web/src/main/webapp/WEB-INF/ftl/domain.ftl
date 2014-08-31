package ${basePath}.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ${className}  implements  Serializable  {

 	private static final long serialVersionUID = 1L;
    <#list list as vo>
    private <#if vo.dataType=="VARCHAR2" >String<#elseif vo.dataType=="DATE" >Date<#elseif vo.dataType=="NUMBER" >Integer</#if> ${vo.propertyName};//${vo.comments}
    </#list>
    
    public ${className}(){
        super();
    }
	
}
