<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Detail</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>

</head>
<body>
    <h1>도서 상세 조회</h1>
    <hr>
    <div>제목 : ${vo.title}</div>
    <div>작가 : ${vo.writer}</div>
    <div>가격 : ${vo.price}</div>
    <div>출판일 : ${vo.publishedDate}</div>
    <hr>
    <button onclick="location.href='/book/list'">목록으로</button>
    <button onclick="location.href=`/book/delete?no=${vo.no}`">삭제</button>
</body>
</html>

