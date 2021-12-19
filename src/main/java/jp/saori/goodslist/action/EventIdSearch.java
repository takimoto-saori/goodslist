package jp.saori.goodslist.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.saori.goodslist.dao.EventDao;
import jp.saori.goodslist.dao.GoodsDao;
import jp.saori.goodslist.entity.EventBean;
import jp.saori.goodslist.entity.GoodsBean;
import jp.saori.goodslist.model.GoodsListModel;

public class EventIdSearch {
	public void execute(HttpServletRequest request) throws Exception {
		//セッションを取得
		HttpSession session = request.getSession(true);
//		System.out.println("EventIdSearch : " + session);

		//イベントIDを取得
		String eventNo = request.getParameter("eventNo");
		if (eventNo == null || eventNo.isEmpty()) {
			eventNo = (String)session.getAttribute("eventNo");
		}
		EventDao eDao = null;
		GoodsDao gDao = null;
		try {
			if (eventNo != null && !eventNo.isEmpty()) {
				eDao = new EventDao();
				//取得したイベントIDからイベント情報を検索して取得
				EventBean eventBean = eDao.getEventId(Integer.parseInt(eventNo));
				session.setAttribute("eventList", eventBean);
				gDao = new GoodsDao();
				GoodsBean goodsBean = null;
				//グッズ情報を検索して取得
				ArrayList<GoodsBean> goodsList = gDao.getGoodsList(Integer.parseInt(eventNo));
				if (goodsList.isEmpty()) {
					request.setAttribute("message", "登録されているグッズはありません");
				} else {
					request.setAttribute("goodsList", goodsList);
					ArrayList<GoodsListModel> goodsListModel = new ArrayList<GoodsListModel>();
					for (GoodsBean bean : goodsList) {
						goodsBean = bean;
						GoodsListModel goodsListBean = new GoodsListModel();
						goodsListBean.setGoodsId(goodsBean.getGoodsId());
						goodsListBean.setGoodsName(goodsBean.getGoodsName());
						goodsListBean.setGoodsPrice(goodsBean.getGoodsPrice());
						goodsListBean.setMaxNum(goodsBean.getMaxNum());
						goodsListBean.setSubTotal(goodsListBean.getSubTotal());
						goodsListBean.setGoodsMemo(goodsBean.getGoodsMemo());
						goodsListBean.setNumberList();
						goodsListModel.add(goodsListBean);
					}
					request.setAttribute("goodsListModel", goodsListModel);
					request.setAttribute("param", false);
					request.setAttribute("btn", "計算");
				}
			}
		} finally {
			if (gDao != null) {
				gDao.close();
			}
			if (eDao != null) {
				eDao.close();
			}
		}
	}
}
