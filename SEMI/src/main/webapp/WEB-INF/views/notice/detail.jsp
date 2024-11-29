<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/common/body.css">
<link rel="stylesheet" href="/css/notice/list.css">
<script defer src="/js/notice/list.js"></script>
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
        <h1>공지사항 상세조회</h1>
        <hr>
        <h3>${vo.title}</h3>
        <span>작성자 : ${vo.writerNick}</span>
        <br>
        <span>조회수 : ${vo.hit}</span>
        <br>
        <span>작성일 : ${vo.createDate}</span>
        <br>
        <p>내용 : ${vo.content}</p>

    </main>

    <!-- 오른쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>

</body>
</html>