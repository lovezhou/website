/**
 * @(#)RegisterControler.java
 *
 * Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.support.spring.vaildator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.common.support.spring.view.JsonView;
import com.jessrun.common.support.spring.view.XmlView;

/**
 * 验证失败后采用此controller进行统一处理,
 * 根据resultType请求参数返回XML或JSON格式
 * 如果resultType=xml返回XML，否则返回json
 * @author  luoyifan
 * @version 1.0,2011-3-22
 */
@Controller
public class ValidatorErrorJsonViewController {
	@RequestMapping("/validator_error.htm")
	public ModelAndView errorToJson(HttpServletRequest request) {
		String errorMsg = (String) request.getAttribute("javax.servlet.error.message");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", false);
		model.put("desc", errorMsg);
		
		String resultType = request.getParameter("resultType");
		if(resultType!=null&&resultType.trim().toUpperCase().equals("XML")) {
			return new ModelAndView(new XmlView(model));
		}
		
		return new ModelAndView(new JsonView(model));
	}
}