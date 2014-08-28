package com.jessrun.common.dao.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


public class ResultSetTypeHandler extends BaseTypeHandler<List<Map<String, Object>>> {

	@Override
	public List<Map<String, Object>> getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		return null;
	}

	public List<Map<String, Object>> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		ResultSet rs = (ResultSet) cs.getObject(columnIndex);

		//if (!rs.isClosed()) {

			ResultSetMetaData data = rs.getMetaData();
			int columnCnt = data.getColumnCount();

			while (rs.next()) {
				Map<String, Object> rowMap = new HashMap<String, Object>();
				for (int i = 1; i <= columnCnt; i++) {
					String colName = data.getColumnName(i).toLowerCase();
					Object colValue = rs.getObject(colName);
					// TODO 类型处理
					rowMap.put(colName.toUpperCase(), colValue == null ? "" : colValue.toString());
				}
				result.add(rowMap);
			}
		//}
			rs.close();
			
			rs = null;

		return result;
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, List<Map<String, Object>> arg2, JdbcType arg3) throws SQLException {
		System.out.println();
	}

	@Override
	public List<Map<String, Object>> getNullableResult(ResultSet arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
