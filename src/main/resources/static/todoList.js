$(document).ready(function () {
    viewContent();

    // 추가 버튼
    $("#contentForm").submit(function(event) {
        console.log("submit 정상 요청됨");

        // 이벤트실행 막기 -> 새로고침을 막음
        event.preventDefault();

        var formData = $(this).serialize();
        viewCreateContent(formData);
        $("#todo").val("").focus();
    });

    //완료 버튼
    $(document).on('click', '#putBtn', function() {
        console.log('putBtn 정상작동');
        var todoClass = '.todo'+$(this).data("todo-num");
        var putClass = '.putBtn'+$(this).data("todo-num");
        var upClass = '.upBtn'+$(this).data("todo-num");

        console.log(this);

        $(todoClass).attr('disabled', true);
        $(putClass).css('display', 'none');
        $(upClass).css('display', 'inline');

        var numData = $(this).data("todo-num");
        var todoData = $(todoClass).val();
        viewUpdateContent(numData, todoData);
    });

    // 수정 버튼
    $(document).on('click', '#upBtn', function() {
        console.log('upBtn 정상작동');
        var todoClass = '.todo'+$(this).data("todo-num");
        var putClass = '.putBtn' + $(this).data("todo-num");
        var upClass = '.upBtn'+$(this).data("todo-num");
        $(todoClass).attr('disabled', false).focus();
        $(putClass).css('display', 'inline');
        $(upClass).css('display', 'none');

    });

    // 삭제 버튼
    $(document).on('click', '#delBtn', function() {
        console.log("delBtn 정상작동");
        var numData = $(this).data("todo-num");
        console.log("numData : "+numData);
        viewDeleteContent(numData);
    });

    // 찾기 버튼
    $(document).on('click', '#searchBtn', function() {
        console.log("searchBtn 정상작동");
        var searchData = $('#todo_search').val();
        console.log("todo_search : "+ searchData);
        viewSearchContent(searchData);
        $("#todo_search").val("").focus();
    });


    // 엔터키 적용
    $("#todo_search").keydown(function(key) {
        if (key.keyCode == 13) {
            console.log("searchInput 정상작동");
            var searchData = $('#todo_search').val();
            console.log("todo_search : "+ searchData);
            viewSearchContent(searchData);
            $("#todo_search").val("").focus();
        }
    });

    // 엔터키 적용
    $(document).on("keydown","#todoInput",function (key){
        if (key.keyCode == 13) {
            console.log("todoInput 정상작동");
            var todoClass = '.todo'+$(this).data("todo-num");
            var putClass = '.putBtn'+$(this).data("todo-num");
            var upClass = '.upBtn'+$(this).data("todo-num");
            $(todoClass).attr('disabled', true);
            $(putClass).css('display', 'none');
            $(upClass).css('display', 'inline');

            var numData = $(this).data("todo-num");
            var todoData = $(todoClass).val();
            viewUpdateContent(numData, todoData);

        }
    })

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
                'id': 'todoInput',
                'class': 'todo'+item.todo_num,
                'data-todo-num': item.todo_num,
                'disabled': 'disabled'
            }).val(item.todo)
        //    .val('· ' + item.todo)
        );
        var functionCol = $('<td>').append(
            $('<input>').css('display', 'none').attr({
                'type': 'button',
                'id': 'putBtn',
                'class': 'putBtn'+item.todo_num,
                'data-todo-num': item.todo_num
            }).val("완료"),
            $('<input>').attr({
                'type': 'button',
                'id': 'upBtn',
                'class': 'upBtn'+item.todo_num,
                'data-todo-num': item.todo_num
            }).val("수정"),
            $('<input>').attr({
                'type': 'button',
                'id': 'delBtn',
                'class': 'functionBtn',
                'data-todo-num': item.todo_num
            }).val("삭제")
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
            alert("Create Connect Error");
            window.location.href = "/login";
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
            console.log(data.data);
            // 로그인 유효성 검사
            // LoginValidate(data);
            viewTodoListContent(data.data);
        },
        error: function(e) {
            alert("read error");
            window.location.href = "/login";
        }
    });
}

// Update - Put
function viewUpdateContent(numData, todoData) {
    console.log("numData : " + numData);
    console.log("todoData : " + todoData);
    $.ajax({
        url: "/todoList/update",
        type: "PUT",
        data: {new_todo: todoData, todo_num: numData},
        success: function(data) {
            console.log("연결성공!")
            // viewTodoListContent(data.data);
        },
        error: function(e) {
            alert("update error");
            window.location.href = "/login";
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
            window.location.href = "/login";
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
            console.log(data);
            if(data.serviceBool === true && data.controllerBool === true){
                console.log("상태확인 완료");
                viewTodoListContent(data.data);
            }
            else{
                console.log("상태이상 발견");
                alert("알 수 없는 오류입니다.");
                location.href = '/todoList';
            }
        },
        error: function(e) {
            alert("search error");
            window.location.href = "/login";
        }
    });
}

// // 로그인 유효성 검사
// function LoginValidate(data){
//     if(data.status === "fail") {
//         alert(data.message);
//         location.href = '/';
//     }
// }

// 로그아웃
function logout() {
    $.get('/logout', function() {
        alert("로그아웃 성공");
        window.location.replace('/login');
    });
}

