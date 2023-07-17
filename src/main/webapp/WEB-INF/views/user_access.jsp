<%--
  Created by IntelliJ IDEA.
  User: jinwoojeong
  Date: 2023/07/17
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 성공!!</title>
</head>
<body>
<h1>환영합니다!</h1>
<div th:text=$"{user_id}"></div>
<a href = "test.jsp">메인으로 돌아가기</a>
<a href = "/logout.jsp">로그아웃</a>
</body>
</html>
