// Javascript
$(document).ready(function() {
    $('#updateForm').on('submit', function(e) {
        e.preventDefault();

        var new_password = $('#new_password').val();
        var user_id = $('#user_id').val();

        $.ajax({
            url: "/manage/update",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                user_id: user_id,
                user_pw: new_password
            }),
            success: function() {
                alert("비밀번호가 성공적으로 변경되었습니다!");
                window.location.reload();
            },
            error: function() {
                alert("비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
            }
        });
    });

    $('#deleteAccount').on('click', function() {
        if(confirm("정말로 계정을 삭제하시겠습니까?")) {
            var user_id = $('#user_id').val();
            alert(user_id);
            $.ajax({
                url: "/manage/delete",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify({
                    user_id: user_id
                }),
                success: function() {
                    alert("계정이 성공적으로 삭제되었습니다!");
                    window.location.href = "/login";
                },
                error: function() {
                    alert("계정 삭제에 실패했습니다. 다시 시도해주세요.");
                }
            });
        }
    });
});
