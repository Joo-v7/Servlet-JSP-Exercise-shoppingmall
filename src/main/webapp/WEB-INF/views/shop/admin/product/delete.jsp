<%--
  Created by IntelliJ IDEA.
  User: joo
  Date: 2024. 11. 2.
  Time: 오후 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>등록된 상품 리스트</title>
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
    <form action="/admin/product/list.do" method="GET">
      <button type="submit" class="btn btn-primary">상품 목록</button><br><br><br>
    </form>
    <form action="/admin/product/register.do" method="GET">
      <button type="submit" class="btn btn-primary">상품 등록</button><br><br><br>
    </form>
    <form action="/admin/product/update.do" method="GET">
      <button type="submit" class="btn btn-primary">상품 수정</button><br><br><br>
    </form>
    <form action="/admin/product/delete.do" method="GET">
      <button type="submit" class="btn btn-primary">상품 삭제</button><br><br><br>
    </form>
  </div>

  <div>
    <h1 class="table-title">상품 목록</h1>
    <table class="product-table">
      <thead>
      <tr>
        <th>상품 ID</th>
        <th>카테고리 ID</th>
        <th>상품 이름</th>
        <th>상품 가격</th>
        <th>상품 수량</th>
        <th>상품 등록일</th>
        <th>상품 설명</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="user" items="${productList}">
        <tr>
          <td>${productList.productId}</td>
          <td>${productList.categoryId}</td>
          <td>${productList.productName}</td>
          <td>${productList.productPrice}</td>
          <td>${productList.quantity}</td>
          <td>${productList.registration}</td>
          <td>${productList.description}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>

