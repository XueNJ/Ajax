package com.training.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import com.training.model.Good;
import com.training.model.Goods;
import com.training.vo.voMemberSearch;
import com.training.vo.voShoppingCarGoods;

public class FrontEndDao {

	private static FrontEndDao frontendDao = new FrontEndDao();

	private static String strSQL = ""; // 條件
	private static String strSQL1 = ""; // 列表資料
	private static String strSQL2 = ""; // 列表總筆數

	private FrontEndDao() {
	}

	public static FrontEndDao getInstance() {
		return frontendDao;
	}

	/**
	 * 前臺顧客瀏灠商品
	 * 
	 * @param searchKeyword
	 * @param startRowNo
	 * @param endRowNo
	 * @return Set(Goods)
	 */
	public Goods searchGoods(voMemberSearch voMemberSearch, String strMainImagePath) {
		int intMainPage = 0; // 指定頁數
		int intShowPage = 0; // 每頁筆數
		intMainPage = Integer
				.parseInt(voMemberSearch.getStrMainPage().equals("") ? "1" : voMemberSearch.getStrMainPage());
		intShowPage = Integer
				.parseInt(voMemberSearch.getStrShowPage().equals("") ? "10" : voMemberSearch.getStrShowPage());
		int intEndSeq = intShowPage * intMainPage;
		int intStartSeq = intEndSeq - intShowPage + 1;
		String strEq = intStartSeq + " AND " + intEndSeq; // 區間字串
		strSQL = "";
		strSQL += voMemberSearch.getSearchKeyword().equals("") ? ""
				: " AND UPPER(GOODS_NAME) LIKE UPPER('%" + voMemberSearch.getSearchKeyword() + "%') ";
		strSQL = " SELECT ROWNUM ROW_ID, GOODS_ID, GOODS_NAME, PRICE, QUANTITY, IMAGE_NAME, STATUS FROM BEVERAGE_GOODS WHERE GOODS_ID IS NOT NULL AND STATUS='1'"
				+ strSQL;
		strSQL1 = " SELECT A.ROW_ID, A.GOODS_ID, A.GOODS_NAME, A.PRICE, A.QUANTITY, A.IMAGE_NAME, A.STATUS FROM ( "
				+ strSQL + ") A " + " WHERE A.ROW_ID BETWEEN " + strEq + " ";
		strSQL2 = " SELECT Count(*) TOTALPAGE FROM ( " + strSQL + ") A ";
		// Step1:取得Connection
		Goods listgd = new Goods();
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
				// Step2:Create PreparedStatement For SQL
				PreparedStatement stmt = conn.prepareStatement(strSQL1);
				ResultSet rs = stmt.executeQuery()) {
			// Step3:Process Result
			if (rs.getFetchSize() > 0) {
				Set<Good> goods = new LinkedHashSet<Good>();
				while (rs.next()) {
					Good good = new Good();
					good.setGoodsID(rs.getBigDecimal("GOODS_ID")); // 商品編號
					good.setGoodsName(rs.getString("GOODS_NAME")); // 商品名稱
					good.setGoodsPrice(rs.getInt("PRICE")); // 商品價格
					good.setGoodsQuantity(rs.getInt("QUANTITY")); // 商品庫存量
					good.setGoodsImageName(rs.getString("IMAGE_NAME")); // 商品圖片名稱
					good.setStatus(rs.getString("STATUS")); // 商品狀態(1:上架、0:下架)
					good.setStrImagePath((rs.getString("IMAGE_NAME") != ""
							? (strMainImagePath + "/" + rs.getString("IMAGE_NAME"))
							: (strMainImagePath + "/" + "nothing.jpg")));
					goods.add(good);
					listgd.setListGoods(goods);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 列表總筆數
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
				// Step2:Create prepareStatement For SQL
				PreparedStatement stmt = conn.prepareStatement(strSQL2);
				ResultSet rs = stmt.executeQuery()) {
			if (rs.getFetchSize() > 0) {
				while (rs.next()) {
					// set object value
					listgd.setTotalCount(rs.getInt("TOTALPAGE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listgd;
	}

	/**
	 * 查詢顧客所購買商品資料(價格、庫存)
	 * 
	 * @param goodsIDs
	 * @return Map(BigDecimal, Goods)
	 */
	public Map<BigDecimal, Good> queryBuyGoods(Set<BigDecimal> goodsIDs) {
		// key:商品編號、value:商品
		Map<BigDecimal, Good> goods = new LinkedHashMap<>();
		strSQL = ""; // 參數字串
		String strComma = ", "; // 逗號
		// 只查 購買 數量 > 0
		for (int i = 0; i < goodsIDs.size(); i++) {
			strSQL = strSQL + "?" + strComma;
		}
		if (strSQL.length() > 0) {
			strSQL = "SELECT GOODS_ID, GOODS_NAME, PRICE, QUANTITY, IMAGE_NAME, STATUS FROM BEVERAGE_GOODS WHERE TO_CHAR(GOODS_ID) IN ("
					+ strSQL.replaceAll(", $", "") + ")";
			// Step1:取得Connection
			try (Connection conn = DBConnectionFactory.getOracleDBConnection();
					// Step2:Create PreparedStatement For SQL
					PreparedStatement stmt = conn.prepareStatement(strSQL)) {
				// 設置查詢的欄位值
				int count = 1; // 欄位順序
				for (BigDecimal value : goodsIDs) {
					stmt.setBigDecimal(count, value); // 商品編號
					count++;
				}
				try (ResultSet rs = stmt.executeQuery()) {
					// Step3:Process Result
					while (rs.next()) {
						Good gd = new Good();
						gd.setGoodsID(rs.getBigDecimal("GOODS_ID")); // 商品編號
						gd.setGoodsName(rs.getString("GOODS_NAME")); // 商品名稱
						gd.setGoodsPrice(rs.getInt("PRICE")); // 商品價格
						gd.setGoodsQuantity(rs.getInt("QUANTITY")); // 商品庫存量
						gd.setGoodsImageName(rs.getString("IMAGE_NAME")); // 商品圖片名稱
						gd.setStatus(rs.getString("STATUS")); // 商品狀態(1:上架、0:下架)
						goods.put(rs.getBigDecimal("GOODS_ID"), gd);
					}
				} catch (SQLException e) {
					throw e;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return goods;
	}

	/**
	 * 建立訂單資料
	 * 
	 * @param customerID
	 * @param goodsOrders 【訂單資料(key:購買商品、value:購買數量)】
	 * @return boolean
	 */
	public boolean batchCreateGoodsOrder(String customerID, Set<voShoppingCarGoods> voShoppingCarGoods) {
		boolean insertSuccess = false;
		// ------------------ Create Order Homework START ------------------
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection()) {
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Update SQL
			strSQL = "";
			// Step2:Create prepareStatement For SQL
			try (Statement pstmt = conn.createStatement()) {
				for (voShoppingCarGoods obj : voShoppingCarGoods) {
					strSQL = "INSERT INTO BEVERAGE_ORDER (ORDER_ID, ORDER_DATE, CUSTOMER_ID, GOODS_ID, GOODS_BUY_PRICE, BUY_QUANTITY) VALUES ("
							+ "BEVERAGE_ORDER_SEQ.NEXTVAL, " + "SYSTIMESTAMP , " + "'" + customerID + "', "
							+ obj.getGoodsID() + ", " + obj.getGoodsPrice() + ", " + obj.getBuyQuantity() + ")";

					pstmt.addBatch(strSQL);
				}
				// Step4:Execute SQL
				int[] insertCounts = pstmt.executeBatch();
				// Step5:交易提交
				if (insertSuccess = IntStream.of(insertCounts).allMatch(x -> x == 1))
					conn.commit(); // 判斷 DB 結果 Commit
				else
					conn.rollback();
			} catch (SQLException e) {
				// 發生 Exception 交易資料 roll back
				conn.rollback();
				throw e;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ------------------ Create Goods Homework END ------------------
		return insertSuccess;
	}

	/**
	 * 交易完成更新扣商品庫存數量
	 * 
	 * @param goods
	 * @return boolean
	 */
	public boolean batchUpdateGoodsQuantity(Set<voShoppingCarGoods> voShoppingCarGoods) {
		boolean updateSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection()) {
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// SQL Parameter
			int intGoodsQuantity = 0; // 商品庫存量
			BigDecimal bdGoodsID = null; // 商品編號
			// Step2:Create prepareStatement For SQL
			try (Statement pstmt = conn.createStatement()) {
				for (voShoppingCarGoods obj : voShoppingCarGoods) {
					strSQL = "";
					// Step3:將"資料欄位編號"、"資料值"作為引數傳入
					intGoodsQuantity = obj.getBuyQuantity();// 商品購買量
					bdGoodsID = obj.getGoodsID(); // 商品編號
					// Update SQL
					strSQL = "UPDATE BEVERAGE_GOODS" + " ";
					strSQL += "SET QUANTITY = QUANTITY - " + intGoodsQuantity + " ";
					strSQL += "WHERE GOODS_ID = " + bdGoodsID;
					pstmt.addBatch(strSQL);
				}
				// Step4:Execute SQL
				int[] updateCounts = pstmt.executeBatch();
				// Step5:交易提交
				if (updateSuccess = IntStream.of(updateCounts).allMatch(x -> x == 1))
					conn.commit(); // 判斷 DB 結果 Commit
				else
					conn.rollback();
			} catch (SQLException e) {
				// 發生 Exception 交易資料 roll back
				conn.rollback();
				throw e;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateSuccess;
	}

}
