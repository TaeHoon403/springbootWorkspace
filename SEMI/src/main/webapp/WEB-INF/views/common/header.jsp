<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<link rel="stylesheet" href="/css/common/header.css">
<script defer src="/js/common/header.js"></script>
<script>
    <!--
    <%if(session.getAttribute("alertMsg") != null) {%>
        alert('${sessionScope.alertMsg}'); 
        <%session.removeAttribute("alertMsg");%>
    <%}%>
    -->

    <c:if test="${not empty alertMsg}">
        alert('${alertMsg}');
        <c:remove var="alertMsg" scope="session"/>
    </c:if>
</script>

<header>
    <div>빈칸</div>
    <div class="logo"></div>
    <div>
        <c:if test="${loginInfo != null}">
            <img src="http://127.0.0.1:8888/img/profile/${loginInfo.profile}" width="100px" height="100px">
            <br>
            <a href="/member/logout">로그아웃</a>
        </c:if>
        <c:if test="${loginAdminVo != null}">
            <a href="/admin/logout">관리자 로그아웃</a>
        </c:if>
        <c:if test="${loginAdminVo == null && loginInfo == null}">
            <a href="/member/join">회원가입</a>
            <br>
            <a href="/member/login">로그인</a>
            <br>
            <a href="/admin/login">관리자 로그인</a>
        </c:if>
    </div>
</header>