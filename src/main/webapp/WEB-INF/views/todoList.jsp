<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<head>
    <title>todoList</title>
    <style>
        #todoListTable {
            width: 700px;
        }
        #todoListTable th:first-child {
            width: 70%;
        }
        #todoListTable th:nth-child(2) {
            width: 30%;
        }
        #todoListTable td:nth-child(2) {
            text-align: center;
        }
    </style>
    <script>
        $(document).ready(function () {
            viewContent();
        });
        function viewContent(){
            $.ajax({
                url: "/todoList/get",
                method: "GET",
                dataType:"JSON",
                success: function (data) {
                    viewMainContent(data);
                },
                error: function (error) {
                    console.error(error);
                }
            });
        }
        function viewMainContent(data) {
            var tbody = $('#todolistTable tbody');
            tbody.empty();

            data.forEach(function(item) {
                var row = $('<tr>');
                var todoCol = $('<td>').append(
                    $('<a>').attr({
                        // 'href': '/todoList_detail?num=' + item.todo_num,
                        'id' : 'todo'
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
        // 삭제 버튼 클릭 이벤트 핸들러 등록
        $(document).on('click', '#delBtn', function() {
            var todoNum = $(this).data('todo-num');
            location.href = "/todoList/delete?todo_num="+todoNum;
        });

        $(document).on('click', '#logoutBtn', function() {
            location.href = "/logout";
        });
    </script>
</head>
<body>
<form method="post" action="/todoList/create">
    <h1>${sessionScope.user_id}님의 todoList</h1>
    <input type="button" id="logoutBtn"value="나가기">
    <div name="top_content">
        <input type="text" name="todo" maxlength="30" required />
        <input type="hidden" name="user_id" value=${sessionScope.user_id} />
        <input type="submit" value="추가하기" />
    </div>
    <div name="mid_content">
        <table id="todoListTable">
            <thead>
                <tr>
                    <th>제목</th>
                    <th>기능</th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</form>
</body>
</html>
