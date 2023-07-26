$(document).ready(function () {
    viewContent();

    $("#contentForm").submit(function(event) {
        console.log("submit 정상 요청됨");
        var formData = $(this).serialize();
        console.log("formData : "+formData);

        // 이벤트실행 막기 -> 새로고침을 막음
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
        var todoCol = $('<td>').append(
            $('<a>').attr({
                'href' : '/todoList_detail?todo_num=' + item.todo_num
            }).html('&middot; ' + item.todo)
        );
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
function viewCreateContent(formData) {
    $.ajax({
        url: "/todoList/create",
        type: "POST",
        data: formData,
        success: function(data) {
            viewTodoListContent(data.data);
        },
        error: function(e) {
            alert(e.responseText);
        }
    });
}


// Read - Get
function viewContent() {
    $.ajax({
        url: "/todoList/read",
        type: "GET",
        success: function(data) {
            console.log(data);
            LoginValidate(data);
        },
        error: function(e) {
            alert(e.responseText);
        }
    });
}
// Update

// Delete - Delete
function viewDeleteContent(numData) {
    $.ajax({
        url: "/todoList/delete",
        type: "DELETE",
        data: {todo_num: numData},
        success: function(data) {
            viewTodoListContent(data.data);
        },
        error: function(e) {
            alert(e.responseText);
        }
    });
}

// Search
function viewSearchContent(searchData) {
    $.ajax({
        url: "/todoList/search",
        type: "POST",
        data: {todo_search: searchData},
        success: function(data) {
            viewTodoListContent(data.data);
        },
        error: function(e) {
            alert(e.responseText);
        }
    });
}

// 유효성 검사
function LoginValidate(data){
    if(data.status === "fail") {
        alert(data.message);
        location.href = "/";
    }
    else {
        viewTodoListContent(data.data);
    }
}

$(document).on('click', '#logoutBtn', function() {
    location.href = "/logout";
});