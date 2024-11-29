<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/common/body.css">
<script defer src="/js/board/write.js"></script>
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
        <h1>게시글 작성</h1>
        <br>
        <form action="/board/write" method="post" enctype="multipart/form-data">
            <input type="text" name="title" placeholder="제목">
            <br>
            <textarea name="content" placeholder="내용"></textarea>
            <br>
            <label>카테고리 : </label>
            <!-- <select id="cate-select" name="categoryNo">

            </select> -->
            <select name="categoryNo" >
                <c:forEach var="x" items="${categoryList}">
                    <option value="${x.no}">${x.name}</option>
                </c:forEach>
            </select>
            <hr>
            <input type="file" name="f" multiple>
            <div class="preview-area">

            </div>
            <hr>
            <input type="submit" value="작성하기">
        </form>
    </main>

    <!-- 오른쪽 광고 영역 -->
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>

</body>
</html>