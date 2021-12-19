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
		<h1>登録完了</h1>
		<p>${sessionScope.message}</p>
		<c:if test="${sessionScope.btn == 'goods'}">
			<form action="goodsregistration" method="post">
				<input type="hidden" name="btn" value="goodsregist">
				<p><input type="submit" value="続けて登録" name="goodsregist" class="btn1"></p>
			</form>
			<form action="event" method="post">
				<input type="hidden" name="btn" value="select">
				<input type="hidden" name="eventNo" value="${sessionScope.eventId}">
				<p><input type="submit" value="イベントトップへ戻る" class="btn1"></p>
			</form>
		</c:if>
		<p><a href="${backAddress}"><c:out value="${back}" />へ戻る</a>&nbsp;/&nbsp;
		<a href="top">トップへ戻る</a></p>
	</main>
</body>
</html>