<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SEMI</title>
<link rel="stylesheet" href="/css/common/body.css">
<script defer src="/js/board/edit.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/asideLeft.jsp" %>
    <main>
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <%@ include file="/WEB-INF/views/common/nav.jsp" %>
        <h1>게시글 수정</h1>
        <hr>
        <form action="/board/edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="no" value="${vo.no}">
            <input type="text" name="title" placeholder="제목" value="${vo.title}">
            <br>
            <textarea name="content" placeholder="내용">
                ${vo.content}
            </textarea>
            <br>
            <label>카테고리 : </label>
            <select name="categoryNo">
                <c:forEach items="${cateVoList}" var="cateVo">
                    <c:if test="${vo.categoryNo != cateVo.no}">
                        <option value="${cateVo.no}">${cateVo.name}</option>
                    </c:if>
                    <c:if test="${vo.categoryNo == cateVo.no}">
                        <option selected value="${cateVo.no}">${cateVo.name}</option>
                    </c:if>
                </c:forEach>
            </select>
            <br>
            <div class="board-img-area">
                <c:forEach items="${attachmentVoList}" var="attachVo">
                    <img
                        src="/img/board/attachment/${attachVo.changeName}"
                        alt="${attachVo.originName}"
                        width="100px"
                        height="100px"
                        onclick="delAttach('${attachVo.no}' , 'img/board/attachment/${attachVo.changeName}' , this);"
                    >
                </c:forEach>
            </div>
            <br>

            <hr>
            <input type="file" name="f" multiple>
            <div class="preview-area">

            </div>
            <hr>

            <input type="submit" value="수정하기">
        </form>
    </main>
    <%@ include file="/WEB-INF/views/common/asideRight.jsp" %>
</body>
</html>