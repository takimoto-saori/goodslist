<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Top</title>
</head>
<body>
	<header><h1>イベント検索・登録</h1></header>
	<main>
		<form action="search" method="get">
			<input type="submit" value="イベント検索">
		</form>
		<br>
		<form action="registration" method="get">
			<input type="submit" value="イベント登録">
		</form>
	</main>
</body>
</html>