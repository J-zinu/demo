$(document).ready(function () {
    viewContent();

    $("#contentForm").submit(function(event) {
        console.log("submit 정상 요청됨");
        var formData = $(this).serialize();
        console.log("formData : "+formData);

        event.preventDefault();

        viewCreateContent(formData);

        $("#todo").val("").focus();
    });

    $(document).on('click', '#delBtn', function() {
        console.log("delBtn 정상작동");
        var numData = $(this).data("todo-num");
        console.log("numData : "+numData);
        viewDeleteContent(numData);
    });

    $(document).on('click', '#searchBtn', function() {
        console.log("searchBtn 정상작동");
        var searchData = $('#todo_search').val();
        console.log("todo_search : "+ searchData);
        viewSearchContent(searchData);
    });


})

// todoList 목록 보여주기
function viewTodoListContent(data) {
    var tbody = $('#todolistTable tbody');
    tbody.empty();

    data.forEach(function(item) {
        var row = $('<tr>');
        var todoCol = $('<td>').html('&middot; ' + item.todo);
        var functionCol = $('<td>').append(
            $('<button>').attr({
                'type': 'button',
                'id': 'delBtn',
                'data-todo-num': item.todo_num
            }).text("삭제")
        );

        row.append(todoCol);
        row.append(functionCol);
        tbody.append(row);
    });
}

// Create - Post
function viewCreateContent(formData){

    $.ajax({
        url: "/todoList/create",
        method: "POST",
        dataType: "JSON",
        data: formData,
        success: function (data) {
            console.log("submit 성공!!");
            viewTodoListContent(data);
        },
        error: function (error) {
            console.error(error);
        }
    });
}

// Read - Get
function viewContent(){
    $.ajax({
        url: "/todoList/read",
        method: "GET",
        dataType:"JSON",
        success: function (data) {
            viewTodoListContent(data);
        },
        error: function (error) {
            console.error(error);
        }
    });
}
// Update
// Delete - Post
function viewDeleteContent(numData){
    $.ajax({
        url: "/todoList/delete",
        method: "DELETE",
        dataType: "JSON",
        data: {todo_num: numData},
        success: function (data) {
            console.log("submit 성공!!");
            viewTodoListContent(data);
        },
        error: function (error) {
            console.error(error);
        }
    });
}

function viewSearchContent(searchData){
    $.ajax({
        url: "/todoList/search",
        method: "POST",
        dataType: "JSON",
        data: {todo_search: searchData},
        success: function (data) {
            console.log("submit 성공!!");
            viewTodoListContent(data);
        },
        error: function (error) {
            console.error(error);
        }
    });
}



$(document).on('click', '#logoutBtn', function() {
    location.href = "/logout";
});