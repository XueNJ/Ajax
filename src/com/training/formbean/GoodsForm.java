package com.training.formbean;

import org.apache.struts.action.ActionForm;

public class GoodsForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// member
	private String goodsID; // 商品編號
	private String goodsName; // 商品名稱
	private String goodsPriceDown; // 最低價
	private String goodsPriceUp; // 最高價
	private String goodsOrderby; // 排序
	private String goodsQuantityDown; // 商品低於庫存量
	private String status; // 狀態
	private String mainPage; // 指定頁數
	private String showPage; // 頁數顯示
	private String goodsPrice;
	private String goodsQuantity;
	private String goodsImage;
	private String queryStartDate; // 起
	private String queryEndDate; // 迄
	// get set
	public String getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsPriceDown() {
		return goodsPriceDown;
	}
	public void setGoodsPriceDown(String goodsPriceDown) {
		this.goodsPriceDown = goodsPriceDown;
	}
	public String getGoodsPriceUp() {
		return goodsPriceUp;
	}
	public void setGoodsPriceUp(String goodsPriceUp) {
		this.goodsPriceUp = goodsPriceUp;
	}
	public String getGoodsOrderby() {
		return goodsOrderby;
	}
	public void setGoodsOrderby(String goodsOrderby) {
		this.goodsOrderby = goodsOrderby;
	}
	public String getGoodsQuantityDown() {
		return goodsQuantityDown;
	}
	public void setGoodsQuantityDown(String goodsQuantityDown) {
		this.goodsQuantityDown = goodsQuantityDown;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMainPage() {
		return mainPage;
	}
	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	public String getShowPage() {
		return showPage;
	}
	public void setShowPage(String showPage) {
		this.showPage = showPage;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsQuantity() {
		return goodsQuantity;
	}
	public void setGoodsQuantity(String goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}
	public String getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	public String getQueryStartDate() {
		return queryStartDate;
	}
	public void setQueryStartDate(String queryStartDate) {
		this.queryStartDate = queryStartDate;
	}
	public String getQueryEndDate() {
		return queryEndDate;
	}
	public void setQueryEndDate(String queryEndDate) {
		this.queryEndDate = queryEndDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
