package jp.saori.goodslist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.saori.goodslist.entity.GoodsBean;

public class GoodsDao {
	private Connection connection;

	//コンストラクター
	//goods_list_dbデータベースとの接続を行う
	public GoodsDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/goods_list_db?useSSL=false";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url, user, password);
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

	//イベントIDからグッズ検索するメソッド
	public ArrayList<GoodsBean> getGoodsList(int eventId) throws SQLException {
		ArrayList<GoodsBean> goodsList = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM goods_list_db.goods WHERE event_id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, eventId);
			rs = ps.executeQuery();
			goodsList = new ArrayList<GoodsBean>();
			while (rs.next()) {
				int num = rs.getInt("max_num");
				if (num == 0) {
					num = 10;
				}
				GoodsBean goods = new GoodsBean();
				goods.setGoodsId(rs.getInt("goods_id"));
				goods.setEventId(rs.getInt("event_id"));
				goods.setGoodsName(rs.getString("goods_name"));
				goods.setGoodsPrice(rs.getInt("goods_price"));
				goods.setGoodsMemo(rs.getString("goods_memo"));
				goods.setMaxNum(num);
				goodsList.add(goods);
			}
			//切断処理
			if (rs != null) {
				rs.close();
			}
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		return goodsList;
	}

	//グッズIDからグッズ検索するメソッド
	public GoodsBean goodsSearch(int goodsId) throws SQLException {
		GoodsBean bean = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM goods_list_db.goods WHERE goods_id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, goodsId);
			rs = ps.executeQuery();
			if (rs.next()) {
				int num = rs.getInt("max_num");
				if (num == 0) {
					num = 10;
				}
				bean = new GoodsBean();
				bean.setGoodsId(rs.getInt("goods_id"));
				bean.setEventId(rs.getInt("event_id"));
				bean.setGoodsName(rs.getString("goods_name"));
				bean.setGoodsPrice(rs.getInt("goods_price"));
				bean.setGoodsMemo(rs.getString("goods_memo"));
				bean.setMaxNum(num);
			}
			//切断処理
			if (rs != null) {
				rs.close();
			}
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		return bean;
	}
}
