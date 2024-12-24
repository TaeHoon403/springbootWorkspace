<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>List</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>

</head>
<body>
    <h1>도서 상세 조회</h1>
    <hr>
    <table border="1">
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작가</th>
                <th>출판일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="x" items="${vo}">
                <tr>
                    <td>${x.no}</td>
                    <td><a href="/book/detail?no=${x.no}">${x.title}</a></td>
                    <td>${x.writer}</td>
                    <td>${x.publishedDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <hr>
    <button onclick="location.href='/book/insert'">신규 도서 등록</button>
</body>
</html>

