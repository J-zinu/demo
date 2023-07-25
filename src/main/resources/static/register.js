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