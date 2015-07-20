package com.jessrun.codegenerator.domain;

import java.io.Serializable;

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



    
    public String getColumnName() {
        return columnName;
    }



    
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }



    
    public String getDataType() {
        return dataType;
    }



    
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }



    
    public int getDataLength() {
        return dataLength;
    }



    
    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }



    
    public int getDataPrecision() {
        return dataPrecision;
    }



    
    public void setDataPrecision(int dataPrecision) {
        this.dataPrecision = dataPrecision;
    }



    
    public int getDataScale() {
        return dataScale;
    }



    
    public void setDataScale(int dataScale) {
        this.dataScale = dataScale;
    }



    
    public String getNullable() {
        return nullable;
    }



    
    public void setNullable(String nullable) {
        this.nullable = nullable;
    }



    
    public String getDataDefault() {
        return dataDefault;
    }



    
    public void setDataDefault(String dataDefault) {
        this.dataDefault = dataDefault;
    }



    
    public String getComments() {
        return comments;
    }



    
    public void setComments(String comments) {
        this.comments = comments;
    }



    
    public String getPropertyName() {
        return propertyName;
    }



    
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }



    
    public String getChkCode() {
        return chkCode;
    }



    
    public void setChkCode(String chkCode) {
        this.chkCode = chkCode;
    }



    
    public String getChkCond() {
        return chkCond;
    }



    
    public void setChkCond(String chkCond) {
        this.chkCond = chkCond;
    }



    
    public String getIsPk() {
        return isPk;
    }



    
    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }
	
	
    
    
}
