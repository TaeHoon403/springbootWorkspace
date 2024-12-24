<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Insert</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>

</head>
<body>
    <h1>신규 도서 등록</h1>
    <hr>
    <form action="/book/insert" method="POST">
        <input type="text" name="title" placeholder="제목">
        <br>
        <input type="text" name="writer" placeholder="작가">
        <br>
        <input type="text" name="price" placeholder="가격">
        <br>
        <input type="text" name="publishedDate" placeholder="출판일">
        <br>
        <input type="submit" value="등록">
    </form>
    <br>
    <button onclick="location.href='/book/list'">목록으로</button>
</body>
</html>

