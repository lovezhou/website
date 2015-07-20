package com.jessrun.platform.exception;

/**
 * 远程调用异常
 * 
 * @author luoyifan 2012-11-28 下午8:06:47
 */
public class RemoteServiceException extends RuntimeException {

    private static final long serialVersionUID = -6723883295447646974L;

    private String            errCode;

    private String            errMsg;

    public RemoteServiceException(){
        super();
    }

    public RemoteServiceException(String message, Throwable cause){
        super(message, cause);
    }

    public RemoteServiceException(String message){
        super(message);
    }

    public RemoteServiceException(Throwable cause){
        super(cause);
    }

    public RemoteServiceException(String errCode, String errMsg){
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
