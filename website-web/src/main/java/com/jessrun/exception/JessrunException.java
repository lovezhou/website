package com.jessrun.exception;

@SuppressWarnings("serial")
public class JessrunException extends Exception {
	private boolean success = false;
	
	private String desc;
	
	private String gotoURL;
	
	private long waitTime = 0;
	
	private RequestType requestType = RequestType.FORM;
	
	private ResultProcessType resultProcessType = ResultProcessType.HISTORY_FOR_WAIT;
	
	public JessrunException(){
		this("系统繁忙，请联系管理员");
	}
	
	public JessrunException(String message){
		super(message);
		this.desc = message;
	}
	
	public JessrunException(Throwable t){
		this("系统繁忙，请联系管理员",t);
	}
	
	public JessrunException(String message,Throwable t){
		super(message,t);
		this.desc = message;
	}

	public String getGotoURL() {
		return gotoURL;
	}

	public void setGotoURL(String gotoURL) {
		this.gotoURL = gotoURL;
	}

	public long getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public ResultProcessType getResultProcessType() {
		return resultProcessType;
	}

	public void setResultProcessType(ResultProcessType resultProcessType) {
		this.resultProcessType = resultProcessType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isSuccess() {
		return success;
	}
	
	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
