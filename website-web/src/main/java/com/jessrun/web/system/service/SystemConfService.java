package com.jessrun.web.system.service;

import java.util.List;

import com.jessrun.web.system.domain.ConfCost;
import com.jessrun.web.system.domain.ConfGrid;
import com.jessrun.web.system.domain.ConfGridInfo;

public interface SystemConfService {

    /**
     * 获得财务款项
     */
    public List<ConfCost> getCostConfList(Integer companyId);

    /**
     * 获取指定列表项公司配置项
     * 
     * @param companyId
     * @param GridType
     * @return
     */
    public List<ConfGrid> getGridConfList(Integer companyId, Integer gridInfoId);

    /**
     * 查询指定列表所有项
     * 
     * @param GridType
     * @return
     */
    public List<ConfGrid> getAllGridConfList(Integer companyId, Integer gridInfoId);

    public List<ConfCost> getAllCostConfList();

    /**
     * 通过IDX获取列表项
     * 
     * @param idx
     * @return
     */
    public ConfCost getConfCostItemByIdx(Integer idx);

    public List<ConfGridInfo> getAllGridInfoList();

    public void setCostConf(int companyId, String flags, Integer userId, String userName);

    public void updateGridConf(int companyId, int gridInfoId, List<ConfGrid> confGrids);

    public void saveConfGridAlias(Integer companyId, Integer itemId, String aliasName);

    public void saveConfCostAlias(Integer companyId, Integer itemId, String aliasName);

    public void rebackConfGridAlias(Integer companyId, Integer itemId);

    public void rebackConfCostAlias(Integer companyId, Integer itemId);
}
