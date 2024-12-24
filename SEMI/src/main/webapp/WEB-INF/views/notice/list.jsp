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
        <h1>공지사항</h1>
        <hr>
        <div class="main-bottom-area">
            <c:if test="${loginAdminVo != null}">
                <button onclick="location.href='/notice/write'">공지사항 작성</button>
                <button onclick="deleteNotice();">삭제</button>
            </c:if>
        </div>
        <div class="search-area">
            <form action="/notice/list">
                <input type="text" name="searchValue" value="${searchValue}" placeholder="검색할 제목을 입력하세요">
                <input type="submit" value="검색">
            </form>
        </div>
        <div class="table-area">
            <table border="1" style="text-align: center;">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>작성일</th>
                        <c:if test="${loginAdminVo != null}">
                            <th><input type="checkbox" onclick="handleCheckBox(this);"></th>
                        </c:if>
                    </tr>
                </thead>
                <tbody onclick="">
                    <c:forEach items="${voList}" var="vo">
                        <tr>
                            <td>${vo.no}</td>
                            <td>${vo.title}</td>
                            <td>${vo.writerNick}</td>
                            <td>${vo.hit}</td>
                            <td>${vo.createDate}</td>
                            <c:if test="${loginAdminVo != null}">
                                <td class="checkbox-td"><input type="checkbox" name="del"></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="page-area">
            <c:if test="${pvo.startPage > 1}">
                <a href="/notice/list?pno=${pvo.startPage-1}&searchValue=${searchValue}"><</a>
            </c:if>
            <c:forEach var="i" begin="${pvo.startPage}" end="${pvo.endPage}" step="1">
                <a href="/notice/list?pno=${i}&searchValue=${searchValue}">${i}</a>
            </c:forEach>            
            <c:if test="${pvo.endPage != pvo.maxPage}">
                <a href="/notice/list?pno=${pvo.endPage+1}&searchValue=${searchValue}">></a>
            </c:if>
        </div>

    </main>

    <!-- 오른쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>

</body>
</html>