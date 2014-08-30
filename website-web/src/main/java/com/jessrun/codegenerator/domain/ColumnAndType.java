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
    private String  chkCode ;
    private String  chkCond;//作为查询条件
    private String  isPk ;//是否是主键 ，“1”：是 “0”：不是
    
    
    
	public ColumnAndType(String columnName, String dataType, int dataLength, int dataPrecision, int dataScale, String nullable,
						 String dataDefault, String comments, String propertyName, String chkCode, String chkCond) {

		super();
		this.columnName = columnName;
		this.dataType = dataType;
		this.dataLength = dataLength;
		this.dataPrecision = dataPrecision;
		this.dataScale = dataScale;
		this.nullable = nullable;
		this.dataDefault = dataDefault;
		this.comments = comments;
		this.propertyName = propertyName;
		this.chkCode = chkCode;
		this.chkCond = chkCond;
	}



    public ColumnAndType(String columnName, String dataType, int dataLength, int dataPrecision, int dataScale,
                         String nullable, String dataDefault, String comments){
        super();
        this.columnName = columnName;
        this.dataType = dataType;
        this.dataLength = dataLength;
        this.dataPrecision = dataPrecision;
        this.dataScale = dataScale;
        this.nullable = nullable;
        this.dataDefault = dataDefault;
        this.comments = comments;
    }



    public ColumnAndType(){
        super();
    }
	
	
    
    
}
