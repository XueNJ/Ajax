package com.training.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.apache.struts.actions.DispatchAction;

import com.training.dao.FrontEndDao;
import com.training.model.Good;
import com.training.model.Goods;
import com.training.vo.voMemberSearch;
import com.training.vo.voShoppingCarGoods;

public class FrontendService extends DispatchAction {

	private static FrontendService frontendService = new FrontendService();

	private FrontendService() {
	}

	public static FrontendService getInstance() {
		return frontendService;
	}

	private FrontEndDao ftDao = FrontEndDao.getInstance();

	/**
	 * 後臺管理商品列表
	 * 
	 * @return Set(Goods)
	 */
	public Goods searchGoods(voMemberSearch voMemberSearch) {
		return ftDao.searchGoods(voMemberSearch);
	}

	/**
	 * 查詢顧客所購買商品資料(價格、庫存)
	 * 
	 * @param goodsIDs
	 * @return Map(BigDecimal, Goods)
	 */
	public Map<BigDecimal, Good> queryBuyGoods(Set<BigDecimal> goodsIDs) {
		return ftDao.queryBuyGoods(goodsIDs);
	}

	/**
	 * 建立訂單資料
	 * 
	 * @param customerID 【客戶編號】
	 * @param voShoppingCarGoods 【購物車】
	 * @return boolean
	 */
	public boolean batchCreateGoodsOrder(String customerID, Set<voShoppingCarGoods> voShoppingCarGoods) {
		return ftDao.batchCreateGoodsOrder(customerID, voShoppingCarGoods);
	}

	/**
	 * 交易完成更新扣商品庫存數量
	 * 
	 * @param voShoppingCarGoods【購物車】
	 * @return boolean
	 */
	public boolean batchUpdateGoodsQuantity(Set<voShoppingCarGoods> voShoppingCarGoods) {
		return ftDao.batchUpdateGoodsQuantity(voShoppingCarGoods);
	}

}
