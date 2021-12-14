<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Event</title>
</head>
<body>
	<header><h1>イベントページ</h1></header>
	<main>
		<h2><c:out value="${sessionScope.eventList.eventName}"></c:out></h2>
		<h3><c:out value="${sessionScope.eventList.artistName}"></c:out></h3>
		<hr>
		<p><c:out value="${sessionScope.eventList.eventMemo}"></c:out></p>
		<hr>
		<form action="goodsregistration" method="get">
			<h3>グッズリスト</h3>
			<p>
				<input type="hidden" name="btn" value="goodsregist">
				<input type="submit" value="グッズ登録">
			</p>
		</form>
		<c:if test="${not empty requestScope.message}">
			<p>${requestScope.message}</p>
		</c:if>
		<c:if test="${not empty requestScope.errorMessage}">
			<p>${requestScope.errorMessage}</p>
		</c:if>
		<form action="event" method="post">
		<c:if test="${not empty requestScope.goodsList && empty requestScope.goodsListModel}">
			<table border="1">
				<tr>
					<th>グッズ名</th>
					<th>価格</th>
					<th>個数</th>
					<th>小計</th>
					<th>備考</th>
					<th></th>
				</tr>
				<c:forEach var="goods" items="${requestScope.goodsList}">
					<tr>
						<td><c:out value="${goods.goodsName}" /></td>
						<td><c:out value="${goods.goodsPrice}" /></td>
						<td>
							<select name="num">
								<c:forEach var="list" items="${goods.numberList}">
									<option value="${list.label}"><c:out value="${list.data}" /></option>
								</c:forEach>
							</select>
							<input type="hidden" name="goodsId" value="${goods.goodsId}">
						</td>
						<td><c:out value="${requestScope.subTotal}" /></td>
						<td><c:out value="${goods.goodsMemo}" /></td>
						<td>
						</td>
					</tr>
				</c:forEach>
			</table>
			<p>
				<input type="hidden" name="btn" value="calc">
				<input type="submit" value="計算">
			</p>
		</c:if>
		<c:if test="${not empty requestScope.goodsList && not empty requestScope.goodsListModel}">
			<table border="1">
				<tr>
					<th>グッズ名</th>
					<th>価格</th>
					<th>個数</th>
					<th>小計</th>
					<th>備考</th>
					<th></th>
				</tr>
				<c:forEach var="goods" items="${requestScope.goodsListModel}">
					<tr>
						<td><c:out value="${goods.goodsName}" /></td>
						<td><c:out value="${goods.goodsPrice}" /></td>
						<td><select name="num">
								<c:forEach var="list" items="${goods.numberList}">
									<option value="${list.label}"
										 <c:if test="${list.label == goods.num}"> selected="selected"
										 </c:if>
									><c:out value="${list.data}" /></option>
								</c:forEach>
							</select>
							<input type="hidden" name="goodsId" value="${goods.goodsId}">
						</td>
						<td><c:out value="${goods.subTotal}" /></td>
						<td><c:out value="${goods.goodsMemo}" /></td>
						<td>
						</td>
					</tr>
				</c:forEach>
					<tr>
						<td></td>
						<td>合計</td>
						<td><c:out value="${requestScope.totalNum}" />個</td>
						<td><c:out value="${requestScope.total}" />円</td>
						<td></td>
						<td>
						</td>
					</tr>
			</table>

			<p>
				<input type="hidden" name="eventNo" value="${sessionScope.eventList.eventId}">
				<input type="hidden" name="btn" value="calc">
				<input type="submit" value="再計算">
			</p>
		</c:if>
		</form>
		<p><a href="top">トップへ戻る</a>
	</main>
</body>
</html>