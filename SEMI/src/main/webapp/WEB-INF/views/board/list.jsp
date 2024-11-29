<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/common/body.css">
<link rel="stylesheet" href="/css/board/list.css">
<script defer src="/js/board/list.js"></script>
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
        <h1>게시판</h1>
        <hr>
        <!-- <c:forEach var="vo" items="${boardList}">
            <div>${vo.no}</div>
            <div>${vo.writerNo}</div>
            <div>${vo.title}</div>
            <div>${vo.createDate}</div>
        </c:forEach> -->
        <div class="search-area">
            <form action="/board/list?pno=1" onsubmit="return submitSearchForm();">
                <select name="searchType" onchange="handleSearchType(this);">
                    <option value="title">제목</option>
                    <option value="category">카테고리</option>
                </select>
                <input type="text" name="searchValue" placeholder="검색할 제목">
                <select name="searchValue" disabled>
                    <c:forEach items="${cateVoList}" var="cateVo">
                        <option value="${cateVo.no}">${cateVo.name}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="검색">
            </form>
        </div>
        <div class="table-area">
            <table border="1" style="text-align: center;">
                <thead>
                    <tr>
                        <th>카테고리</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>작성일</th>
                    </tr>
                </thead>
                <tbody>
                    <td>로딩 중...</td>
                </tbody>
            </table>
        </div>
        <div class="main-bottom-area">
            <button onclick="location.href='/board/write'">게시글 작성</button>
        </div>
        <div class="page-area">
            
        </div>

    </main>

    <!-- 오른쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>

</body>
</html>