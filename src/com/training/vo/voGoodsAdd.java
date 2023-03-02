package com.training.vo;

import org.apache.struts.upload.FormFile;

public class voGoodsAdd {
	// member
	private String txtGoodsName;
	private String txtGoodsPrice;
	private String txtGoodsQuantity;
	private String ckbStatus;
	private FormFile txtUpdateGoodsImage;
	// method
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
	public String getCkbStatus() {
		return ckbStatus;
	}
	public void setCkbStatus(String ckbStatus) {
		this.ckbStatus = ckbStatus;
	}
	public FormFile getTxtUpdateGoodsImage() {
		return txtUpdateGoodsImage;
	}
	public void setTxtUpdateGoodsImage(FormFile txtUpdateGoodsImage) {
		this.txtUpdateGoodsImage = txtUpdateGoodsImage;
	}
	
}
