package com.training.service;


import org.apache.struts.actions.DispatchAction;

import com.training.dao.BackEndDao;
import com.training.model.Goods;
import com.training.model.GoodsAdd;
import com.training.model.GoodsUpdate;
import com.training.model.Orders;
import com.training.vo.voGoods;
import com.training.vo.voGoodsAdd;
import com.training.vo.voGoodsUpdate;
import com.training.vo.voGoodsOrder;

public class BackendService extends DispatchAction {

	private static BackendService backendService = new BackendService();

	private BackendService() {
	}

	public static BackendService getInstance() {
		return backendService;
	}

	private BackEndDao bkDao = BackEndDao.getInstance();

	/**
	 * @methodName 商品列表查詢
	 * 
	 * @input voGoodQuery voGQ
	 * 
	 * @return GoodsLIst
	 */
	public Goods queryGoods(voGoods voGood) {
		return bkDao.queryGoods(voGood);
	}

	/**
	 * 後臺管理新增商品
	 * 
	 * @param goods
	 * @return int(商品編號)
	 */
	public GoodsAdd createGoods(voGoodsAdd voGoods) {
		return bkDao.createGoods(voGoods);
	}

	/**
	 * 後臺管理更新商品
	 * 
	 * @param goods
	 * @return boolean
	 */
	public GoodsUpdate updateGoods(voGoodsUpdate voGoods) {
		return bkDao.updateGoods(voGoods);
	}
	
	/**
	 * 後臺管理刪除商品
	 * 
	 * @param goodsID
	 * @return boolean
	 */
	public boolean deleteGoods(String strID) {
		return bkDao.deleteGoods(strID);
	}

	/**
	 * 後臺管理顧客訂單查詢
	 * 
	 * @param queryStartDate
	 * @param queryEndDate
	 * @return Set(SalesReport)
	 */
	public Orders queryOrderBetweenDate(voGoodsOrder goods) {
		return bkDao.queryOrderBetweenDate(goods);
	}
}
