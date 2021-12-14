<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>EventSearch</title>
</head>
<body>
	<header><h1>イベント検索ページ</h1></header>
	<main>
		<form action="search" method="post">
			<p>検索したいイベント名もしくはアーティスト名を入力してください。</p>
			<p>
				<input type="text" name="paramEvent">
				<input type="hidden" name="btn" value="eventSearch">
				<input type="submit" value="イベント検索">
			</p>
		</form>
		<c:if test="${not empty requestScope.message}">
			<p>${requestScope.message}</p>
		</c:if>
		<c:if test="${not empty requestScope.errorMessage}">
			<p>${requestScope.errorMessage}</p>
		</c:if>
		<c:if test="${not empty requestScope.eventList}">
			<h2>検索結果</h2>
			<table border="1">
				<tr>
					<th>イベント名</th>
					<th>アーティスト名</th>
					<th>備考</th>
					<th></th>
				</tr>
				<c:forEach var="event" items="${requestScope.eventList}">
					<tr>
						<td><c:out value="${event.eventName}" /></td>
						<td><c:out value="${event.artistName}" /></td>
						<td><c:out value="${event.eventMemo}" /></td>
						<td>
							<form action="event" method="get">
								<input type="hidden" name="eventNo" value="${event.eventId}">
								<input type="submit" value="選択">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<p><a href="top">トップへ戻る</a>
	</main>
</body>
</html>