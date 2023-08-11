document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("registerForm").addEventListener("submit", register);
});


const id_Check = () => {
    const input_id = document.getElementById("username").value;
    const checkResult = document.getElementById("checkResult");
    console.log("입력한아이디", input_id);
    $.ajax({
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

const register = (event) => {
    event.preventDefault();
    const user_id = document.getElementById("username").value;
    const user_pw = document.getElementById("password").value;

    $.ajax({
        type:"POST",
        url: "/register",
        data: {
            user_id: user_id,
            user_pw: user_pw
        },
        success: function (response) {
            if (response.status === "success") {
                alert("회원가입에 성공하였습니다." + "\n" + "로그인 페이지로 이동합니다." + "\n" + "아이디 : " + user_id + "\n" + "비밀번호 : " + user_pw);
                window.location.href = "/login";  // 페이지를 로그인 페이지로 리다이렉트
            }
            else{
                alert("회원가입에 실패하였습니다." + "\n" + "이미 사용중인 아이디이므로" + "\n" + "회원가입을 다시 시도해주세요.");
            }
        },
        error: function (err){
            console.log("요청실패!", err);

        }
    })
}



//
// const id_Check = () => {
//     const input_id = document.getElementById("username").value;
//     const checkResult = document.getElementById("checkResult");
//     console.log("입력한아이디", input_id);
//     $.ajax({
//         type: "post",
//         url: "/member/id_check", // Corrected URL
//         data: {
//             user_id: input_id
//         },
//         success: function (res) {
//             console.log("요청성공!", res);
//             if (res === "success") { // Check for "success" instead of "true"
//                 console.log("사용가능한 아이디입니다.");
//                 checkResult.style.color = "green";
//                 checkResult.innerHTML = "사용가능한 아이디입니다.";
//             } else {
//                 console.log("이미 사용중인 아이디입니다.");
//                 checkResult.style.color = "red";
//                 checkResult.innerHTML = "이미 사용중인 아이디입니다.";
//             }
//         },
//         error: function (err) {
//             console.log("요청실패!", err);
//         }
//     })
// }