package jp.saori.goodslist.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jp.saori.goodslist.dao.EventDao;
import jp.saori.goodslist.entity.EventBean;

public class EventSearch {
	public void execute(HttpServletRequest request) throws Exception {
		EventDao dao = null;
		String keyWord = request.getParameter("paramEvent");
		try {
			dao = new EventDao();
			ArrayList<EventBean> eventList = dao.getEventList(keyWord);
			if (eventList.isEmpty()) {
				request.setAttribute("message", "該当イベントはありません");
			} else {
				request.setAttribute("eventList", eventList);
			}
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}
