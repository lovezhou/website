package com.jessrun.common.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CommonParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jessrun.common.pagination.Pagination;
import com.jessrun.platform.util.StringUtils;

public class BasicSolrClientService {

    private static final Logger log = LoggerFactory.getLogger(GeneralSolrClientService.class);
    private final JztSolrServer jztSolrServer;

    public BasicSolrClientService(JztSolrServer jztSolrServer){
        this.jztSolrServer = jztSolrServer;
    }

    protected SolrServer getSolrServer() {
        return jztSolrServer.getSolrServer();
    }

    protected String getBaseUrl() {
        return jztSolrServer.getBaseUrl();
    }

    protected void preSearch(SolrQuery query) {
        query.set(CommonParams.WT, "json");
        query.set(CommonParams.VERSION, "2");
    }

    protected void afterSearch(SolrQuery query, QueryResponse response) {

    }

    public QueryResponse search(SolrQuery query, Pagination pagination) {
        QueryResponse response = null;

        query.setRows(pagination.getPageSize());
        query.setStart((pagination.getCurrentPage() - 1) * pagination.getPageSize());

        try {
            preSearch(query);
            String requestUrl = this.jztSolrServer.getBaseUrl()
                                + (StringUtils.isNullOrEmpty(query.get(CommonParams.QT)) ? "/select" : query.get(CommonParams.QT))
                                + ClientUtils.toQueryString(query, false);
            if (log.isInfoEnabled()) {
                log.info(requestUrl);
            }
            response = getSolrServer().query(query);
            response.setRequestUrl(requestUrl);
            afterSearch(query, response);
        } catch (SolrServerException e) {
            log.error("search solr document error.", e);
            return null;
        }
        if (log.isInfoEnabled()) {
            log.info("results:" + response.getRequestUrl() + " num_found:" + response.getResults().getNumFound()
                     + " status:" + response.getStatus() + " qtime:" + response.getQTime());
        }
        SolrDocumentList solrDocumentList = response.getResults();
        int queryCount = (int) solrDocumentList.getNumFound();
        pagination.setCount(queryCount);
        return response;
    }

    public QueryResponse search(SolrQuery solrQuery) {
        Pagination pagination = new Pagination(Integer.MAX_VALUE, 1);
        return this.search(solrQuery, pagination);
    }
}
