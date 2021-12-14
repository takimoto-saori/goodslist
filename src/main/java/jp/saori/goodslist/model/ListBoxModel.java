package jp.saori.goodslist.model;

public class ListBoxModel {
	private String label;
	private String data;

	public ListBoxModel(String label, String data) {
		this.label = label;
		this.data = data;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
