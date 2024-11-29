<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/common/body.css">
<link rel="stylesheet" href="/css/member/quit.css">

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
        <h1>회원 탈퇴</h1>
        <hr>
        <form id="quit-form" action="/member/quit" method="post" onsubmit="return confirm('정말 탈퇴하시겠습니까?');">
            <label>현재 비밀번호</label>
            <input type="password" name="pwd" placeholder="비밀번호">
            <input type="submit" value="탈퇴하기">
        </form>
    </main>

    <!-- 오른쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>

</body>
</html>