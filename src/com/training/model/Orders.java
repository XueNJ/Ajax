package com.training.model;

import java.util.Set;

public class Orders {
	// member
	private Set<Order> listOrder;
	private int TotalCount;

	// get set
	public Set<Order> getListOrder() {
		return listOrder;
	}

	public void setListOrder(Set<Order> listOrder) {
		this.listOrder = listOrder;
	}

	public int getTotalCount() {
		return TotalCount;
	}

	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}

}
