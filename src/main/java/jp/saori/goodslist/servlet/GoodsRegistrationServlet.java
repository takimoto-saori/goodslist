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

import jp.saori.goodslist.action.GoodsInsert;

/**
 * Servlet implementation class EventSearchServlet
 */
@WebServlet("/goodsregistration")
public class GoodsRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("errorMessage", "不正アクセスです");
		//JSPへの転送
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/error.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエスト処理
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("btn");
		//セッションの取得
		HttpSession session = request.getSession(false);
		String jsp;
		if (session != null) {
			try {
				if (btn != null && request.getParameter("goodsregist") != null) {		//グッズ登録
					jsp = "/goodsRegistration.jsp";
				} else if (btn != null && btn.equals("regist")){	//グッズ登録
					GoodsInsert gi = new GoodsInsert();
					int numRow = gi.execute(request);
					if (numRow == 0) {
						jsp = "/goodsRegistration.jsp";
					} else {
						session.setAttribute("message", "グッズ情報を登録しました");
						session.setAttribute("btn", "goods");
						session.setAttribute("backAddress", "search");
						session.setAttribute("back", "イベント検索ページ");
						response.sendRedirect("/goodslist/complete.jsp");
						return;
					}
				} else if (btn != null && request.getParameter("goodsdel") != null) {		//グッズ削除
					jsp = "/maintenance.jsp";
				} else {
					request.setAttribute("errorMessage", "不正アクセスです");
					request.setAttribute("backAddress", "search");
					jsp = "/error.jsp";
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "入力値にエラーがあるため登録できません");
				request.setAttribute("errorMessage3", "数字以外は入力できません");
				jsp = "/goodsRegistration.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "JDBCのエラーが発生しました");
				request.setAttribute("backAddress", "search");
				jsp = "/error.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "エラーが発生しました");
				request.setAttribute("backAddress", "search");
				jsp = "/error.jsp";
			}
		} else {
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
