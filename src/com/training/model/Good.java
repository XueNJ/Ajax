package com.training.model;

import java.math.BigDecimal;

public class Good {
	// member
	private BigDecimal goodsID;
	private String goodsName;
	private int goodsPrice;
	private int goodsQuantity;
	private String goodsImageName;
	private String status;
	private String statusName;
	private String strImagePath;

	// get set
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

	public int getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(int goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

	public String getGoodsImageName() {
		return goodsImageName;
	}

	public void setGoodsImageName(String goodsImageName) {
		this.goodsImageName = goodsImageName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStrImagePath() {
		return strImagePath;
	}

	public void setStrImagePath(String strImagePath) {
		this.strImagePath = strImagePath;
	}

	@Override
	public String toString() {
		return "Goods [goodsID=" + goodsID + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsQuantity=" + goodsQuantity + ", goodsImageName=" + goodsImageName + ", status=" + status
				+ "]";
	}
}
