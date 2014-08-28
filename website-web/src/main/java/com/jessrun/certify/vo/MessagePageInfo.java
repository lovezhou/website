package com.jessrun.certify.vo;

public class MessagePageInfo {
	private String type;
	private String message;
	private String url;
	
	public MessagePageInfo(){
		
	}
	
	public MessagePageInfo(String type,String message,String url){
		this.type = type;
		this.message = message;
		this.url = url;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
