<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/common/body.css">
<link rel="stylesheet" href="/css/notice/detail.css">
<script defer src="/js/notice/detail.js"></script>
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
        <hr>
        <c:if test="${vo.writerNo == loginAdminVo.no}">
            <button onclick="location.href='/notice/edit?no=${vo.no}'">수정</button>
            <button>삭제</button>
            <hr>
        </c:if>
        <div id="reply-area" noticeNo="${vo.no}">
            <c:if test="${loginInfo != null}">
                <div id="reply-write-area">
                    <input type="text" name="content" placeholder="댓글 내용">
                    <!-- <button onclick="writeReply(${vo.no});">작성하기</button> -->
                    <button onclick="writeReply();">작성하기</button>
                </div>
            </c:if>
            <div id="reply-list-area">

            </div>
        </div>
    </main>

    <!-- 오른쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>

</body>
</html>