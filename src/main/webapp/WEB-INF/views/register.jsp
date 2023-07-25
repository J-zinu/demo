<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous">
        // jquery 라이브러리를 사용하기 위해 추가
    </script>
    <script src="register.js"></script>
</head>
<body>
<h1>회원가입 페이지입니다.</h1>
<h2>아이디와 비밀번호를 입력하여 회원 등록을 진행하십시오.</h2>
<form action="/register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="user_id" required onblur="id_Check()">
    <span id="checkResult"></span><br><br> <!-- Add the checkResult element here -->
    <label for="password">Password:</label>
    <input type="password" id="password" name="user_pw" required><br><br>
    <input type="submit" value="회원가입">
</form>
<a href="/">홈화면으로 돌아가기</a>
<a href="/login">로그인</a>
</body>

</html>
