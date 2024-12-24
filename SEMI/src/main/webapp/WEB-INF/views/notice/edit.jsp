<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SEMI</title>
<link rel="stylesheet" href="/css/common/body.css">

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
        <h1>공지사항 수정</h1>
        <hr>
        <form action="/notice/edit" method="POST">
            <input type="hidden" name="no" value="${vo.no}">
            <label>제목 :</label>
            <input type="text" name="title" value="${vo.title}">
            <br>
            <label>내용 : </label>
            <textarea name="content">${vo.content}</textarea>
            <br>
            <input type="submit" value="수정">
        </form>
        
    </main>

    <!-- 오른쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>

</body>
</html>