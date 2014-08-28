/**
 * @(#)ExecutorInterceptor.java Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.dao.mybatis.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.jessrun.common.pagination.Pagination;
import com.jessrun.platform.util.reflect.ReflectUtils;

/**
 * Executor.query()方法拦截器<br/>
 * 该拦截器是把map查询参数中代用分页的的参数整合到一个map中，方便后面的代码调用<br/>
 * 
 * @author luoyifan
 * @version 1.0,2012-3-12
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                                                                          RowBounds.class, ResultHandler.class }) })
public class ExecutorInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object paramObj = args[1];
        if (paramObj instanceof Map) {
            processMapParam(invocation, args, paramObj);
        } else if (paramObj instanceof Pagination) {
            processPaginationParam(invocation, args, paramObj);
        }
        return invocation.proceed();
    }

    @SuppressWarnings("unchecked")
    private void processMapParam(Invocation invocation, Object[] args, Object paramObj) {
        Map<String, Object> paramMap = (Map<String, Object>) paramObj;
        Map<String, Object> realParam = null;
        Pagination pagination = null;
        for (String key : paramMap.keySet()) {
            Object value = paramMap.get(key);
            if (value instanceof Map) {
                realParam = (Map<String, Object>) value;
            }
            if (value instanceof Pagination) {
                pagination = (Pagination) value;
            }
        }
        if (realParam == null) {
            realParam = paramMap;
        }
        if (pagination != null) {
            realParam.put("pagination", pagination);
        }
        args[1] = realParam;
        ReflectUtils.setValueByFieldName(invocation, "args", args);
    }

    private void processPaginationParam(Invocation invocation, Object[] args, Object paramObj) {
        Pagination pagination = (Pagination) paramObj;
        Map<String, Object> realParam = new HashMap<String, Object>();
        realParam.put("pagination", pagination);
        args[1] = realParam;
        ReflectUtils.setValueByFieldName(invocation, "args", args);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
