<%--
  Created by IntelliJ IDEA.
  User: joo
  Date: 2024. 11. 2.
  Time: 오후 2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>관리자 리스트</title>
    <style>
        table {
            margin: auto;
            width: 800px;
        }
        table, tr, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 8px;
            text-align: center;
        }

        /* 테이블 제목 스타일 */
        .table-title {
            text-align: center;
            font-size: 1.5em;
            margin: 20px 0;
        }

        /* 사이드바 스타일 */
        .sidebar {
            width: 250px;
            background-color: #f8f9fa;
            border-right: 1px solid #ddd;
            padding: 15px;
        }

        .sidebar a {
            display: block;
            padding: 10px;
            margin: 5px 0;
            text-decoration: none;
            color: #333;
            border-radius: 4px;
        }

        .sidebar a.active, .sidebar a:hover {
            background-color: #007bff;
            color: white;
        }

        .container {
            display: flex;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- 사이드바 -->
    <div class="sidebar">
        <form action="/admin/user/list/user.do" method="GET">
            <button type="submit" class="btn btn-primary">유저 정보</button><br><br><br>
        </form>
        <form action="/admin/user/list/admin.do" method="GET">
            <button type="submit" class="btn btn-primary">관리자 정보</button><br><br><br>
        </form>
    </div>

    <!-- 유저 목록 테이블 -->
    <div>
        <h1 class="table-title">관리자 목록</h1>
        <table class="user-table">
            <thead>
            <tr>
                <th>아이디</th>
                <th>닉네임</th>
                <th>비밀번호</th>
                <th>생년월일</th>
                <th>권한</th>
                <th>포인트</th>
                <th>계정 생성일</th>
                <th>최근 접속일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${adminList}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.userName}</td>
                    <td>${user.userPassword}</td>
                    <td>${user.userBirth}</td>
                    <td>${user.userAuth}</td>
                    <td>${user.userPoint}</td>
                    <td>${user.createdAt}</td>
                    <td>${user.latestLoginAt}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

