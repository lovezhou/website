package com.jessrun.platform.exception;

/**
 * 重载了异常的方法，增加自定义异常信息；
 * 
 * @author luoyifan 2012-11-28 下午8:07:10
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = -8165709103763425534L;

    private String            errCode;
    private String            errMsg;

    public BaseException(){
        super();
    }

    public BaseException(String message, Throwable cause){
        super(message, cause);
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(Throwable cause){
        super(cause);
    }

    public BaseException(String errCode, String errMsg){
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
