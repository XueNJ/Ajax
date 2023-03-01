package com.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.model.Member;

public class MemberDao {

	private static MemberDao bankDao = new MemberDao();
	
	private static String strSQL = "";

	private MemberDao(){ }

	public static MemberDao getInstance(){
		return bankDao;
	}
	
	public List<Member> queryMember(Member voMember){
		Member objMember = null;
		List<Member> listMembers = new ArrayList<>();
		// querySQL SQL
		strSQL = "SELECT IDENTIFICATION_NO ID, CUSTOMER_NAME NAME, PASSWORD PWD FROM BEVERAGE_MEMBER WHERE IDENTIFICATION_NO = ?";		
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
		    // Step2:Create prepareStatement For SQL
			PreparedStatement stmt = conn.prepareStatement(strSQL)){
			stmt.setString(1, voMember.getId());
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next() && rs != null){
					objMember = new Member();
					objMember.setId(rs.getString("ID"));
					objMember.setName(rs.getString("NAME"));
					objMember.setPwd(rs.getString("PWD"));
					listMembers.add(objMember);
				} else {
					listMembers = null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listMembers;
	}
	
	public boolean createMember(Member voMember) {
		boolean createSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();){
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Insert SQL
			strSQL = "INSERT INTO BEVERAGE_MEMBER (IDENTIFICATION_NO, CUSTOMER_NAME, PASSWORD) VALUES (?, ?, ?)";
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(strSQL)){
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入
				stmt.setString(1, voMember.getId());
				stmt.setString(2, voMember.getName());
				stmt.setString(3, voMember.getPwd());
				// Step4:Execute SQL
				int recordCount = stmt.executeUpdate();
				createSuccess = (recordCount > 0) ? true : false;
				// Step5:Transaction commit(交易提交)
				conn.commit();
			} catch (SQLException e) {
				// 若發生錯誤則資料 rollback(回滾)
				conn.rollback();
				throw e;
			}			
		} catch (SQLException e) {
			createSuccess = false;
			e.printStackTrace();
		}
		
		return createSuccess;
	}
	
}
