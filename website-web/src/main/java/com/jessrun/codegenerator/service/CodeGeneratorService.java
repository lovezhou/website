package com.jessrun.codegenerator.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jessrun.codegenerator.domain.ColumnAndType;




public interface CodeGeneratorService {
    
    public List<ColumnAndType>  getListColumnAndType(@Param("tableName") String tableName);

    public String convertTableNameToClassName(String tableName);

    /**
     * 根据传递的参数生成代码
     * 使用freemark解析文件
     * 
     * @param map 使用key-value形式返回到页面，共freemark调用
     * @return
     */
    public  Map<String,Object> codeGenerator(Map<String, String[]> map)throws IOException;
}
