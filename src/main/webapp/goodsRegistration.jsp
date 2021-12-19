<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>グッズリスト</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>
		<h1>グッズ登録ページ</h1>
		<hr>
		<h2><c:out value="${sessionScope.eventList.eventName}"></c:out></h2>
		<h3><c:out value="${sessionScope.eventList.artistName}"></c:out></h3>
		<p><c:out value="${sessionScope.eventList.eventMemo}"></c:out></p>
		<hr>
		<p class="message"><c:out value="${requestScope.message}" /></p>
		<p class="errors"><c:out value="${requestScope.errorMessage}" /></p>
		<form action="goodsregistration" method="post">
			<table id="table">
				<tr>
					<td>グッズ名&nbsp;<span class="required">必須</span></td>
					<td><input type="text" name="goodsName" value="${requestScope.goodsName}"></td>
					<td class="errors"><c:out value="${requestScope.errorMessage1}" /></td>
				</tr>
				<tr>
					<td>価格&nbsp;<span class="required">必須</span></td>
					<td><input type="text" name="goodsPrice" value="${requestScope.goodsPrice}"></td>
					<td class="errors"><c:out value="${requestScope.errorMessage2}" /><c:if test="${requestScope.checkPrice}"><c:out value="${requestScope.errorMessage3}" /></c:if></td>
				</tr>
				<tr>
					<td>購入制限</td>
					<td><input type="text" name="maxNum" value="${requestScope.maxNum}">個まで<br>
					※未入力の場合10個で設定されます</td>
					<td class="errors"><c:if test="${requestScope.checkNum}"><c:out value="${requestScope.errorMessage3}" /></c:if></td>
				</tr>
				<tr>
					<td>備考</td>
					<td><textarea cols="22" rows="5" name="goodsdMemo">
					<c:out value="${requestScope.goodsMemo}" />
					</textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="hidden" name="btn" value="regist">
					<input type="submit" value="登録" class="btn1">
					</td>
				</tr>
			</table>
		</form>
		<form action="event" method="post">
			<p><input type="hidden" name="btn" value="select">
			<input type="hidden" name="eventNo" value="${sessionScope.eventList.eventId}">
			<input type="submit" value="イベントトップへ戻る" class="btn1"></p>
		</form>
		<br>
		<p><a href="top">トップへ戻る</a></p>
	</main>
</body>
</html>