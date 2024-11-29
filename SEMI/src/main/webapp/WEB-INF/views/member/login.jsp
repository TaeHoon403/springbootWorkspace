<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/common/body.css">
<script defer src="/js/member/login.js"></script>

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
        <h1>로그인</h1>
        <hr>
        <form action="/member/login" method="post">
            <input type="text" name="id" placeholder="아이디">
            <br>
            <input type="password" name="pwd" placeholder="비밀번호">
            <br>
            <input type="submit" value="로그인">
        </form>
    </main>

    <!-- 오른쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>

</body>
</html>