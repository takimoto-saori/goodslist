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
import javax.servlet.http.HttpSession;

import jp.saori.goodslist.action.EventInsert;

/**
 * Servlet implementation class EventSearchServlet
 */
@WebServlet("/registration")
public class EventRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//JSPへの転送
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/eventRegistration.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエスト処理
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("btn");
		String jsp;
		//イベント登録
		try {
			if (btn != null && btn.equals("eventregist")){
				EventInsert ei = new EventInsert();
				int numRow = ei.execute(request);
				if (numRow == 0) {
					jsp = "/eventRegistration.jsp";
				} else {
					//セッションの取得
					HttpSession session = request.getSession(true);
					session.setAttribute("backAddress", "registration");
					session.setAttribute("back", "イベント登録ページ");
					response.sendRedirect("/goodslist/complete.jsp");
					return;
				}
			} else {
				request.setAttribute("errorMessage", "不正アクセスです");
				request.setAttribute("backAddress", "registration");
				jsp = "/error.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "JDBCのエラーが発生しました");
			request.setAttribute("backAddress", "registration");
			jsp = "/error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "エラーが発生しました");
			request.setAttribute("backAddress", "registration");
			jsp = "/error.jsp";
		}

		//転送処理
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

}
