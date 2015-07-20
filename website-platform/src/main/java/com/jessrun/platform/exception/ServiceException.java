package com.jessrun.platform.exception;

/**
 * Service层重载了异常的方法，增加自定义异常信息；
 * 
 * @author luoyifan 2012-11-28 下午8:06:35
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = -8165709103763425534L;

    private String            errCode;
    private String            errMsg;

    public ServiceException(){
        super();
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }

    public ServiceException(String errCode, String errMsg){
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

}
