package jp.saori.goodslist.entity;

import java.io.Serializable;

public class EventBean implements Serializable {
	private int eventId;			//イベントID
	private String eventName;		//イベント名
	private String artistName;		//アーティスト名
	private String eventMemo;		//備考

	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getEventMemo() {
		return eventMemo;
	}
	public void setEventMemo(String eventMemo) {
		this.eventMemo = eventMemo;
	}

}
