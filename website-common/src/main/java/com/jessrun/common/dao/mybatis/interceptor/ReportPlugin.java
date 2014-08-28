package com.jessrun.common.dao.mybatis.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jessrun.common.report.Report;
import com.jessrun.common.report.ReportColHead;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class ReportPlugin implements Interceptor  {
	
	private static final Logger log         = LoggerFactory.getLogger(ReportPlugin.class);
    private String              pageSqlId   = "";                                       // mapper.xml中需要拦截的ID(正则匹配)

	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation ivk) throws Throwable {
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler,
                                                                                                     "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate,
                                                                                                  "mappedStatement");

            if (mappedStatement.getId().matches(pageSqlId)) {
            	String[] temps = mappedStatement.getId().replaceAll("ByReport", "").replaceAll("call", "").split("\\.");
            	String reportId = temps[temps.length - 1];
            	//System.out.println("reportId=" + reportId + "mappedStatement.getId()=" + mappedStatement.getId());
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();
                if (parameterObject == null) {
                    throw new NullPointerException("parameterObject尚未实例化！");
                } else {
                	Report report = new Report();
                	
                    Connection connection = (Connection) ivk.getArgs()[0];
                    
                    String sql = "select t.report_name from tb_conf_report t where t.report_id = '" + reportId + "'";
                    PreparedStatement countStmt = connection.prepareStatement(sql);
                    ResultSet rs = countStmt.executeQuery();
                    if (rs.next()) {
                    	report.setReportName(rs.getString(1));
                    }
                    rs.close();
                    rs = null;
                    countStmt.close();
                    countStmt = null;
                    
                    if(report.getReportName() == null){
                    	throw new NullPointerException("report not found");
                    }
                    
                    sql = "select t.item_id, t.company_id, t.item_name,t.item_code, t.parent_id, t.sort" +
						 " from (select b.item_id, c.company_id, b.item_name,b.item_code, b.parent_id, c.sort " +
						 "         from tb_conf_report_item b, tb_conf_report_company c " +
						 "        where b.item_id = c.item_id " +
						 "          and c.company_id = 172 " +
						 "          and c.report_id = '" + reportId + "' " +
						 "        order by c.sort asc) t " +
						 " start with t.parent_id is null " +
						 " connect by prior t.item_id = t.parent_id " +
						 "order by t.sort asc";
                    
                    countStmt = connection.prepareStatement(sql);
                    rs = countStmt.executeQuery();
                    Map<Integer,ReportColHead> map = null;
                    ReportColHead head = null;
                    while(rs.next()) {
                    	if(map == null){
                    		map = new HashMap<Integer,ReportColHead>();
                    	}
                    	head = new ReportColHead();
                    	
                    	head.setItemCode(rs.getString(4));
                    	head.setItemId(rs.getInt(1));
                    	head.setItemName(rs.getString(3));
                    	
                    	
                    	if(rs.getString(5) != null){
                    		if(map.get(rs.getInt(5)) != null){
                    			map.get(rs.getInt(5)).addNextReportColHead(head);
                    		}
                    	}
                    	
                    	map.put(head.getItemId(), head);
                    	report.addHead(head);
                    }
                    
                    rs.close();
                    rs = null;
                    countStmt.close();
                    countStmt = null;
                    
                    report.createIndex();
                    
                    if (!(parameterObject instanceof Map)) {
                    	throw new Exception("param type error");
                    }
                    
                    Map<String,Object> param = (Map<String, Object>) parameterObject;

                    param.put("report", report); 
                    
                }
            }
        }
		
		return ivk.proceed();
		
	}
	
	

	@Override
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}
	
	public static boolean isEmpty(String s) {
        return s == null || "".equals(s) || "null".equals(s);
    }

	@Override
	public void setProperties(Properties p) {
		pageSqlId = p.getProperty("pageSqlId");
        if (isEmpty(pageSqlId)) {
            try {
                throw new PropertyException("pageSqlId property is not found!");
            } catch (PropertyException e) {
                log.error("error", e);
            }
        }
	}

	public String getPageSqlId() {
		return pageSqlId;
	}

	public void setPageSqlId(String pageSqlId) {
		this.pageSqlId = pageSqlId;
	}

	
}
