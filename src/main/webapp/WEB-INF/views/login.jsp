<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>로그인 페이지입니다.</h1>
<h2>아이디와 비밀번호를 입력하세요</h2>

<form action="/login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="user_id" required><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="user_pw" required><br><br>
    <input type="submit" value="로그인">
</form>
<a href="/">홈화면으로 돌아가기</a>
<a href="/register">회원가입</a>
</body>
</html>

