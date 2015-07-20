package com.jessrun.common.solr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jessrun.common.pagination.Pagination;
import com.jessrun.platform.util.DateUtils;
import com.jessrun.platform.util.DateUtils.TimeUnit;

public abstract class GeneralSolrClientService<E extends Serializable> extends BasicSolrClientService implements SolrClientService<E> {

    private static final Logger log        = LoggerFactory.getLogger(GeneralSolrClientService.class);

    protected boolean           useGMTDate = true;
    private Class<E>            clazz;

    public GeneralSolrClientService(JztSolrServer solrServer, Class<E> clazz){
        super(solrServer);
        this.clazz = clazz;
    }

    /**
     * 是否使用格林威治时间<br/>
     * useGMTDate = true 时 表示 :Solr使用的是标准的格林威治（GMT）时间 这种（yyyy-MM-dd'T'HH:mm:ss.SSS'Z'）北京在东八区 默认时间会-8小时 所以为了满足他这个-8
     * 会在创建索引格式化日期类型的时候 把它时间+8<br/>
     * 查询时需要对日期类型-8<br/>
     * useGMTDate = false 时不做任何操作 默认=true
     */
    public boolean isUseGMTDate() {
        return useGMTDate;
    }

    public void setUseGMTDate(boolean useGMTDate) {
        this.useGMTDate = useGMTDate;
    }

    /**
     * 重置solr document中类型为date的field<br/>
     * 如果采用了格林威治时间需要在创建索引格式化日期类型的时候 把它时间+8
     * 
     * @param doc
     * @author xu.jianguo
     */
    private void reSetDate(SolrInputDocument doc) {
        if (!useGMTDate) return;
        for (Entry<String, SolrInputField> entry : doc.entrySet()) {

            SolrInputField field = entry.getValue();
            float boost = field.getBoost();

            for (Object value : field.getValues()) {
                List<Date> newValues = new ArrayList<Date>();
                if (value instanceof Date) {
                    Date date = (Date) value;
                    date = DateUtils.add(date, TimeUnit.HOURS, 8);
                    newValues.add(date);
                }
                if (!newValues.isEmpty()) {
                    field.setValue(newValues, boost);
                }
            }
        }
    }

    /**
     * 批量提交数据
     */
    public void commitList(Collection<E> objectList) {
        SolrServer server = getSolrServer();
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        for (E object : objectList) {
            SolrInputDocument doc = objectConvert2SolrDocument(object);
            reSetDate(doc);
            docs.add(doc);
        }
        try {
            server.add(docs);
            server.commit(false, false);
            log.info("commit object to solr document success.");
        } catch (Exception e) {
            log.error("commit object to solr document error.", e);
        }

    }

    /**
     * 提交单个数据
     * 
     * @param obj
     */
    public void commit(E obj) {
        Collection<E> objectList = new ArrayList<E>();
        objectList.add(obj);
        commitList(objectList);
    }

    /**
     * 删除所有数据
     */
    public void deleteAll() {
        try {
            SolrServer server = getSolrServer();
            server.deleteByQuery("*:*");
            server.commit();
            log.info("delete solr all document success");
        } catch (Exception e) {
            log.error("delete solr all document error.", e);
        }
    }

    /**
     * 根据ID删除数据
     */
    public void deleteByIdList(List<String> ids) {
        try {
            SolrServer server = getSolrServer();
            server.deleteById(ids);
            server.commit(false, false);
            log.info("delete solr doucment by id list success.");
        } catch (Exception e) {
            log.error("delete solr document by id list error.", e);
        }
    }

    /**
     * 搜索，并分页
     */
    public SolrSearchResult<E> searchEntity(SolrQuery query, Pagination pagination) {
        QueryResponse response = super.search(query, pagination);
        return parserQueryResponse(response);
    }

    protected SolrSearchResult<E> parserQueryResponse(QueryResponse response) {
        SolrDocumentList list = response.getResults();
        DocumentObjectBinder binder = new DocumentObjectBinder();
        List<E> lst = binder.getBeans(this.clazz, list);

        SolrSearchResult<E> result = new SolrSearchResult<E>();
        result.setQTime(response.getQTime());
        result.setStatus(response.getStatus());
        result.setRequestUrl(response.getRequestUrl());
        result.setNumFound(response.getResults().getNumFound());
        result.setList(lst);

        return result;
    }

    protected SolrInputDocument objectConvert2SolrDocument(E obj) {
        DocumentObjectBinder binder = new DocumentObjectBinder();
        SolrInputDocument doc1 = binder.toSolrInputDocument(obj);
        return doc1;
    }
}
