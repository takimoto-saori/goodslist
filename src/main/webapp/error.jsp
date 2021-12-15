<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>error</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>
		<h1>エラーページ</h1>
		<p class="error">${requestScope.errorMessage}</p>
		<p><a href="${backAddress}">戻る</a>&nbsp;/&nbsp;
		<a href="top">トップへ戻る</a></p>
	</main>
</body>
</html>