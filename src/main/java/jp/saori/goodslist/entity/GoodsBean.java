package jp.saori.goodslist.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jp.saori.goodslist.model.ListBoxModel;

public class GoodsBean implements Serializable {
	private int goodsId;		//グッズID
	private int eventId;		//イベントID
	private String goodsName;	//グッズ名
	private int goodsPrice;	//価格
	private String goodsMemo;	//備考
	private int maxNum;		//購入制限
	private List<ListBoxModel> numberList;

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

	public List<ListBoxModel> getNumberList(int maxNum){
		numberList = new ArrayList<ListBoxModel>();
		for (int i = 0; i <= maxNum; i++) {
			numberList.add(new ListBoxModel(Integer.toString(i),Integer.toString(i)));
		}
		return numberList;
	}

	public List<ListBoxModel> getNumberList() {
		return numberList;
	}

	public void setNumberList() {
		this.numberList = getNumberList(maxNum);
	}

}
