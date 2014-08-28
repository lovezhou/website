package com.jessrun.common.solr;

import java.io.Serializable;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.jessrun.common.pagination.Pagination;

public interface SolrClientService<E extends Serializable> {

    SolrSearchResult<E> searchEntity(SolrQuery query, Pagination pagination);

    QueryResponse search(SolrQuery query, Pagination pagination);

    QueryResponse search(SolrQuery solrQuery);
}
