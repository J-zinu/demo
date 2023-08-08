$(document).ready(function() {
    $.get("/error", function(data) {
        alert(data.errorMessage); // 오류 메시지를 알림창으로 표시합니다.
    });
});
