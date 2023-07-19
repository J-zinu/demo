<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous">
        // jquery 라이브러리를 사용하기 위해 추가
    </script>
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

<script>
    //아이디 입력값을 가져오고,
    //입력값을 서버로 전송하고 이메일이 있는지 체크한 후
    //사용가능 여부를 아이디 입력창 아래에 표시
    const id_Check = () => {
        const input_id = document.getElementById("username").value;
        const checkResult = document.getElementById("checkResult");
        console.log("입력한아이디", input_id);
        $.ajax({
            //요청방식: post,url: "id_check", 데이터: {user_id: input_id}
            type: "post",
            url: "/member/id_check", // Corrected URL
            data: {
                user_id: input_id
            },
            success: function (res) {
                console.log("요청성공!", res);
                if (res === "success") { // Check for "success" instead of "true"
                    console.log("사용가능한 아이디입니다.");
                    checkResult.style.color = "green";
                    checkResult.innerHTML = "사용가능한 아이디입니다.";
                } else {
                    console.log("이미 사용중인 아이디입니다.");
                    checkResult.style.color = "red";
                    checkResult.innerHTML = "이미 사용중인 아이디입니다.";
                }
            },
            error: function (err) {
                console.log("요청실패!", err);
            }
        })
    }
</script>
</html>
