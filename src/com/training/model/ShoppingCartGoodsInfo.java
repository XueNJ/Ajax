package com.training.model;

import java.util.Set;

public class ShoppingCartGoodsInfo {
	
	private int totalAmount;
	
	private Set<ShoppingCarGoods> shoppingCartGoods;

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Set<ShoppingCarGoods> getShoppingCartGoods() {
		return shoppingCartGoods;
	}

	public void setShoppingCartGoods(Set<ShoppingCarGoods> shoppingCartGoods) {
		this.shoppingCartGoods = shoppingCartGoods;
	}
}
