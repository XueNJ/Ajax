package com.training.formbean;

import org.apache.struts.action.ActionForm;

public class FrontendForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	// member
	private String id;	
	private String name;
	private String pwd;
	private String pwdCheck;
	private String searchKeyword; // 商品查詢
	private String strMainPage;
	private String strShowPage;
	private int inputMoney; // 投入金額
	private int goodsID; // 產品 ID
	private int buyQuantity; // 產品 數量
	// get set 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwdCheck() {
		return pwdCheck;
	}
	public void setPwdCheck(String pwdCheck) {
		this.pwdCheck = pwdCheck;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getStrMainPage() {
		return strMainPage;
	}
	public void setStrMainPage(String strMainPage) {
		this.strMainPage = strMainPage;
	}
	public String getStrShowPage() {
		return strShowPage;
	}
	public void setStrShowPage(String strShowPage) {
		this.strShowPage = strShowPage;
	}
	public int getInputMoney() {
		return inputMoney;
	}
	public void setInputMoney(int inputMoney) {
		this.inputMoney = inputMoney;
	}
	public int getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public int getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
