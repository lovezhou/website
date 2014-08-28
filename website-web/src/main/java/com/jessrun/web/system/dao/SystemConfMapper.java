package com.jessrun.web.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jessrun.common.dao.mybatis.OracleMapper;
import com.jessrun.web.system.domain.ConfCost;
import com.jessrun.web.system.domain.ConfGrid;
import com.jessrun.web.system.domain.ConfGridInfo;

public interface SystemConfMapper extends OracleMapper {

    public String getBillNum(@Param(value = "companyId") Integer companyId, @Param(value = "billType") Integer billType);

    public List<ConfCost> getCostConfList(@Param(value = "companyId") Integer companyId);

    public List<ConfCost> getAllCostConfList();
    
    /**
     * 通过IDX获取列表项
     * @param idx
     * @return
     */
    public ConfCost getConfCostItemByIdx(@Param(value = "idx") Integer idx);

    public List<ConfGrid> getGridConfList(@Param(value = "companyId") Integer companyId,
                                          @Param(value = "gridInfoId") Integer gridInfoId);

    public List<ConfGrid> getAllGridConfList(@Param(value = "companyId") Integer companyId,@Param(value = "gridInfoId") Integer gridInfoId);

    public List<ConfGridInfo> getAllGridInfoList();

    public int setCostConf(@Param(value = "companyId") Integer companyId, @Param(value = "flags") String flags,
                           @Param(value = "userId") Integer userId, @Param(value = "userName") String userName);

    public int deleteGridConf(@Param(value = "companyId") Integer companyId,
                              @Param(value = "gridInfoId") Integer gridInfoId);

    public int insertGridConfBatch(@Param(value = "companyId") Integer companyId,
                                   @Param(value = "confGrids") List confGrids);
    
    public void saveConfGridAlias(@Param(value = "companyId")Integer companyId, @Param(value = "itemId")Integer itemId, @Param(value = "aliasName")String aliasName);
    
    public void deleteConfGridAlias(@Param(value = "companyId")Integer companyId, @Param(value = "itemId")Integer itemId);
    
    public void saveConfCostAlias(@Param(value = "companyId")Integer companyId, @Param(value = "itemId")Integer itemId, @Param(value = "aliasName")String aliasName);
    
    public void deleteConfCostAlias(@Param(value = "companyId")Integer companyId, @Param(value = "itemId")Integer itemId);
}
