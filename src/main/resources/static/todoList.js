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

        // var middot = $(todoClass).val().slice(0,1);
        // var data = $(todoClass).val().slice(2);
        //
        // if(middot == "· "){
        //     console.log("동일합니다.");
        //
        // }else if (middot == "·") {
        //     console.log("다름.");
        // }else{
        //     console.log("완전다름.");
        // }
    });

    $(document).on('click', '#upBtn', function() {
        console.log('upBtn 정상작동');
        var todoClass = '.todo'+$(this).data("todo-num");
        var putClass = '.putBtn'+$(this).data("todo-num");
        var upClass = '.upBtn'+$(this).data("todo-num");
        $(todoClass).attr('disabled', false);
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
            }).val(item.todo)  // val 메소드를 사용하여 값 설정
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
        location.href = "/";
    }
}

$(document).on('click', '#logoutBtn', function() {
    location.href = "/logout";
});

