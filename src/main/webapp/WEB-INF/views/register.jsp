<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous">
        // jquery 라이브러리를 사용하기 위해 추가
    </script>
    <script src="register.js"></script>
    <link rel="stylesheet" type="text/css" href="register.css">
</head>
<body>
<h1>회원가입 페이지입니다.</h1>
<h2>아이디와 비밀번호를 입력하여 회원 등록을 진행하십시오.</h2>
<form action="/register" method="post">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="user_id" placeholder="사용할 아이디 입력" required onblur="id_Check()"><br>
        <span id="checkResult"></span><br><br> <!-- Add the checkResult element here -->
        <label for="password">Password:</label>
        <input type="password" id="password" name="user_pw" required><br><br>
        <input type="submit" value="회원가입">
        <br>
        <p>이미 회원이신가요?</p>
        <a href="/login">로그인 하러가기</a>

    </div>
</form>

</body>

</html>
