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
		<h1>エラーページ</h1>
		<p class="errors">${requestScope.errorMessage}</p>
		<p><a href="${requestScope.backAddress}">戻る</a>&nbsp;/&nbsp;
		<a href="top">トップへ戻る</a></p>
	</main>
</body>
</html>