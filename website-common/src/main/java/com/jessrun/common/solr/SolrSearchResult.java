package com.jessrun.common.solr;

import java.io.Serializable;
import java.util.List;

public class SolrSearchResult<E extends Serializable> {

    private List<E> list;
    private int     QTime;
    private int     status;
    private String  requestUrl;
    private long    numFound;

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getQTime() {
        return QTime;
    }

    public void setQTime(int qTime) {
        QTime = qTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getNumFound() {
        return numFound;
    }

    public void setNumFound(long numFound) {
        this.numFound = numFound;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

}
