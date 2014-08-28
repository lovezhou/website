package com.jessrun.codegenerator.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class ColumnAndType  implements  Serializable  {
    
    private static final long serialVersionUID = 1L;
    private String  columnName; //字段名
    private String dataType;
    private int dataLength;  //长度
    private int dataPrecision; //整数位
    private int dataScale; //小数位
    private String  nullable;  //是否为空
    private String dataDefault; //默认值
    private String comments;//注释
    private String  propertyName;//java对应的code

}
