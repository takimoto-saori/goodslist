package jp.saori.goodslist.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.saori.goodslist.dao.GoodsDao;
import jp.saori.goodslist.entity.EventBean;
import jp.saori.goodslist.entity.GoodsBean;

public class GoodsInsert {
	private boolean checkNumber(String value) {
		if (value == null || value.isEmpty()) {
			return false;
		} else {
			try {
				Integer.parseInt(value);
				return false;
			} catch (NumberFormatException e) {
				return true;
			}
		}
	}

	public int execute(HttpServletRequest request) throws Exception {
		//セッションを取得
		HttpSession session = request.getSession(false);
		EventBean eventBean = (EventBean)session.getAttribute("eventList");
		int eventId = eventBean.getEventId();
		session.setAttribute("eventId", eventId);

		//登録する情報をリクエストパラメータから取得
		String goodsName = request.getParameter("goodsName");
		String goodsPrice = request.getParameter("goodsPrice");
		String maxNum = request.getParameter("maxNum");
		String goodsMemo = request.getParameter("goodsMemo");

		request.setAttribute("goodsName", goodsName);
		request.setAttribute("goodsPrice", goodsPrice);
		request.setAttribute("maxNum", maxNum);
		request.setAttribute("goodsMemo", goodsMemo);

		boolean checkPrice = checkNumber(goodsPrice);
		boolean checkNum = checkNumber(maxNum);
		request.setAttribute("checkPrice", checkPrice);
		request.setAttribute("checkNum", checkNum);

		GoodsDao dao = null;
		int numRow = 0;
		try {
			if (goodsName != null && !goodsName.isEmpty() && goodsPrice != null && !goodsPrice.isEmpty()) {
				GoodsBean bean = new GoodsBean();
				bean.setEventId(eventId);
				bean.setGoodsName(goodsName);
				bean.setGoodsPrice(Integer.parseInt(goodsPrice));
				bean.setGoodsMemo(goodsMemo);
				if (maxNum != null && !maxNum.isEmpty()) {
					bean.setMaxNum(Integer.parseInt(maxNum));
				}
				dao = new GoodsDao();
				numRow = dao.insertGoods(bean);
				if (numRow == 0) {
					request.setAttribute("message", "グッズ情報を登録できませんでした");
				}
			} else {
				request.setAttribute("errorMessage", "未入力の項目があるため、登録できません");
			}
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
		if (goodsName == null || goodsName.isEmpty()) {
			request.setAttribute("errorMessage1", "未入力です");
		}
		if (goodsPrice == null || goodsPrice.isEmpty()) {
			request.setAttribute("errorMessage2", "未入力です");
		}
		return numRow;
	}
}
