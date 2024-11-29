<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/common/body.css">
<script defer src="/js/member/mypage.js"></script>

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
        <h1>MyPage</h1>
        <hr>
        <form id="edit-form" action="/member/edit" method="post">
            id   : <input type="text" name="id" value="${loginInfo.id}" disabled>
            <br>
            pw   : <input type="password" name="pwd" placeholder="비밀번호" disabled>
            <br>
            nick : <input type="text" name="nick" value="${loginInfo.nick}" disabled>
            <br>
            <input type="button" value="수정하기">
            <input type="button" value="탈퇴하기" onclick="location.href='/member/quit'">
        </form>
    </main>

    <!-- 오른쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>

</body>
</html>