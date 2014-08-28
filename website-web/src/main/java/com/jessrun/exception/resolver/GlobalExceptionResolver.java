package com.jessrun.exception.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.common.support.spring.view.JsonView;
import com.jessrun.exception.JessrunException;
import com.jessrun.exception.RequestType;
import com.jessrun.exception.ResultProcessType;

/**
 * 全局异常捕获
 * 
 * @author Awen
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	private static final Log    LOG = LogFactory.getLog(GlobalExceptionResolver.class);
	/**
	 * 错误页
	 */
	private String errorPage = DEFAULT_ERROR_PAGE;
	
	/**
	 * 捕获异常
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3) {
		LOG.error(arg3);
		
		
		StackTraceElement[] trace = arg3.getStackTrace();
		for (int i=0; i < trace.length; i++)
			LOG.error("\tat " + trace[i]);
		
		
		JessrunException je = null;
		
		if(arg3 instanceof JessrunException){
			je = (JessrunException)arg3;
		}else{
			je = new JessrunException(arg3);
			je.setSuccess(false);
			
			try{
				
				RequestType requestType = (RequestType)arg0.getAttribute(RequestType.REQUEST_TYPE_KEY);
				
				if(requestType == null){
					requestType = RequestType.FORM;
				}
				
				je.setRequestType(requestType);
				
				//判断请求的是页面还是JSON
				switch(requestType){
					case FORM://请求是FORM提交
						//将异常放入MODEL
						
						ResultProcessType resultProcessType = (ResultProcessType)arg0.getAttribute(ResultProcessType.RESULT_PROCESS_TYPE_KEY);
						
						if(resultProcessType == null){
							resultProcessType = ResultProcessType.HISTORY_FOR_WAIT;
						}
						
						je.setResultProcessType(resultProcessType);
						
						Object waitTimeObj = arg0.getAttribute(ResultProcessType.RESULT_WAIT_TIME_KEY);
						
						if(waitTimeObj == null){
							//设置错误页等待跳转的时间
							waitTimeObj = DEFAULT_WAIT_TIME;
						}

						//设置错误页等待跳转的时间
						je.setWaitTime((Long)waitTimeObj);
						
						//设置错误页跳转的路径
						je.setGotoURL(arg0.getAttribute(ResultProcessType.RESULT_GOTO_URL_KEY).toString());
				}
			}catch(Throwable t){
			}
		}
		//arg3.printStackTrace();
		if(je.getRequestType() == RequestType.FORM){
			ModelAndView mav = null;
			switch(je.getResultProcessType()){
				case FORWARD:
					mav = new ModelAndView(je.getGotoURL());
					mav.addObject("je", je);
					break;
				case REDIRECT:
					mav = new ModelAndView("redirect:" + je.getGotoURL());
					break;
				default:
					mav = new ModelAndView(errorPage);
					mav.addObject("je", je);
			}
			return mav;
		}else{
			return new ModelAndView(new JsonView(false, je.getDesc()));
		}
		
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
	/**
	 * 默认错误页面等待跳转时间
	 */
	private static final long DEFAULT_WAIT_TIME = 3000l;

	/**
	 * 默认跳转错误页面
	 */
	private static final String DEFAULT_ERROR_PAGE = "/error_page.jsp";
}
