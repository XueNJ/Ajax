package com.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.training.model.Good;
import com.training.model.Goods;
import com.training.model.GoodsAdd;
import com.training.model.GoodsUpdate;
import com.training.model.Order;
import com.training.model.Orders;
import com.training.model.ResultMessage;
import com.training.vo.voGoods;
import com.training.vo.voGoodsAdd;
import com.training.vo.voGoodsUpdate;
import com.training.vo.voGoodsOrder;

public class BackEndDao {

	private static BackEndDao backendDao = new BackEndDao();

	private static String strSQL = ""; // 條件
	private static String strSQL1 = ""; // 列表資料
	private static String strSQL2 = ""; // 列表總筆數

	private BackEndDao() {
	}

	public static BackEndDao getInstance() {
		return backendDao;
	}

	/**
	 * @methodName 商品列表查詢
	 * 
	 * @input voGoodQuery voGQ
	 * 
	 * @return GoodsLIst
	 */
	public Goods queryGoods(voGoods voGDs) {
		strSQL = "";
		strSQL1 = "";
		strSQL2 = "";
		int intMainPage = 0; // 指定頁數
		int intShowPage = 0; // 每頁筆數
		if (voGDs.getStrAction().equals("List")) {
			intMainPage = Integer.parseInt(voGDs.getStrMainPage().equals("") ? "1" : voGDs.getStrMainPage());
			intShowPage = Integer.parseInt(voGDs.getStrShowPage().equals("") ? "10" : voGDs.getStrShowPage());
		} else if (voGDs.getStrAction().equals("Detail")) {
			intMainPage = 1;
			intShowPage = 1;
		} else { // 防呆
			intMainPage = 1;
			intShowPage = 10;
		}
		int intEndSeq = intShowPage * intMainPage;
		int intStartSeq = intEndSeq - intShowPage + 1;
		// querySQL
		strSQL = " ";
		strSQL += !voGDs.getTxtSearchGoodsID().equals("") ? " AND GOODS_ID = " + voGDs.getTxtSearchGoodsID() + " " : "";
		strSQL += !(voGDs.getTxtSearchGoodsName().equals(""))
				? " AND UPPER(GOODS_NAME) LIKE UPPER('%" + voGDs.getTxtSearchGoodsName() + "%') "
				: "";
		if (!(voGDs.getTxtSearchPriceDown().equals("")) && !(voGDs.getTxtSearchPriceUp().equals(""))) {
			strSQL += " AND PRICE BETWEEN " + voGDs.getTxtSearchPriceDown() + " AND " + voGDs.getTxtSearchPriceUp() + "";
		} else if ((voGDs.getTxtSearchPriceDown().equals("")) && !(voGDs.getTxtSearchPriceUp().equals(""))) {
			strSQL += " AND PRICE < " + voGDs.getTxtSearchPriceUp() + " ";
		} else if (!(voGDs.getTxtSearchPriceDown().equals("")) && (voGDs.getTxtSearchPriceUp().equals(""))) {
			strSQL += " AND PRICE > " + voGDs.getTxtSearchPriceDown() + " ";
		}
		strSQL += !(voGDs.getTxtSearchGoodsQuantityDown().equals(""))
				? " AND QUANTITY <= " + Integer.parseInt(voGDs.getTxtSearchGoodsQuantityDown()) + " "
				: "";
		strSQL += !(voGDs.getDdlSearchStatus().equals("")) ? " AND STATUS = " + voGDs.getDdlSearchStatus() : "";
		strSQL += !(voGDs.getDdlSearchOrderBy().equals("")) ? " ORDER BY PRICE " + voGDs.getDdlSearchOrderBy() : "";
		// 條件 結果
		strSQL = "SELECT ROWNUM NUM_ID, GOODS_ID, GOODS_NAME, PRICE, QUANTITY, IMAGE_NAME, STATUS FROM BEVERAGE_GOODS WHERE GOODS_ID IS NOT NULL "
				+ strSQL;
		// 頁數 起訖
		strSQL1 = "SELECT GOODS_ID GoodsID, GOODS_NAME GoodsName, PRICE Price, QUANTITY Quantity, IMAGE_NAME ImageName, STATUS Status,(SELECT NAME FROM PARAMETER WHERE TYPE = 'Status' AND VALUE = STATUS) StatusName FROM ( "
				+ strSQL + " )  WHERE NUM_ID BETWEEN " + intStartSeq + " AND " + intEndSeq + " ";
		strSQL2 = "SELECT Count(*) TOTALPAGE FROM ( " + strSQL + " ) ";
		// Step1:取得Connection
		// 列表資料
		Goods listgd = new Goods();
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
				// Step2:Create prepareStatement For SQL
				PreparedStatement stmt = conn.prepareStatement(strSQL1);
				ResultSet rs = stmt.executeQuery()) {
			if (rs.getFetchSize() > 0) {
				Set<Good> gd = new LinkedHashSet<Good>();
				while (rs.next()) {
					Good goods = new Good();
					// set object value
					goods.setGoodsID(rs.getBigDecimal("GoodsID")); // 商品編號
					goods.setGoodsName(rs.getString("GoodsName")); // 商品名稱
					goods.setGoodsPrice(rs.getInt("Price"));// 商品價格
					goods.setGoodsQuantity(rs.getInt("Quantity")); // 商品庫存量
					goods.setGoodsImageName(rs.getString("ImageName")); // 商品圖片名稱
					goods.setStatus(rs.getString("Status")); // 商品狀態(1:上架、0:下架)
					goods.setStatusName(rs.getString("StatusName")); // 商品狀態(1:上架、0:下架)
					gd.add(goods); //
					listgd.setListGoods(gd);
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
	 * 後臺管理 新增商品
	 * 
	 * @param voGoodsAdd
	 * 
	 * @return GoodsAdd
	 */
	public GoodsAdd createGoods(voGoodsAdd goods) {
		GoodsAdd rtnGoods = new GoodsAdd();
		int goodsID = 0;
		String[] cols = { "GOODS_ID" };
		try (Connection conn = DBConnectionFactory.getOracleDBConnection()) {
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Insert SQL
			strSQL = "INSERT INTO BEVERAGE_GOODS (GOODS_ID, GOODS_NAME, PRICE, QUANTITY, IMAGE_NAME, STATUS) VALUES (BEVERAGE_GOODS_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement pstmt = conn.prepareStatement(strSQL, cols)) {
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入
				pstmt.setString(1, goods.getTxtGoodsName()); // 商品名稱
				pstmt.setString(2, goods.getTxtGoodsPrice()); // 商品價格
				pstmt.setString(3, goods.getTxtGoodsQuantity()); // 商品庫存量
				pstmt.setString(4, goods.getTxtUpdateGoodsImage()); // 商品圖片名稱
				pstmt.setString(5, goods.getCkbStatus()); // 商品狀態(1:上架、0:下架)
				// Step4:Execute SQL
				ResultMessage rm = new ResultMessage();
				// 判斷新增成功/失敗
				if (pstmt.executeUpdate() > 0) {
					// 取對應的自增主鍵值
					ResultSet rsKeys = pstmt.getGeneratedKeys();
					rsKeys.next();
					goodsID = rsKeys.getInt(1);
					if (goodsID > 0) {
						rtnGoods.setID(String.valueOf(goodsID));
						rm.setStrResult("S");
						rm.setStrMessage("新增商品成功!");
						rtnGoods.setResultMessage(rm);
						conn.commit();
					} else {
						rm.setStrResult("E");
						rm.setStrMessage("新增商品失敗!");
						conn.rollback();
					}
				} else {
					rm.setStrResult("E");
					rm.setStrMessage("新增商品失敗!");
					conn.rollback();
				}
			} catch (SQLException e) {
				// 若發生錯誤則資料 rollback(回滾)
				conn.rollback();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rtnGoods;
	}

	/**
	 * 後臺管理 修改商品
	 * 
	 * @param voGoodsUpdate
	 * 
	 * @return GoodsModify
	 */
	public GoodsUpdate updateGoods(voGoodsUpdate goods) {
		GoodsUpdate rtnGoods = new GoodsUpdate();
		boolean boolImage = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection()) {
			conn.setAutoCommit(false);// 設置交易不自動提交
			// Update SQL
			boolImage = !goods.getTxtUpdateGoodsImage().equals("");
			strSQL = boolImage ? "IMAGE_NAME = ?," : "";
			strSQL = " GOODS_NAME = ?, PRICE = ?, QUANTITY = ?, " + strSQL + " STATUS = ? ";

			strSQL = "UPDATE BEVERAGE_GOODS SET" + strSQL + " WHERE GOODS_ID = ? ";
			try (PreparedStatement pstmt = conn.prepareStatement(strSQL)) {
				pstmt.setString(1, goods.getTxtGoodsName()); // 商品名稱
				pstmt.setString(2, goods.getTxtGoodsPrice()); // 商品價格
				pstmt.setString(3, goods.getTxtGoodsQuantity()); // 商品庫存量
				if (boolImage) {
					pstmt.setString(4, goods.getTxtUpdateGoodsImage()); // 圖片名稱
					pstmt.setString(5, goods.getCkbStatus()); // 商品狀態(1:上架、0:下架)
					pstmt.setString(6, goods.getHidPrimaryID()); // 商品編號
				} else {
					pstmt.setString(4, goods.getCkbStatus()); // 商品狀態(1:上架、0:下架)
					pstmt.setString(5, goods.getHidPrimaryID()); // 商品編號
				}
				// 判斷新增成功/失敗
				ResultMessage rm = new ResultMessage();
				System.out.println(pstmt.executeUpdate());
				if (pstmt.executeUpdate() > 0) {
					rtnGoods.setID(goods.getHidPrimaryID());
					rm.setStrResult("S");
					rm.setStrMessage("修改商品成功!");
					rtnGoods.setResultMessage(rm);
					conn.commit();
				} else {
					rm.setStrResult("E");
					rm.setStrMessage("修改商品失敗!");
					conn.rollback();
				}
			} catch (SQLException e) {
				conn.rollback();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rtnGoods;
	}

	/**
	 * 後臺管理刪除商品
	 * 
	 * @param goodsID
	 * @return boolean
	 */
	public boolean deleteGoods(String strID) {
		boolean deleteSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection()) {
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// DELETE SQL
			strSQL = "DELETE FROM BEVERAGE_GOODS WHERE GOODS_ID = ? ";
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(strSQL)) {
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入
				stmt.setString(1, strID);
				// Step5:交易提交
				deleteSuccess = stmt.executeUpdate() == 1 ? true : false;
				if (deleteSuccess)
					conn.commit();
				else
					conn.rollback();
			} catch (SQLException e) {
				// 若發生錯誤則資料 rollback(回滾)
				conn.rollback();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleteSuccess;
	}

	/**
	 * 後臺管理顧客訂單查詢
	 * 
	 * @param queryStartDate
	 * @param queryEndDate
	 * @return Set(SalesReport)
	 */
	public Orders queryOrderBetweenDate(voGoodsOrder voOrder) {
		strSQL = "";
		strSQL1 = "";
		strSQL2 = "";
		String startDate = voOrder.getTxtSearchStartDate().replace('/', '-');
		String endDate = voOrder.getTxtSearchEndDate().replace('/', '-');
		int intMainPage = 0; // 指定頁數
		int intShowPage = 0; // 每頁筆數
		if (voOrder.getStrAction().equals("List")) {
			intMainPage = Integer.parseInt(voOrder.getStrMainPage().equals("") ? "1" : voOrder.getStrMainPage());
			intShowPage = Integer.parseInt(voOrder.getStrShowPage().equals("") ? "10" : voOrder.getStrShowPage());
		} else { // 防呆
			intMainPage = 1;
			intShowPage = 10;
		}
		int intEndSeq = intShowPage * intMainPage;
		int intStartSeq = intEndSeq - intShowPage + 1;

		Orders listOrder = new Orders();
		// 條件
		if (!startDate.equals("") && !endDate.equals("")) // 都空白
			strSQL += "WHERE TO_CHAR( bo.ORDER_DATE, 'YYYY-MM-DD') BETWEEN '" + startDate + "' AND '" + endDate + "' ";
		else if (startDate.equals("") && !endDate.equals("")) // 起空白
			strSQL += "WHERE TO_CHAR( bo.ORDER_DATE, 'YYYY-MM-DD') > '" + endDate + "' ";
		else if (!startDate.equals("") && !endDate.equals("")) // 迄空白
			strSQL += "WHERE TO_CHAR( bo.ORDER_DATE, 'YYYY-MM-DD') < '" + startDate + "' ";

		strSQL = "SELECT ROWNUM NUM_ID, bo.ORDER_ID AS ORDER_ID, bo.CUSTOMER_ID AS CUSTOMER_ID, bm.CUSTOMER_NAME AS CUSTOMER_NAME, bo.ORDER_DATE AS ORDER_DATE, bg.GOODS_NAME AS GOODS_NAME, bo.GOODS_BUY_PRICE AS GOODS_BUY_PRICE, bo.BUY_QUANTITY AS BUY_QUANTITY, (bo.GOODS_BUY_PRICE * bo.BUY_QUANTITY) AS AMOUNT "
				+ "FROM BEVERAGE_ORDER bo " + "LEFT JOIN BEVERAGE_GOODS bg ON bg.GOODS_ID = bo.GOODS_ID "
				+ "LEFT JOIN BEVERAGE_MEMBER bm ON bm.IDENTIFICATION_NO = bo.CUSTOMER_ID " + strSQL;
		strSQL1 = "SELECT ORDER_ID,  CUSTOMER_ID CUSTOMER_ID, CUSTOMER_NAME, ORDER_DATE, GOODS_NAME, GOODS_BUY_PRICE, BUY_QUANTITY, AMOUNT FROM ("
				+ strSQL + ") WHERE NUM_ID BETWEEN " + intStartSeq + " AND " + intEndSeq + " ";
		strSQL2 = "SELECT Count(*) TOTALPAGE FROM (" + strSQL + ")";

		// Step1:取得Connection
		Set<Order> listor = new LinkedHashSet<Order>();
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
				// Step2:Create PreparedStatement For SQL
				PreparedStatement stmt = conn.prepareStatement(strSQL1)) {
			try (ResultSet rs = stmt.executeQuery()) {
				// Step3:Process Result
				if (rs.getFetchSize() > 0) {
					while (rs.next()) {
						Order o = new Order();
						o.setOrderID(rs.getLong("ORDER_ID"));// 訂單編號
						o.setCustomerID(rs.getString("CUSTOMER_ID")); // 客戶名稱
						o.setCustomerName(rs.getString("CUSTOMER_NAME")); // 客戶名稱
						o.setOrderDate(rs.getString("ORDER_DATE")); // 訂單日期
						o.setGoodsName(rs.getString("GOODS_NAME")); // 商品名稱
						o.setGoodsBuyPrice(rs.getInt("GOODS_BUY_PRICE"));// 商品金額(購買單價)
						o.setBuyQuantity(rs.getInt("BUY_QUANTITY"));// 購買數量
						o.setBuyAmount(rs.getInt("AMOUNT")); // 總金額
						listor.add(o);
						listOrder.setListOrder(listor);
					}
				}
			} catch (SQLException e) {
				throw e;
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
					listOrder.setTotalCount(rs.getInt("TOTALPAGE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOrder;
	}

}
