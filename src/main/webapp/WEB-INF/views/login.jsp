<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="login.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script> src="login.js"</script>

</head>
<body>
<form action="/login" method="post">

    <div>
        <h1>로그인 페이지입니다.</h1>
        <br>
        <h2>아이디와 비밀번호를 입력하세요</h2>
        <br>

        <label for="username"></label>
        <input type="text" id="username" name="user_id" placeholder="아이디 입력"><br><br>
        <label for="password"> </label>
        <input type="password" id="password" name="user_pw" placeholder="비밀번호"><br><br>
        <input type="submit" value="로그인">
        <br>
        <p>아직 회원이 아니신가요?</p>
        <a href="/register">회원가입 하러가기</a>
    </div>
</form>

</body>
</html>

