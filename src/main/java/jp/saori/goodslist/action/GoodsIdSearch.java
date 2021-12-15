package jp.saori.goodslist.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.saori.goodslist.dao.GoodsDao;
import jp.saori.goodslist.entity.EventBean;
import jp.saori.goodslist.entity.GoodsBean;
import jp.saori.goodslist.model.GoodsListModel;

public class GoodsIdSearch {
	public void execute(HttpServletRequest request) throws Exception {
		String[] getGoodsId = request.getParameterValues("goodsId");
		String[] getNum = request.getParameterValues("num");

		//セッションを取得
		HttpSession session = request.getSession(false);
		EventBean eventList = null;
		if (session != null) {
			eventList = (EventBean) session.getAttribute("eventList");
		}

		//グッズリストを取得
		if (eventList != null) {
			GoodsDao dao = null;
			try {
			ArrayList<GoodsBean> goodsList = new ArrayList<GoodsBean>();
			int totalNum = 0;
			int total = 0;
			ArrayList<GoodsListModel> goodsListModel = new ArrayList<GoodsListModel>();
			dao = new GoodsDao();
			GoodsBean goodsBean = null;
			GoodsListModel goodsListBean = null;
			//取得したグッズIDからグッズ情報を検索して取得
			for (String goodsId : getGoodsId) {
				goodsBean = dao.goodsSearch(Integer.parseInt(goodsId));
				goodsList.add(goodsBean);
			}
			request.setAttribute("goodsList", goodsList);
			for (GoodsBean bean : goodsList) {
				goodsBean = bean;
				goodsListBean = new GoodsListModel();
				goodsListBean.setGoodsId(goodsBean.getGoodsId());
				goodsListBean.setGoodsName(goodsBean.getGoodsName());
				goodsListBean.setGoodsPrice(goodsBean.getGoodsPrice());
				if(getGoodsId != null) {
					for (int i = 0; i < getGoodsId.length; i++) {
						int goodsId = Integer.parseInt(getGoodsId[i]);
						if (goodsBean.getGoodsId() == goodsId) {
							goodsListBean.setNum(Integer.parseInt(getNum[i]));
						}
					}
				}
				goodsListBean.setMaxNum(goodsBean.getMaxNum());
				goodsListBean.setSubTotal(goodsListBean.getSubTotal());
				goodsListBean.setGoodsMemo(goodsBean.getGoodsMemo());
				goodsListBean.setNumberList();
				goodsListModel.add(goodsListBean);
			}
			request.setAttribute("goodsListModel", goodsListModel);
			for (GoodsListModel goods : goodsListModel) {
				totalNum += goods.getNum();
				total += goods.getSubTotal();
			}
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("total", total);
			request.setAttribute("param", true);
			request.setAttribute("btn", "再計算");
			} finally {
				if (dao != null) {
					dao.close();
				}
			}
		}
	}
}
