package com.jessrun.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jessrun.web.system.dao.SystemConfMapper;
import com.jessrun.web.system.domain.ConfCost;
import com.jessrun.web.system.domain.ConfGrid;
import com.jessrun.web.system.domain.ConfGridInfo;
import com.jessrun.web.system.service.SystemConfService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class SystemConfServiceImpl implements SystemConfService {

    @Autowired
    private SystemConfMapper systemConfMapper;

    /**
     * 获得财务款项
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ConfCost> getCostConfList(Integer companyId) {
        return systemConfMapper.getCostConfList(companyId);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ConfCost> getAllCostConfList() {
        return systemConfMapper.getAllCostConfList();
    }

    /**
     * 通过IDX获取列表项
     * 
     * @param idx
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ConfCost getConfCostItemByIdx(Integer idx) {
        return systemConfMapper.getConfCostItemByIdx(idx);
    }

    @Override
    @Transactional(value = "OracletransactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void setCostConf(int companyId, String flags, Integer userId, String userName) {
        systemConfMapper.setCostConf(companyId, flags, userId, userName);
    }

    public SystemConfMapper getSystemConfMapper() {
        return systemConfMapper;
    }

    public void setSystemConfMapper(SystemConfMapper systemConfMapper) {
        this.systemConfMapper = systemConfMapper;
    }

    @Override
    public List<ConfGrid> getGridConfList(Integer companyId, Integer gridInfoId) {
        return this.systemConfMapper.getGridConfList(companyId, gridInfoId);
    }

    @Override
    public List<ConfGrid> getAllGridConfList(Integer companyId, Integer gridInfoId) {
        return this.systemConfMapper.getAllGridConfList(companyId, gridInfoId);
    }

    @Override
    public List<ConfGridInfo> getAllGridInfoList() {
        return this.systemConfMapper.getAllGridInfoList();
    }

    @Override
    public void saveConfGridAlias(Integer companyId, Integer itemId, String aliasName) {
        this.systemConfMapper.deleteConfGridAlias(companyId, itemId);

        this.systemConfMapper.saveConfGridAlias(companyId, itemId, aliasName);
    }

    @Override
    public void rebackConfGridAlias(Integer companyId, Integer itemId) {
        this.systemConfMapper.deleteConfGridAlias(companyId, itemId);
    }

    @Override
    public void saveConfCostAlias(Integer companyId, Integer itemId, String aliasName) {
        this.systemConfMapper.deleteConfCostAlias(companyId, itemId);
        this.systemConfMapper.saveConfCostAlias(companyId, itemId, aliasName);
    }

    @Override
    public void rebackConfCostAlias(Integer companyId, Integer itemId) {
        this.systemConfMapper.deleteConfCostAlias(companyId, itemId);
    }

    @Override
    public void updateGridConf(int companyId, int gridInfoId, List<ConfGrid> confGrids) {

    }

}
