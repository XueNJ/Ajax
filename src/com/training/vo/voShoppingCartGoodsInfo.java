package com.training.vo;

import java.util.Set;

public class voShoppingCartGoodsInfo {
	
	private int totalAmount;
	
	private Set<voShoppingCarGoods> shoppingCartGoods;

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Set<voShoppingCarGoods> getShoppingCartGoods() {
		return shoppingCartGoods;
	}

	public void setShoppingCartGoods(Set<voShoppingCarGoods> shoppingCartGoods) {
		this.shoppingCartGoods = shoppingCartGoods;
	}
}
