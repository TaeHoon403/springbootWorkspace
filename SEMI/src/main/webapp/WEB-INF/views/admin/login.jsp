<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SEMI - Admin</title>
</head>
<body>
    <h1>관리자 로그인 페이지</h1>
    <hr>
    <form action="/admin/login" method="post">
        <input type="text" name="id" placeholder="아이디">
        <br>
        <input type="password" name="pwd" placeholder="비밀번호">
        <br>
        <input type="submit" value="로그인">
    </form>
</body>
</html>