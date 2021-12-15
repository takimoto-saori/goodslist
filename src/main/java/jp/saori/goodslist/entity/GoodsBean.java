package jp.saori.goodslist.entity;

import java.io.Serializable;

public class GoodsBean implements Serializable {
	private int goodsId;		//グッズID
	private int eventId;		//イベントID
	private String goodsName;	//グッズ名
	private int goodsPrice;	//価格
	private String goodsMemo;	//備考
	private int maxNum;		//購入制限

	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
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
	public String getGoodsMemo() {
		return goodsMemo;
	}
	public void setGoodsMemo(String goodsMemo) {
		this.goodsMemo = goodsMemo;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

}
