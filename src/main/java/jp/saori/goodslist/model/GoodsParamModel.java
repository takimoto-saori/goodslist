package jp.saori.goodslist.model;

import java.io.Serializable;

public class GoodsParamModel implements Serializable {
	private String paramGoodsId;
	private String paramNum;

	public String getParamGoodsId() {
		return paramGoodsId;
	}
	public void setParamGoodsId(String paramGoodsId) {
		this.paramGoodsId = paramGoodsId;
	}
	public String getParamNum() {
		return paramNum;
	}
	public void setParamNum(String paramNum) {
		this.paramNum = paramNum;
	}

}
