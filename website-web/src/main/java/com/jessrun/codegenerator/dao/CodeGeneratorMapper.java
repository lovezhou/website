package com.jessrun.codegenerator.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jessrun.codegenerator.domain.ColumnAndType;
import com.jessrun.common.dao.mybatis.OracleMapper;


@Repository
public interface CodeGeneratorMapper extends  OracleMapper{

    List<ColumnAndType> getListColumnAndType(String tableName);

    Map<String,String> getPKandColumn(String tableName);
    
}
