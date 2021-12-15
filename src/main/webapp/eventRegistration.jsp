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
		<h1>イベント登録ページ</h1>
		<p class="message"><c:out value="${message}" /></p>
		<p class="errors"><c:out value="${errorMessage}" /></p>
		<form action="registration" method="post">
			<table id="table">
				<tr>
					<td>イベント名</td>
					<td><input type="text" name="eventName"></td>
				</tr>
				<tr>
					<td>アーティスト名</td>
					<td><input type="text" name="artistName"></td>
				</tr>
				<tr>
					<td>備考</td>
					<td><textarea cols="22" rows="5" name="eventMemo">
					</textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="hidden" name="btn" value="eventregist">
					<input type="submit" value="登録">
					</td>
				</tr>
			</table>
		</form>
	</main>
</body>
</html>