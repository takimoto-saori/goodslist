package jp.saori.goodslist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.saori.goodslist.entity.EventBean;

public class EventDao {
	private Connection connection;
	private PreparedStatement ps_no_key;
	private PreparedStatement ps;

	//コンストラクター
	//goods_list_dbデータベースとの接続を行う
	public EventDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/goods_list_db?useSSL=false";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url, user, password);

		//全件検索するSQL（キーワードが未入力の場合）
		String sql1 = "SELECT * FROM goods_list_db.events";
		ps_no_key = connection.prepareStatement(sql1);
		//キーワード検索するSQL
		String sql2 = "SELECT * FROM goods_list_db.events WHERE (event_name LIKE ? OR artist_name LIKE ?)";
		ps = connection.prepareStatement(sql2);
	}

	//goods_list_dbデータベースとの切断をするメソッド
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<EventBean> getEventList(String keyWord) throws SQLException {
		ResultSet rs = null;
		if (keyWord == null || keyWord.isEmpty()) {		//キーワードが未入力の場合
			rs = ps_no_key.executeQuery();
		} else {		//キーワードが入力された場合
			ps.setString(1, "%" + keyWord + "%");
			ps.setString(2, "%" + keyWord + "%");
			rs = ps.executeQuery();
		}
		//イベント一覧用のArrayListオブジェクトの生成
		ArrayList<EventBean> eventList = new ArrayList<EventBean>();
		while (rs.next()) {
			EventBean event = new EventBean();
			event.setEventId(rs.getInt("event_id"));
			event.setEventName(rs.getString("event_name"));
			event.setArtistName(rs.getString("artist_name"));
			event.setEventMemo(rs.getString("event_memo"));
			eventList.add(event);
		}
		//切断処理
		if (rs != null) {
			rs.close();
		}
		if (ps_no_key != null){
			ps_no_key.close();
		}
		if (ps != null) {
			ps.close();
		}

	return eventList;
	}
}

//	//引数を受け取らず、イベント全件検索を行うメソッド
//	public ArrayList<EventBean> getAllEventList() throws SQLException {
//		ArrayList<EventBean> eventList = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			String sql = "SELECT * FROM goods_list_db.events";
//			ps = connection.prepareStatement(sql);
//			rs = ps.executeQuery();
//			eventList = new ArrayList<EventBean>();
//			while (rs.next()) {
//				EventBean eBean = new EventBean();
//				eBean.setEventId(rs.getInt("event_id"));
//				eBean.setEventName(rs.getString("event_name"));
//				eBean.setArtistName(rs.getString("artist_name"));
//				eBean.setEventMemo(rs.getString("event_memo"));
//				eventList.add(eBean);
//			}
//			rs.close();
//		} finally {
//			ps.close();
//		}
//		return eventList;
//	}
//
//	//引数にキーワード（イベント名又はアーティスト名）を受け取り、イベント検索を行うメソッド
//	public ArrayList<EventBean> getEventList(String keyWord) throws SQLException {
//		ArrayList<EventBean> eventList = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			String sql = "SELECT * FROM goods_list_db.events WHERE (event_name LIKE ? OR artist_name LIKE ?)";
//			ps = connection.prepareStatement(sql);
//			ps.setString(1, "%" + keyWord + "%");
//			ps.setString(2, "%" + keyWord + "%");
//			rs = ps.executeQuery();
//			eventList = new ArrayList<EventBean>();
//			while (rs.next()) {
//				EventBean eBean = new EventBean();
//				eBean.setEventId(rs.getInt("event_id"));
//				eBean.setEventName(rs.getString("event_name"));
//				eBean.setArtistName(rs.getString("artist_name"));
//				eBean.setEventMemo(rs.getString("event_memo"));
//				eventList.add(eBean);
//			}
//			rs.close();
//		} finally {
//			ps.close();
//		}
//		return eventList;
//	}
