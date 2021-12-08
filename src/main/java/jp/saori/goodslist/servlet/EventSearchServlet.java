package jp.saori.goodslist.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.saori.goodslist.dao.EventDao;
import jp.saori.goodslist.entity.EventBean;

/**
 * Servlet implementation class EventSearchServlet
 */
@WebServlet("/search")
public class EventSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//JSPへの転送
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/eventSearch.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエスト処理
		request.setCharacterEncoding("UTF-8");
		//検索処理
		String searchBtn = request.getParameter("btn");
		if(searchBtn != null && searchBtn.equals("eventSearch")) {
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
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "エラーが発生しました");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "エラーが発生しました");
			} finally {
				if (dao != null) {
					dao.close();
				}
			}
		}
		//転送処理
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/eventSearch.jsp");
		dispatcher.forward(request, response);
	}

}
