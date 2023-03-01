package com.training.formbean;

import org.apache.struts.action.ActionForm;

public class GoodForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// member
	private String txtSearchGoodsID;
	private String txtSearchGoodsName;
	private String txtSearchPriceDown;
	private String txtSearchPriceUp;
	private String ddlSearchOrderBy;
	private String txtSearchGoodsQuantityDown;
	private String ddlSearchStatus;
	private String strMainPage;
	private String strShowPage;
	private String strAction;
	private String txtGoodsName;
	private String txtGoodsPrice;
	private String txtGoodsQuantity;
	private String txtUpdateGoodsImage;
	private String ckbStatus;
	private String hidPrimaryID;
	private String txtSearchStartDate;
	private String txtSearchEndDate;

	// get set
	public String getTxtSearchGoodsID() {
		return txtSearchGoodsID;
	}

	public void setTxtSearchGoodsID(String txtSearchGoodsID) {
		this.txtSearchGoodsID = txtSearchGoodsID;
	}

	public String getTxtSearchGoodsName() {
		return txtSearchGoodsName;
	}

	public void setTxtSearchGoodsName(String txtSearchGoodsName) {
		this.txtSearchGoodsName = txtSearchGoodsName;
	}

	public String getTxtSearchPriceDown() {
		return txtSearchPriceDown;
	}

	public void setTxtSearchPriceDown(String txtSearchPriceDown) {
		this.txtSearchPriceDown = txtSearchPriceDown;
	}

	public String getTxtSearchPriceUp() {
		return txtSearchPriceUp;
	}

	public void setTxtSearchPriceUp(String txtSearchPriceUp) {
		this.txtSearchPriceUp = txtSearchPriceUp;
	}

	public String getDdlSearchOrderBy() {
		return ddlSearchOrderBy;
	}

	public void setDdlSearchOrderBy(String ddlSearchOrderBy) {
		this.ddlSearchOrderBy = ddlSearchOrderBy;
	}

	public String getTxtSearchGoodsQuantityDown() {
		return txtSearchGoodsQuantityDown;
	}

	public void setTxtSearchGoodsQuantityDown(String txtSearchGoodsQuantityDown) {
		this.txtSearchGoodsQuantityDown = txtSearchGoodsQuantityDown;
	}

	public String getDdlSearchStatus() {
		return ddlSearchStatus;
	}

	public void setDdlSearchStatus(String ddlSearchStatus) {
		this.ddlSearchStatus = ddlSearchStatus;
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

	public String getStrAction() {
		return strAction;
	}

	public void setStrAction(String strAction) {
		this.strAction = strAction;
	}

	public String getTxtGoodsName() {
		return txtGoodsName;
	}

	public void setTxtGoodsName(String txtGoodsName) {
		this.txtGoodsName = txtGoodsName;
	}

	public String getTxtGoodsPrice() {
		return txtGoodsPrice;
	}

	public void setTxtGoodsPrice(String txtGoodsPrice) {
		this.txtGoodsPrice = txtGoodsPrice;
	}

	public String getTxtGoodsQuantity() {
		return txtGoodsQuantity;
	}

	public void setTxtGoodsQuantity(String txtGoodsQuantity) {
		this.txtGoodsQuantity = txtGoodsQuantity;
	}

	public String getTxtUpdateGoodsImage() {
		return txtUpdateGoodsImage;
	}

	public void setTxtUpdateGoodsImage(String txtUpdateGoodsImage) {
		this.txtUpdateGoodsImage = txtUpdateGoodsImage;
	}

	public String getCkbStatus() {
		return ckbStatus;
	}

	public void setCkbStatus(String ckbStatus) {
		this.ckbStatus = ckbStatus;
	}

	public String getHidPrimaryID() {
		return hidPrimaryID;
	}

	public void setHidPrimaryID(String hidPrimaryID) {
		this.hidPrimaryID = hidPrimaryID;
	}

	public String getTxtSearchStartDate() {
		return txtSearchStartDate;
	}

	public void setTxtSearchStartDate(String txtSearchStartDate) {
		this.txtSearchStartDate = txtSearchStartDate;
	}

	public String getTxtSearchEndDate() {
		return txtSearchEndDate;
	}
	public void setTxtSearchEndDate(String txtSearchEndDate) {
		this.txtSearchEndDate = txtSearchEndDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
