/**
 * @(#)FreeMarkerPaginationConvertor.java
 *
 * Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.pagination;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * description
 * @author  luoyifan
 * @version 1.0,2011-3-11
 */
public class FreeMarkerPaginationConvertor implements PaginationConvertor{
	//private static final String PAGINATION_TEMPLATE="pagination.ftl";
	 private static final String PAGINATION_TEMPLATE="pagination_one.ftl";
	private static final String TEMPLATE_ENCODING="UTF-8";
	private static final Log LOG = LogFactory.getLog(FreeMarkerPaginationConvertor.class);
	
	private static Template template;
	static {
		//template = getTemplate();
	}
	
	private static Template getTemplate() {
		/*URL url = FreeMarkerPaginationConvertor.class.getResource("");
		File dir = new File(url.getPath());*/
		Configuration freemarkerConfiguration = new Configuration();
		freemarkerConfiguration.setDefaultEncoding(TEMPLATE_ENCODING);
		try {
			//freemarkerConfiguration.setDirectoryForTemplateLoading(new File(""));
			freemarkerConfiguration.setClassForTemplateLoading(FreeMarkerPaginationConvertor.class, "");
			 System.out.println("---------------------------" + freemarkerConfiguration.getTemplate("PAGINATION_TEMPLATE"));
			 Template temp = freemarkerConfiguration.getTemplate(PAGINATION_TEMPLATE);
			 return temp;
		} catch (IOException e) {
			throw new RuntimeException("get pagination template error.",e);
		}
	}
	

	public String convert(Pagination pagination) {
		 
		 Map<String,Object> model = new HashMap<String, Object>();
		 model.put("pagination", pagination);
		 try {
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}
	}
}
