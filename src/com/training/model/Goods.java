package com.training.model;

import java.util.Set;

public class Goods {
	// member
	private Set<Good> listGoods;
	private int TotalCount;
	// get set
	public Set<Good> getListGoods() {
		return listGoods;
	}
	public void setListGoods(Set<Good> listGoods) {
		this.listGoods = listGoods;
	}
	public int getTotalCount() {
		return TotalCount;
	}
	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}
	
	
}
