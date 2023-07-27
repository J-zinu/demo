$(document).ready(function () {
    viewContent();

    $("#contentForm").submit(function(event) {
        console.log("submit 정상 요청됨");

        // 이벤트실행 막기 -> 새로고침을 막음
        event.preventDefault();

        var formData = $(this).serialize();
        viewCreateContent(formData);
        $("#todo").val("").focus();
    });

    $(document).on('click', '#putBtn', function() {
        console.log('putBtn 정상작동');
        var todoClass = '.todo'+$(this).data("todo-num");
        var putClass = '.putBtn'+$(this).data("todo-num");
        var upClass = '.upBtn'+$(this).data("todo-num");
        $(todoClass).attr('disabled', true);
        $(putClass).css('display', 'none');
        $(upClass).css('display', 'inline');

        var numData = $(this).data("todo-num");
        var todoData = $(todoClass).val();
        viewUpdateContent(numData, todoData);
    });

    $(document).on('click', '#upBtn', function() {
        console.log('upBtn 정상작동');
        var todoClass = '.todo'+$(this).data("todo-num");
        var putClass = '.putBtn'+$(this).data("todo-num");
        var upClass = '.upBtn'+$(this).data("todo-num");
        $(todoClass).attr('disabled', false).focus();
        $(putClass).css('display', 'inline');
        $(upClass).css('display', 'none');

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
            $('<input>').attr({
                'type': 'text',
                'class': 'todo'+item.todo_num,
                'data-todo-num': item.todo_num,
                'disabled': 'disabled'
            }).val(item.todo)
        //    .val('· ' + item.todo)
        );
        var functionCol = $('<td>').append(
            $('<button>').css('display', 'none').attr({
                'type': 'button',
                'id': 'putBtn',
                'class': 'putBtn'+item.todo_num,
                'data-todo-num': item.todo_num
            }).text("완료"),
            $('<button>').attr({
                'type': 'button',
                'id': 'upBtn',
                'class': 'upBtn'+item.todo_num,
                'data-todo-num': item.todo_num
            }).text("수정"),
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
            alert("create error");
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

            // 로그인 유효성 검사
            LoginValidate(data);
            viewTodoListContent(data.data);
        },
        error: function(e) {
            alert("read error");
        }
    });
}

// Update
function viewUpdateContent(numData, todoData) {
    console.log("numData : " + typeof numData);
    console.log("todoData : " + typeof todoData);
    $.ajax({
        url: "/todoList/update",
        type: "PUT",
        data: {todo: todoData, todo_num: numData},
        success: function(data) {
            console.log("연결성공!")
            viewTodoListContent(data.data);
        },
        error: function(e) {
            alert("update error");
        }
    });
}

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
            alert("delete error");
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
            alert("search error");
        }
    });
}

// 로그인 유효성 검사
function LoginValidate(data){
    if(data.status === "fail") {
        alert(data.message);
        location.href = '/';
    }
}

function logout() {
    // /logout 엔드포인트를 호출하여 사용자 로그아웃
    $.get('/logout', function() {
        // 로그아웃 후 로그인 페이지 또는 적절한 다른
        window.location.replace('/login'); // "/login"을 실제 로그인 페이지의 URL로 대체하세요
    });
}