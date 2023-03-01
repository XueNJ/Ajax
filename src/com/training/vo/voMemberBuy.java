package com.training.vo;

import java.math.BigDecimal;

public class voMemberBuy {
	// member
	private String MemberID; // 顧客ID
	private int inputMoney; // 投入金額
	private BigDecimal goodsID; // 商品 ID
	private String goodsName; // 商品 名稱
	private int buyQuantity; // 商品 購買數量
	private int goodsPrice; // 商品金額
	private int TotalAmount; // 商品總金額
	// get set

	public String getMemberID() {
		return MemberID;
	}

	public void setMemberID(String memberID) {
		MemberID = memberID;
	}

	public int getInputMoney() {
		return inputMoney;
	}

	public void setInputMoney(int inputMoney) {
		this.inputMoney = inputMoney;
	}

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

	public int getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public int getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public int getTotalAmount() {
		return TotalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		TotalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "MemberBuy {goodsID=" + goodsID + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsQuantity=" + buyQuantity + "}";
	}
	
}
