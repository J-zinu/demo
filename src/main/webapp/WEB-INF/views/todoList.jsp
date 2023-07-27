<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<head>
    <title>todoList</title>
    <link rel="stylesheet" type="text/css" href="todoList.css">
    <script src="todoList.js"></script>
</head>
<body>
<form id="contentForm" method="post" action="/todoList/create">
    <div id = "header">
        ${sessionScope.user_id}님의 todoList <a id="manage1" href="/manage" id="manage">마이페이지</a>
        <input type="button" id="logoutBtn" value="로그아웃" onclick="logout()" />
    </div>

    <div id="top_content">
        <input type="text" id="todo" name="todo" maxlength="30" required />
        <input type="hidden" id="user_id" name="user_id" value=${sessionScope.user_id} />
        <input type="submit" id="createBtn" value="추가하기" />
    </div>
    <div id="mid_content">
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
    <div id="bot_content">
        <input type="text" id="todo_search" placeholder="할 일 찾기" />
        <input type="button" id="searchBtn" value="찾기" />
    </div>
</form>
</body>
</html>


