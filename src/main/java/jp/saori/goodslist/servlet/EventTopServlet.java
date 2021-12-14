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
import javax.servlet.http.HttpSession;

import jp.saori.goodslist.dao.EventDao;
import jp.saori.goodslist.dao.GoodsDao;
import jp.saori.goodslist.entity.EventBean;
import jp.saori.goodslist.entity.GoodsBean;
import jp.saori.goodslist.model.GoodsListModel;

/**
 * Servlet implementation class EventSearchServlet
 */
@WebServlet("/event")
public class EventTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションを取得
		HttpSession session = request.getSession(true);
		//イベントIDを取得
		String eventNo = request.getParameter("eventNo");
		if (eventNo != null && !eventNo.isEmpty()) {
			EventDao dao = null;
			try {
				dao = new EventDao();
				//取得したイベントIDからイベント情報を検索して取得
				EventBean bean = dao.getEventId(Integer.parseInt(eventNo));
				session.setAttribute("eventList", bean);
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

		GoodsDao dao = null;

		try {
			dao = new GoodsDao();
			//取得したイベントIDからグッズ情報を検索して取得
			ArrayList<GoodsBean> goodsList = dao.getGoodsList(Integer.parseInt(eventNo));
			if (goodsList.isEmpty()) {
				request.setAttribute("message", "登録されているグッズはありません");
			} else {
				request.setAttribute("goodsList", goodsList);
				request.setAttribute("subTotal", "-");
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

		//JSPへの転送
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/eventTop.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエスト処理
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("btn");
		String[] getGoodsId = request.getParameterValues("goodsId");
		String[] getNum = request.getParameterValues("num");

		//セッションを取得
		HttpSession session = request.getSession(false);
		EventBean eventList = null;
		if (session != null) {
			eventList = (EventBean) session.getAttribute("eventList");
		}

		//イベントIDを取得
		if (eventList != null) {
			GoodsDao dao = null;
			ArrayList<GoodsBean> goodsList = new ArrayList<GoodsBean>();
			int totalNum = 0;
			int total = 0;
			if(btn != null && btn.equals("calc")) {
				ArrayList<GoodsListModel> goodsListModel = new ArrayList<GoodsListModel>();
				try {
					dao = new GoodsDao();
					GoodsBean goodsBean = null;
					//取得したグッズIDからグッズ情報を検索して取得
					for (String goodsId : getGoodsId) {
						goodsBean = dao.goodsSearch(Integer.parseInt(goodsId));
						goodsList.add(goodsBean);
					}
					request.setAttribute("goodsList", goodsList);
					for (GoodsBean bean : goodsList) {
						goodsBean = bean;
						GoodsListModel goodsListBean = new GoodsListModel();
						goodsListBean.setGoodsId(goodsBean.getGoodsId());
						goodsListBean.setGoodsName(goodsBean.getGoodsName());
						goodsListBean.setGoodsPrice(goodsBean.getGoodsPrice());
						for (int i = 0; i < getGoodsId.length; i++) {
							int goodsId = Integer.parseInt(getGoodsId[i]);
							if (goodsBean.getGoodsId() == goodsId) {
								goodsListBean.setNum(Integer.parseInt(getNum[i]));
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
			} else {
				request.setAttribute("errorMessage", "不正アクセスです");
			}
		}

		//JSPへの転送
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/eventTop.jsp");
		dispatcher.forward(request, response);
	}
}