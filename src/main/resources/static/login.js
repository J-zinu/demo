$(document).ready(function () {
    $('#login').click(function (event) {
        event.preventDefault();

        var form = $(this).parents('form');
        var url = form.attr('action');
        var data = form.serialize();

        console.log("Sending login request to the server with the following data: " + data);

        $.ajax({
            type: 'POST',
            url: url,
            data: data,
            success: function (response) {
                console.log("Received response from the server: ", response);

                if (response.status === 'success') {
                    console.log("Login successful, redirecting to the task management list");
                    alert(response.message);
                    window.location.href = '/todoList';
                } else {
                    console.log("Login failed, returning to login page");
                    alert(response.message);
                }
            },
            error: function (error) {
                console.log("An error occurred while trying to login: ", error);
                alert('An error occurred while trying to login.');
            }
        });
    });
});
