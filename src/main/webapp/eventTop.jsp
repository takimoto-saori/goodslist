<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>グッズリスト</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>
		<hr>
		<h2><c:out value="${sessionScope.eventList.eventName}"></c:out></h2>
		<h3><c:out value="${sessionScope.eventList.artistName}"></c:out></h3>
		<p><c:out value="${sessionScope.eventList.eventMemo}"></c:out></p>
		<hr>
		<form action="goodsregistration" method="post">
			<p><input type="hidden" name="btn">
				<input type="submit" value="グッズ登録" class="btn" name="goodsregist">
				<input type="submit" value="グッズ削除" class="btn" name="goodsdel"></p>
		</form>
	<!--	<table id="table">
			<tr>
				<td>
					<form action="goodsregistration" method="post">
						<input type="hidden" name="btn" value="goodsregist">
						<input type="submit" value="グッズ登録" class="btn">
					</form>
				</td>
				<td>
					<form action="goodsregistration" method="post">
						<input type="hidden" name="btn" value="goodsdel">
						<input type="submit" value="グッズ削除" class="btn">
					</form>
				</td>
			</tr>
		</table> -->
		<h3>グッズリスト</h3>
		<c:if test="${not empty requestScope.message}">
			<p class="message">${requestScope.message}</p>
		</c:if>
		<form action="event" method="post">
		<c:if test="${not empty requestScope.goodsListModel}">
			<table id="table" border="1">
				<tr>
					<th>グッズ名</th>
					<th>価格</th>
					<th>個数</th>
					<th>小計</th>
					<th>備考</th>
				</tr>
				<c:forEach var="goods" items="${requestScope.goodsListModel}">
					<tr>
						<td><c:out value="${goods.goodsName}" /></td>
						<td align="right">\<c:out value="${goods.goodsPrice}" /></td>
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
						<td align="right"><c:choose>
							<c:when test="${requestScope.param}">\<c:out value="${goods.subTotal}" /></c:when>
							<c:otherwise>-&nbsp;&nbsp;</c:otherwise>
						</c:choose></td>
						<td><c:out value="${goods.goodsMemo}" /></td>
					</tr>
				</c:forEach>
				<c:if test="${requestScope.param}">
					<tr>
						<td class="total" colspan="2" align="right">合計</td>
						<td class="total"><c:out value="${requestScope.totalNum}" />個</td>
						<td class="total"colspan="2" align="left"><c:out value="${requestScope.total}" />円</td>
					</tr>
				</c:if>
			</table>

			<p>
				<input type="hidden" name="btn" value="calc">
				<input type="submit" value="${requestScope.btn}" class="btn1">
			</p>
		</c:if>
		</form>
		<p>
			<a href="search">イベント検索へ戻る</a>&nbsp;/&nbsp;
			<a href="top">トップへ戻る</a>
		</p>
	</main>
</body>
</html>