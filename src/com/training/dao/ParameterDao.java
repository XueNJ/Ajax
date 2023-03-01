package com.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.model.Parameter;
import com.training.vo.voParameter;

public class ParameterDao {

	private static ParameterDao parameterDao = new ParameterDao();
	
	private static String strSQL = "";

	private ParameterDao(){ }

	public static ParameterDao getInstance(){
		return parameterDao;
	}
	
	public List<Parameter> queryParameter(voParameter voParameter){
		Parameter objParameter = null;
		List<Parameter> listParameter = new ArrayList<>();
		// querySQL SQL
		strSQL = "SELECT NAME SELECTED, VALUE VALUED FROM PARAMETER WHERE  TYPE IS NOT NULL AND TYPE = (SELECT CATEGORY FROM PARAMETER WHERE ID = ?)";
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
		    // Step2:Create prepareStatement For SQL
			PreparedStatement stmt = conn.prepareStatement(strSQL)){
			stmt.setString(1, voParameter.getID());
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()){
					objParameter = new Parameter();
					objParameter.setSelected(rs.getString("SELECTED"));
					objParameter.setValued(rs.getString("VALUED"));
					listParameter.add(objParameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listParameter;
	}
}
