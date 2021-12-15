package jp.saori.goodslist.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsListModel implements Serializable {
	private int goodsId;			//グッズID
	private String goodsName;		//グッズ名
	private int goodsPrice;		//価格
	private int num;				//個数
	private int subTotal;			//小計
	private String goodsMemo;		//備考
	private int maxNum;			//購入制限
	private List<ListBoxModel> numberList;		//ドロップダウンリスト

	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
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
	public List<ListBoxModel> getNumberList() {
		return numberList;
	}
	public void setNumberList(List<ListBoxModel> numberList) {
		this.numberList = numberList;
	}
	//小計を取得するメソッド
	public int getSubTotal() {
		return goodsPrice * num;
	}
	//ドロップダウンリストを生成するメソッド
	public List<ListBoxModel> getNumberList(int maxNum){
		numberList = new ArrayList<ListBoxModel>();
		for (int i = 0; i <= maxNum; i++) {
			numberList.add(new ListBoxModel(Integer.toString(i),Integer.toString(i)));
		}
		return numberList;
	}
	public void setNumberList() {
		this.numberList = getNumberList(maxNum);
	}

}
