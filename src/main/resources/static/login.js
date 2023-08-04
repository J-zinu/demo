$(document).ready(function () {
    $('#login').click(function (event) {
        event.preventDefault();

        var form = $(this).parents('form');
        var url = form.attr('action');
        var data = form.serialize(); //직렬화를 통해서 데이터를 가져옴

        console.log("login.js 서버에 로그인 요청을 보내는 중, 다음 데이터를 사용합니다: " + data);

        $.ajax({
            type: 'POST',
            url: url,
            data: data,
            success: function (response) {
                console.log("login.js 서버에서 응답을 받았습니다: ", response);
                if (response.status === 'success') {
                    console.log("login.js 로그인 성공, 할 일 목록 페이지로 이동합니다");
                    alert(response.message);
                    window.location.href = '/todoList';

                } else {
                    console.log("login.js 로그인 실패, 로그인 페이지로 돌아갑니다");
                    alert(response.message);
                }
            },
            error: function (error) {
                console.log("login.js 로그인 시도 중 오류가 발생했습니다: ", error);
                alert('로그인 시도 중 오류가 발생했습니다.');
            }
        });
    });

});
