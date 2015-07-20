package com.jessrun.platform.exception;

import java.sql.SQLException;

/**
 * DAO异常类
 * 
 * @author luoyifan 2012-11-28 下午8:07:00
 */
public class DaoException extends Exception {

    private static final long serialVersionUID = 1L;

    protected SQLException    sqlException;

    /**
     * Creates a new instance of <code>DAOException</code> without detail message.
     */
    public DaoException(){
    }

    /**
     * Constructs an instance of <code>DAOException</code> with the specified detail message.
     * 
     * @param msg The message string.
     */
    public DaoException(String msg){
        super(msg);
    }

    /*
     * Constructs an instance of <code>DAOException</code> using the specified SQLException
     * @param ex An SQLException
     */
    public DaoException(SQLException ex){
        super(ex);
        sqlException = ex;
    }

    /*
     * Constructs an instance of <code>DAOException</code> with both a user-defined message and an SQLException object.
     * For instances created by calling this constructor, getMessage() will return a message in the form <I>msg:
     * sqlExceptionMessage</I>
     * @param msg The message string @param ex An SQLException
     */
    public DaoException(String msg, SQLException ex){
        super(ex);

        StringBuffer xmsg = new StringBuffer(msg);
        xmsg.append(": ");
        xmsg.append(ex.getMessage());
        sqlException = ex;
    }

    public DaoException(Exception ex){
        super(ex);
    }

    /**
     * Returns the SQLException object, or null if there is no SQLException
     * 
     * @return SQLException
     */
    public SQLException getSQLException() {
        return sqlException;
    }

    /**
     * Returns a message describing the exception.
     */
    public String getMessage() {
        return super.getMessage();
    }

    /**
     * Overrides printStackTrace() to print stack trace from SQLException (when there is an embedded SQL exception)
     */
    public void printStackTrace() {
        if (sqlException != null) {
            sqlException.printStackTrace();
        }
    }
}
