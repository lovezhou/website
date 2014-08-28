package com.jessrun.common.dao.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class Boolean2NumTypeHandler implements TypeHandler<Boolean> {

	@Override
	public Boolean getResult(ResultSet arg0, String arg1) throws SQLException {
		int result = arg0.getInt(arg1); 
		return result == 0 ? false : true ; 
	}

	@Override
	public Boolean getResult(ResultSet arg0, int arg1) throws SQLException {
		int result = arg0.getInt(arg1); 
        return result == 0 ? false : true ;  
	}

	@Override
	public Boolean getResult(CallableStatement arg0, int arg1) throws SQLException {
		int result = arg0.getInt(arg1);  
        return result == 0 ? false : true ;  
	}

	@Override
	public void setParameter(PreparedStatement arg0, int arg1, Boolean arg2,
			JdbcType arg3) throws SQLException {
		Boolean b = (Boolean) arg2;
		int result = 0;
        if(b != null ){
        	result = b ? 1 : 0;
        }
        arg0.setInt(arg1, result);  
	}

}
