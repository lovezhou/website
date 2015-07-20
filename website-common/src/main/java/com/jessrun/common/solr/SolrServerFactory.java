package com.jessrun.common.solr;

import java.io.Serializable;

public interface SolrServerFactory {

    /**
     * 创建solr泛型操作客户端
     */
    <A extends Serializable> SolrClientService<A> getSolrClient(String branchId, Class<A> domainClass);

}
