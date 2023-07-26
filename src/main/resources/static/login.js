// const login = () => {
//     const username = document.getElementById("username").value;
//     const password = document.getElementById("password").value;
//
//     $.ajax({
//         type: "POST",
//         url: "/login",
//         data: {
//             user_id: username,
//             user_pw: password
//         },
//         success: function(res) {
//             console.log("로그인 요청 성공!", res);
//             if(res.status === "success") {
//                 // 로그인 성공 처리
//                 window.location.href = "/todoList";
//             } else {
//                 // 로그인 실패 처리
//                 alert("로그인 실패");
//             }
//         },
//         error: function(err) {
//             console.log("로그인 요청 실패!", err);
//         }
//     });
// }
// //위의 소스코드는 7/25 5:46 추가된부분입니다.