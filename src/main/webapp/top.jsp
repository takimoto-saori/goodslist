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
		<h1>イベント検索・登録ページ</h1>
		<form action="search" method="get">
			<p><input type="submit" value="イベント検索" class="btn"><p>
		</form>
		<form action="registration" method="get">
			<p><input type="submit" value="イベント登録" class="btn"></p>
		</form>
	</main>
</body>
</html>