package com.jessrun.codegenerator.service.imp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jessrun.codegenerator.dao.CodeGeneratorMapper;
import com.jessrun.codegenerator.domain.ColumnAndType;
import com.jessrun.codegenerator.service.CodeGeneratorService;
import com.jessrun.platform.util.EmptyUtils;

@Service
public class CodeGeneratorServiceImp implements CodeGeneratorService {

    @Autowired
    private  CodeGeneratorMapper codeGeneratorMapper;
    
    public List<ColumnAndType>  getListColumnAndType(String tableName){
       List<ColumnAndType>  list = codeGeneratorMapper.getListColumnAndType(tableName);
       //将字段名称映射为对应的属性名
       if(EmptyUtils.isNotEmptyList(list)){
           for(ColumnAndType tmp:list){
               String columnName = tmp.getColumnName().toLowerCase();
               String propertyName = convertColumnToProperty(columnName);
               tmp.setPropertyName(propertyName.substring(1));
           }
       }
       return  list; 
    }
    
    /**
     * 将数据库字段名转化为属性名称
     * 例如
     *   dict ->dict
     *   dict_id -> dictId
     *   dict_detail_remark -> dictDetailRemark
     * @param columnName
     * @return
     */
   private  String  convertColumnToProperty(String columnName){
        String[] property  = columnName.split("_");
        String propertyName="";
        String upperLow="";
        if(property.length>1){
            propertyName=property[0];
            for(int i=1 ; i<property.length;i++){
                String upper = "";
                String low ="";
                if(property[i].length()>1){
                    upper=property[i].substring(0,1).toUpperCase();
                    low=property[i].substring(1,property[i].length());
                    upperLow=upper+low;
                }else{
                    upperLow=property[i].toLowerCase();
                }
                propertyName=propertyName+upperLow;
            }
        }else{
            propertyName =property[0];
        }
        return propertyName;
    }
    
    
    
   /**
    * 将tableName转变为对应的className
    * @param tableName
    * @return
    */
    public  String convertTableNameToClassName(String tableName){
        String className=convertColumnToProperty(tableName);
        className= className.substring(1,className.length());
        return className;
    }

    /**
     * 解析前台传过来的参数
     */
    @Override
    public Map<String,Object> codeGenerator(Map<String, String[]> map) throws IOException {
    	Map<String,Object> params= new HashMap<String,Object>();
        //参数过滤
        Map<String,String>  paramMap = new HashMap<String,String>();
        Map<String,String[]> arrayMap = new HashMap<String,String[]>();
        Set<String>  set = map.keySet();
        Iterator<String> itr = set.iterator();
        while(itr.hasNext()){
            String key = (String)itr.next();
            String[] value =  (String[])map.get(key);
            if(value!=null){
                if(value.length==1){
                    paramMap.put(key, value[0]);
                }else{
                    arrayMap.put(key, value);
                }
            }
        }
        
        //类名称
        String  className = paramMap.get("className");
        //前台js
        String  jsPath = paramMap.get("jsPath");
        String  jspPath = paramMap.get("jspPath");
        String  packageName = paramMap.get("packageName");
        String  javaSrcPath = paramMap.get("javaSrcPath");
        //是否生成jsp，js
        String  jsp = paramMap.get("jsp");
        //后台 BasePath
        String  basePath= paramMap.get("basePath");
        //是否生成controller
        String  controller = paramMap.get("controller");
        //是否生成service
        String  service = paramMap.get("service");
        //是否生成dao
        String  dao = paramMap.get("dao");
        //数据库类型
        String  databaseType= paramMap.get("databaseType");
        
        String tableName = paramMap.get("tableName");
        
        params.put("className", className);
        params.put("jsPath",jsPath );
        params.put("jspPath",jspPath );
        params.put("packageName",packageName );
        params.put("javaSrcPath",javaSrcPath );
        params.put("isJsp",jsp );
        params.put("basePath",basePath );
        params.put("isController",controller );
        params.put("isService",service );
        params.put("isDao",dao );
        params.put("databaseType",databaseType );
        params.put("tableName",tableName);
     
        
        String[]   columnName = arrayMap.get("columnName");
        String[]   propertyName = arrayMap.get("propertyName");
        String[]   dataType = arrayMap.get("dataType");
        String[]   comments = arrayMap.get("comments");
        String[]   nullable = arrayMap.get("nullable");
        String[]   chkCode = arrayMap.get("chk_code");
        String[]   chkCond= arrayMap.get("chk_cond");
        //获取tableName的主键 只支持唯一主键
        String pkColumn= codeGeneratorMapper.getPKandColumn(tableName.toUpperCase()).get("PKCOLUMN");
        List<ColumnAndType> list=  new ArrayList<ColumnAndType>();
        for (int i = 0; i < columnName.length; i++) {
        	ColumnAndType  tmp = new ColumnAndType(columnName[i],dataType[i],0,0,0,"",nullable[i],comments[i],propertyName[i],chkCode[i],chkCond[i]);
        	if(columnName[i].equalsIgnoreCase(pkColumn)){
        	    tmp.setIsPk("1");
        	}else{
        	    tmp.setIsPk("0");
        	}
        	list.add(tmp);
    	}
        
        params.put("list",list);
     // System.out.println("jspPath"+jspPath+"--------basePath="+basePath);
        return params;
    }
        
    
   
    
    
  
}
