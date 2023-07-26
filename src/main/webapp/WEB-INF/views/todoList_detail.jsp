<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<head>
    <title>todoList_detail</title>
    <link rel="stylesheet" type="text/css" href="todoList.css">
    <script src="todoList.js"></script>
</head>
<body>
<h1>${sessionScope.user_id}님의 todoList_detail페이지입니다.</h1><br/>

<div class="container">
    <table id="detailTable">
        <thead>
            <tr>
                <th>11</th>
                <th>12</th>
                <th>13</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="">21</td>
                <td class="">22</td>
                <td class="second">23</td>
            </tr>
            <tr>
                <td>31</td>
                <td>32</td>
                <td>33</td>
            </tr>
        </tbody>
    </table>
</div>
<a href="/todoList">목록</a>
<a href="/todoList">수정</a>
<a href="/todoList">삭제</a>
</body>
</html>
