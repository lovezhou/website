package com.jessrun.certify.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jessrun.certify.constant.CookieNames;
import com.jessrun.certify.constant.ParamKeys;
import com.jessrun.certify.service.NewCertifyService;
import com.jessrun.common.web.NewCookieUtils;
import com.jessrun.common.web.WebApplicationContextUtils;

public class URLRewriteFilter implements Filter {
	private boolean isSelfURLRewrite = false;
	
	private ServletContext ctx;
	
	private NewCertifyService certifyService;


	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		if(!isSelfURLRewrite){
			NewCookieUtils.deleteCookie((HttpServletResponse)arg1, CookieNames.COMPANYSIM_COOKIE_NAME);
			arg2.doFilter(arg0, arg1);
		}
		
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String companySIM = request.getParameter(ParamKeys.COMPANYSIM_KEY);
		if(companySIM != null){
			 if(certifyService.hasCompanySim(companySIM)){
				 
			 }
		}
		
		
		
		
		request.setAttribute(ParamKeys.SELF_REWRITE_MODE, "true");
		String url = request.getRequestURI();
		
		//System.out.println("arg0.getRequestURI():" + url);
		
		String[] urlKeys = url.split("/");
		
		//String cPath =  ctx.getContextPath();
		
		//System.out.println("cPath=" + cPath);
		//System.out.println("13=" + ctx.);

		String forwardURL = null;
		
		boolean hasCompany = false;
		companySIM = request.getParameter(ParamKeys.COMPANYSIM_KEY);
		if(urlKeys.length == 4){
			//if(("/" + urlKeys[1]).equals(cPath) && (urlKeys[3].indexOf("index.jsp") == 0 || urlKeys[3].indexOf("default.jsp") == 0)){
			if(urlKeys[3].indexOf("index.jsp") == 0 || urlKeys[3].indexOf("default.jsp") == 0){
				if(companySIM == null){
					companySIM = urlKeys[2];
				}
				forwardURL = "/" + urlKeys[3];
				hasCompany = true;
			}
		}else if(urlKeys.length == 3){
			//if(!urlKeys[1].equals(cPath) && (urlKeys[2].indexOf("index.jsp") == 0 || urlKeys[2].indexOf("default.jsp") == 0)){
			if(urlKeys[2].indexOf("index.jsp") == 0 || urlKeys[2].indexOf("default.jsp") == 0){
				if(companySIM == null){
					companySIM = urlKeys[1];
				}
				forwardURL = "/" + urlKeys[2];
				hasCompany = true;
			}
		}else{
			hasCompany = companySIM != null;
		}
		
		if(hasCompany){
			hasCompany = certifyService.hasCompanySim(companySIM);
		}
//		for(String s : urlKeys){
//			System.out.println("s:" + s);
//		}
		
		//System.out.println("urlKeys[urlKeys.length]:" + urlKeys[urlKeys.length - 1]);
		//System.out.println("(urlKeys[urlKeys.length-1].indexOf(index.jsp)=" + (urlKeys[urlKeys.length-1].indexOf("index.jsp")));
		//System.out.println("(urlKeys[urlKeys.length-1].indexOf(default.jsp)=" + (urlKeys[urlKeys.length-1].indexOf("default.jsp")));
		if(hasCompany){
			NewCookieUtils.writeCookie(request, response, NewCookieUtils.createCookie(CookieNames.COMPANYSIM_COOKIE_NAME, companySIM));
			request.getRequestDispatcher(forwardURL).forward(arg0, arg1);
			return;
		}
		
		
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		ctx = arg0.getServletContext();
		
		String temp = arg0.getInitParameter("isSelfURLRewrite").toLowerCase();
		if(temp == null){
			return;
		}
		try{
			isSelfURLRewrite = Boolean.valueOf(temp);
		}catch(Exception e){
			
		}
		
		if(isSelfURLRewrite){
			certifyService = (NewCertifyService)WebApplicationContextUtils.getService(NewCertifyService.class, ctx);
		}
	}

	@Override
	public void destroy() {

	}
}
