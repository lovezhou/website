package com.jessrun.common.web;

import java.io.Serializable;



/**
 * 
 * 返回页面的消息message
 * @author zmy
 * @version 1.0, 2015-7-25
 * @since 1.0, 2015-7-25
 */
public class Message implements Serializable {

	private static Message message;
    private static final long serialVersionUID = 1L;
    public String errorMsg;//
    public String title;
	
	public boolean success=true ;//

    public Message(){
        
    }
	
    public Message(String title,String errorMsg){
        this.title= title;
        this.errorMsg=errorMsg;
        success=false;
    }

    public static Message newInstance(String title,String errorMsg){
        return new Message(title,errorMsg);
    }
    
    public static Message newInstance(){
        return new Message();
    }
    
    public String getErrorMsg() {
        return errorMsg;
    }

    
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    
    public boolean isSuccess() {
        return success;
    }

    
    public void setSuccess(boolean success) {
        this.success = success;
    }

    
    public String getTitle() {
        return title;
    }

    
    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
