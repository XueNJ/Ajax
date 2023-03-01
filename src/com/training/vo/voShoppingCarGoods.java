package com.training.vo;

import java.math.BigDecimal;

public class voShoppingCarGoods {

	private BigDecimal goodsID;
	private String goodsName;
	private int goodsPrice;
	private int buyQuantity;
	
	public BigDecimal getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(BigDecimal goodsID) {
		this.goodsID = goodsID;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	
	@Override
	public String toString() {
		return "{goodsID=\"" + goodsID + "\","
				+ "goodsName=\"" + goodsName + "\","
				+ "goodsPrice=" + goodsPrice + ","
				+ "buyQuantity=" + buyQuantity + "}";
	}
	
}
