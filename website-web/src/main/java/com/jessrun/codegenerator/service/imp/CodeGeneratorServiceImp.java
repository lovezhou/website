package com.jessrun.codegenerator.service.imp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
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
               tmp.setPropertyName(propertyName);
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
     * 解析前台闯过来的参数
     */
    @Override
    public String codeGenerator(Map<String, String[]> map) throws IOException {
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
        
        String[]   columnName = arrayMap.get("columnName");
        String[]   propertyName = arrayMap.get("propertyName");
        String[]   dataType = arrayMap.get("dataType");
        String[]   comments = arrayMap.get("comments");
        String[]   nullable = arrayMap.get("nullable");
        String[]   chk_code = arrayMap.get("chk_code");
        String[]   chk_cond= arrayMap.get("chk_cond");
        //类名称
        String  className = paramMap.get("className");
        //前台js，jsp  基路径:
        String  jspPath = paramMap.get("jspPath");
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
        System.out.println("jspPath"+jspPath+"--------basePath="+basePath);
        //X:\source\website-web\src\main\java\com\jessrun\system
        
        
        if(EmptyUtils.isNotEmptyArray(columnName)){
             for (int i = 0; i < columnName.length; i++) {
                 System.out.println(columnName[i]+"--"+propertyName[i]+"--"+dataType[i]+
                                    "--"+comments[i]+"--"+nullable[i]+"--"+chk_code[i]+"--"+chk_cond[i]);
                 
             }           
        }
        generatorDomain(basePath,className,propertyName,dataType,comments);
        generatorDao(dao,basePath,className,databaseType);
        return null;
    }
        
    
    
    private  void  generatorDao(String isGeneratorDao,String filePath,String className,String databaseType) throws IOException{
        //构建dao路径
        String basePackage = filePath.substring(filePath.indexOf("\\src\\main\\java")+15,filePath.length()).replace("\\", ".");
        String daoPath = filePath+"\\dao";
        File dao = new File(daoPath);
        if(!dao.exists()){
            dao.mkdirs();
        }
        //构建mapper路径
        String mapperPath= daoPath+"\\conf";
        File mapper = new File(mapperPath);
        if(!mapper.exists()){
            mapper.mkdirs();
        }
        //创建dao
        if(isGeneratorDao.equals("1")){
            //生成dao
            String mapperName = className+"Mapper.java";
            File  mapperfile = new File(daoPath,mapperName);
            //存在先删除
            if(mapperfile.exists()){
                mapperfile.delete();
            }
            Writer  out = new FileWriter(mapperfile);
            StringBuffer sb = new StringBuffer();
            String daoPackageName = basePackage+".dao";
            //生成packageName
            sb.append("package "+ daoPackageName +";\n\n");
            sb.append("import org.springframework.stereotype.Repository;\n");
            sb.append("import com.jessrun.common.dao.mybatis.OracleMapper;\n");
            //TODO 引入实体
            sb.append("import "+basePackage+".domain."+className+";\n");
            sb.append("import java.util.List;\n");
            sb.append("import java.util.Map;\n");
            //生成className
            sb.append("@Repository\n");
            if(databaseType.equals("mysql")){
                sb.append("public interface "+className+"Mapper extends MysqlMapper  {\n");
            }else if(databaseType.equals("oracle")){
                sb.append("public interface "+className+"Mapper extends OracleMapper {\n");
            }
            //定义增删改成方法
            sb.append("\t\t int saveObject("+className+"  obj); \n\n");
            sb.append("\t\t int updateObject("+className+"  obj); \n\n");
            sb.append("\t\t int deleteById(String  id);\n\n");
            sb.append("\t\t "+className+" selectById(String id);\n\n");
            sb.append("\t\t List<"+className+"> selectListByPage(Map<String,Object> model);\n\n");
            sb.append("}");
            out.write(sb.toString());
            out.flush();
            out.close();
            //创建mapper文件
            String xmlName = className+"Mapper.xml";
            File xmlfile = new File(mapperPath,xmlName);
            Writer  outXml = new FileWriter(xmlfile);
            //存在先删除
            if(xmlfile.exists()){
                xmlfile.delete();
            }
            sb= new StringBuffer();
            //头信息
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n\n\n");
            sb.append("<mapper namespace=\""+daoPackageName+"."+className+"\" >");
            
            
            sb.append("</mapper>\n\n");
            outXml.write(sb.toString());
            outXml.flush();
            outXml.close();

        }
    }
    
    private void  generatorService(String yesOrNo){
        if(yesOrNo.equals("1")){
            
            
        }
        
    }
    
    private void  generatorController(String yesOrNo){
        if(yesOrNo.equals("1")){
            
            
        }
    }
    
    private void generatorDomain(String filePath,String className,String[] propertyName, String[] dataType,String[] comments) throws IOException{
        String basePackage = filePath.substring(filePath.indexOf("\\src\\main\\java")+15,filePath.length()).replace("\\", ".");
        String domainPath= filePath+"\\domain";
        File domain = new File(domainPath);
        if(!domain.exists()){
            domain.mkdirs();
        }
       
        File domainfile = new File(domainPath,className+".java");
        Writer  out = new FileWriter(domainfile);
        StringBuffer sb = new StringBuffer();
        String domainPackageName= basePackage+".domain";
        //生成packageName
        sb.append("package "+ domainPackageName +";\n\n");
        sb.append("import java.io.Serializable;\n");
        sb.append("import java.util.Date;\n");
        sb.append("import lombok.Data;\n");
        //生成className
        sb.append("@Data\n");
        sb.append("public class "+className+"  implements  Serializable  {\n");
        sb.append("\t\t private static final long serialVersionUID = 1L;\n");
        //定义增删改成方法
        for (int i = 0; i < propertyName.length; i++) {
                sb.append("\t\t //"+comments[i]+"\n");
            if(dataType[i].equals("VARCHAR2")){
                sb.append("\t\t private String "+propertyName[i]+";  \n\n");
            }else if(dataType[i].equals("DATE")){
                sb.append("\t\t private String "+propertyName[i]+";  \n\n");
            }else if(dataType[i].equals("DECIMAL")){
                sb.append("\t\t private BigDecimal "+propertyName[i]+";  \n\n");
            }else if(dataType[i].equals("NUMERIC")){
                sb.append("\t\t private BigDecimal "+propertyName[i]+";  \n\n");
            }else if(dataType[i].equals("DOUBLE")){
                sb.append("\t\t private double "+propertyName[i]+";  \n\n");
            }else if(dataType[i].equals("FLOAT")){
                sb.append("\t\t private float "+propertyName[i]+";  \n\n");
            }else if(dataType[i].equals("BIGINT")){
                
            }else if(dataType[i].equals("INTEGER")){
                sb.append("\t\t private int "+propertyName[i]+";  \n\n");
            }else if(dataType[i].equals("SMALLINT")){
                
            }else if(dataType[i].equals("TINYINT")){
                
            }else if(dataType[i].equals("BIT")){
                
            }else if(dataType[i].equals("CHAR")){
                
            }else if(dataType[i].equals("VARCHAR")){
                
            }else if(dataType[i].equals("LONGVARCHAR")){
                
            }else if(dataType[i].equals("TIME")){
                
            }else if(dataType[i].equals("TIMESTAMP")){
                sb.append("\t\t private TimeStamp "+propertyName[i]+";  \n\n");
            }else if(dataType[i].equals("BINARY")){
                
            }else if(dataType[i].equals("VARBINARY")){
                
            }else if(dataType[i].equals("LONGVARBINARY")){
                
            }else if(dataType[i].equals("NULL")){
                
            }else if(dataType[i].equals("OTHER")){
                
            }else if(dataType[i].equals("BLOB")){
                
            }else if(dataType[i].equals("CLOB")){
                
            }else if(dataType[i].equals("BOOLEAN")){
                sb.append("\t\t private boolean "+propertyName[i]+";  \n\n");
            }else if(dataType[i].equals("NVARCHAR")){
                
            }else if(dataType[i].equals("UNDEFINED")){
                
            }else if(dataType[i].equals("NCHAR")){
                
            }else if(dataType[i].equals("NCLOB")){
                
            }
        }
        sb.append("}");
        out.write(sb.toString());
        out.flush();
        out.close();
    }
    
    
  
}
