<!-- HTML -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Page</title>
    <link rel="stylesheet" type="text/css" href="manage.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="manage.js"></script>
</head>
<body>
<h2>${sessionScope.user_id}님의 마이페이지</h2>

<form id="updateForm" method="post">
    <label for="new_password">새 비밀번호: </label>
    <input type="password" id="new_password" name="new_password" required />
    <input type="hidden" id="user_id" name="user_id" value="${sessionScope.user_id}" />
    <input type="submit" value="비밀번호 변경" />
</form>

<button id="deleteAccount">회원 탈퇴</button>
</body>
</html>
