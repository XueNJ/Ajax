package com.training.model;

public class Parameter {
	// member
	private String Selected; // 名稱
	private String Valued; // 數值
	// get set
	public String getSelected() {
		return Selected;
	}
	public void setSelected(String selected) {
		Selected = selected;
	}
	public String getValued() {
		return Valued;
	}
	public void setValued(String valued) {
		Valued = valued;
	}
	@Override
	public String toString() {
		return "Parameter { Selected=" + Selected 
				+ ", Valued=" + Valued 
				+ "}";
	}	
}
