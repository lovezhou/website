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
     * @param map
     * @return
     */
    public String codeGenerator(Map<String, String[]> map)throws IOException;
}
