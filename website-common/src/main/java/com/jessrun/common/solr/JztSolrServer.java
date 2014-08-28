package com.jessrun.common.solr;

import org.apache.solr.client.solrj.SolrServer;

public class JztSolrServer {

    private SolrServer solrServer;
    private String     baseUrl;

    public JztSolrServer(SolrServer solrServer, String baseUrlString){
        this.solrServer = solrServer;
        this.baseUrl = baseUrlString;

        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        if (baseUrl.indexOf('?') >= 0) {
            throw new RuntimeException("Invalid base url for solrj.  The base URL must not contain parameters: "
                                       + baseUrl);
        }
    }

    public SolrServer getSolrServer() {
        return solrServer;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

}
