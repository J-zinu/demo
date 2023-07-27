<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<head>
    <title>todoList</title>
    <link rel="stylesheet" type="text/css" href="todoList.css">
    <script src="todoList.js"></script>
</head>
<body>
<div id = "header">
    ${sessionScope.user_id}님의 todoList <input id="manage1" type="button" value="마이페이지" onclick="location.href = '/manage'" />
    <input type="button" id="logoutBtn" value="로그아웃" onclick="logout()" />
</div>
<div id="content">
<form id="contentForm" method="post" action="/todoList/create">
    <div id="top_content">
        <input type="text" id="todo_search" placeholder="할 일 찾기" />
        <input type="button" id="searchBtn" value="찾기" /><br/>
        <input type="text" id="todo" name="todo" maxlength="30" placeholder="할 일 추가" required />
        <input type="hidden" id="user_id" name="user_id" value=${sessionScope.user_id} />
        <input type="submit" id="createBtn" value="추가" />
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
</form>
</div>
</body>
</html>


