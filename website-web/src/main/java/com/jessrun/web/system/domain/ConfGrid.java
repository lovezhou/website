package com.jessrun.web.system.domain;

public class ConfGrid {
    
    //常量定义
    public static int GRIDINFO_ID_CARRY = 1;
    public static int GRIDINFO_ID_LOAD = 2;
    public static int GRIDINFO_ID_OUTSOURCE = 3;
    public static int GRIDINFO_ID_OUTSOURCETRA = 4;
    public static int GRIDINFO_ID_LOADREPORT = 5;
    public static int GRIDINFO_ID_REPORTMONTHRETURN = 6;
    public static int GRIDINFO_ID_STATEGETGOING = 7;
    public static int GRIDINFO_ID_NOWPAYCASH = 8;
    
    private Integer id;
    private String gridInfoId;
    private String gridDesc;
    private String itemName;
    private String itemVCode;
    private String itemDCode;
    private String aliasName;
    private Integer sort;
    private String dataType;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGridInfoId() {
        return gridInfoId;
    }
    
    public void setGridInfoId(String gridInfoId) {
        this.gridInfoId = gridInfoId;
    }

    public String getItemVCode() {
        return itemVCode;
    }
    
    public void setItemVCode(String itemVCode) {
        this.itemVCode = itemVCode;
    }
    
    public String getItemDCode() {
        return itemDCode;
    }
    
    public void setItemDCode(String itemDCode) {
        this.itemDCode = itemDCode;
    }

    
    public String getGridDesc() {
        return gridDesc;
    }

    
    public void setGridDesc(String gridDesc) {
        this.gridDesc = gridDesc;
    }

    
    public String getItemName() {
        return itemName;
    }

    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    
    public String getAliasName() {
        return aliasName;
    }
    
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getDataType() {
        return dataType;
    }

    
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

}
