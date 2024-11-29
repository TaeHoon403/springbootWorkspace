<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/common/body.css">

<title>SEMI</title>

</head>
<body>

    <!-- 왼쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideLeft.jsp" %>

    <!-- 메인 영역 -->
    <main>
        <!-- 헤더 영역 -->
        <%@ include file="/WEB-INF/views/common/header.jsp" %>

        <!-- 네비게이터 영역 -->
        <%@ include file="/WEB-INF/views/common/nav.jsp" %>

        <!-- 본문 영역 -->
        <h1>공지사항 작성</h1>
        <hr>
        <form action="/notice/write" method="post">
            <input type="text" name="title" placeholder="제목">
            <br>
            <textarea name="content" placeholder="내용"></textarea>
            <br>
            <input type="submit" value="작성하기">
        </form>
    </main>

    <!-- 오른쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>

</body>
</html>