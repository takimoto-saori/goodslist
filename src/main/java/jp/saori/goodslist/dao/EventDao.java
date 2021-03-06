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

	//イベント全件検索、キーワード検索用のメソッド
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

	//イベントID検索用のメソッド
	public EventBean getEventId(int eventId) throws SQLException {
		EventBean bean = null;
		PreparedStatement ps_id = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM goods_list_db.events WHERE event_id = ?";
			ps_id = connection.prepareStatement(sql);
			ps_id.setInt(1, eventId);
			rs = ps_id.executeQuery();
			if (rs.next()) {
				bean = new EventBean();
				bean.setEventId(rs.getInt("event_id"));
				bean.setEventName(rs.getString("event_name"));
				bean.setArtistName(rs.getString("artist_name"));
				bean.setEventMemo(rs.getString("event_memo"));
			}
			if (rs != null) {
				rs.close();
			}
		} finally {
			if (ps_id != null) {
				ps_id.close();
			}
		}
		return bean;
	}

	//event表に登録するメソッド
	public int insertEvent(EventBean event) throws SQLException {
		int numRow = 0;
		PreparedStatement ps = null;
		try {
			//トランザクション開始
			connection.setAutoCommit(false);
			//SQL文
			String sql = "INSERT INTO events (event_name, artist_name, event_memo) VALUES (?, ?, ?)";
			ps = connection.prepareStatement(sql);
			//INパラメーターの設定
			ps.setString(1, event.getEventName());
			ps.setString(2, event.getArtistName());
			ps.setString(3, event.getEventMemo());
			//SQLの発行、登録された行数を取得
			numRow = ps.executeUpdate();
		} finally {
			if (numRow > 0) {
				connection.commit();
			} else {
				connection.rollback();
			}
			//切断処理
			if (ps != null) {
				ps.close();
			}
		}
		return numRow;
	}

}
