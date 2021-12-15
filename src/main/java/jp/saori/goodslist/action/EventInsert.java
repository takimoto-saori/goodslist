package jp.saori.goodslist.action;

import javax.servlet.http.HttpServletRequest;

import jp.saori.goodslist.dao.EventDao;
import jp.saori.goodslist.entity.EventBean;

public class EventInsert {
	public void execute(HttpServletRequest request) throws Exception {
		EventDao dao = null;
		//登録する情報をリクエストパラメータから取得
		String eventName = request.getParameter("eventName");
		String artistName = request.getParameter("artistName");
		String evnetMemo = request.getParameter("eventMemo");

		try {
			if (eventName != null && !eventName.isEmpty()) {
				EventBean bean = new EventBean();
				bean.setEventName(eventName);
				bean.setArtistName(artistName);
				bean.setEventMemo(evnetMemo);
				dao = new EventDao();
				int numRow = dao.insertEvent(bean);
				if (numRow > 0) {
					request.setAttribute("message", "イベント情報を登録しました");
				} else {
					request.setAttribute("message", "イベント情報を登録できませんでした");
				}
			} else {
				request.setAttribute("errorMessage", "イベント名が未入力のため、登録できません");
			}
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}
