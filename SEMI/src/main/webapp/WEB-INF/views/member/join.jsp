<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/common/body.css">
<script defer src="/js/member/join.js"></script>

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
        <h1>회원가입</h1>
        <hr>
        <form action="/member/join" method="post" enctype="multipart/form-data">
            <input type="text" name="id" placeholder="아이디" oninput="disableSubmitBtn()">
            <input type="button" class="kh-btn-blue" value="중복검사" onclick="checkDupId()">
            <br>
            <input type="password" name="pwd" placeholder="비밀번호">
            <br>
            <input type="text" name="nick" placeholder="닉네임">
            <br>
            <label for="">프로필 사진 : </label>
            <input type="file" name="f" accept=".png,.jpg,.jpeg,.svg">
            <br>
            <input type="submit" value="회원가입" disabled="true">
        </form>
    </main>

    <!-- 오른쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>

</body>
</html>