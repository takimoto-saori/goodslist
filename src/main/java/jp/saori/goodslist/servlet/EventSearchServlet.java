package jp.saori.goodslist.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.saori.goodslist.action.EventSearch;

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
		String searchBtn = request.getParameter("btn");
		String jsp;
		try {
			if(searchBtn != null && searchBtn.equals("eventSearch")) {
				//イベント検索
				EventSearch es = new EventSearch();
				es.execute(request);
				jsp = "/eventSearch.jsp";
			} else {
				request.setAttribute("errorMessage", "不正アクセスです");
				request.setAttribute("backAddress", "search");
				jsp = "/error.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "エラーが発生しました");
			request.setAttribute("backAddress", "search");
			jsp = "/error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "エラーが発生しました");
			request.setAttribute("backAddress", "search");
			jsp = "/error.jsp";
		}

		//転送処理
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

}
