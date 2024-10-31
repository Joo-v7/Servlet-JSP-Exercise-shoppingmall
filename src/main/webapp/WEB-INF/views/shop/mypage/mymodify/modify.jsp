<%--
  Created by IntelliJ IDEA.
  User: joo
  Date: 2024. 10. 31.
  Time: 오전 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <style>
    body {
      margin: 0;
      font-family: Arial, sans-serif;
    }

    .container {
      display: flex; /* Flexbox 레이아웃 활성화 */
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

    /* 콘텐츠 영역 */
    .content {
      flex-grow: 1; /* 콘텐츠 영역이 남은 공간을 차지하도록 설정 */
      padding: 20px;
      display: none; /* 기본적으로 감춤 */
    }

    /* 처음에 보이는 첫 번째 섹션 */
    .content.active {
      display: block;
    }
  </style>

  <script>
    // 탭 전환 함수: 특정 id의 콘텐츠만 보이도록 설정
    function showContent(id) {
      // 모든 콘텐츠를 숨김
      const sections = document.querySelectorAll('.content');
      sections.forEach(section => section.classList.remove('active'));

      // 선택한 콘텐츠만 보이도록 설정
      document.getElementById(id).classList.add('active');

      // 모든 메뉴에서 active 클래스 제거
      const menuItems = document.querySelectorAll('.sidebar a');
      menuItems.forEach(item => item.classList.remove('active'));

      // 클릭한 메뉴에 active 클래스 추가
      event.target.classList.add('active');
    }
  </script>
</head>
<body>
<div class="container">
  <!-- 사이드바 -->
  <div class="sidebar">
    <form action="/mypage/myinfo/view.do" method="GET">
      <button type="submit" class="btn btn-primary">내 정보</button><br><br><br>
    </form>

    <form action="/mypage/mymodify/modify.do" method="GET">
      <button type="submit" class="btn btn-primary">회원 정보 수정</button><br><br><br>
    </form>

    <form action="/mypage/myorder/order.do" method="GET">
      <button type="submit" class="btn btn-primary">주문 명세 조회</button><br><br><br>
    </form>

    <form action="/mypage/mypoint/point.do" method="GET">
      <button type="submit" class="btn btn-primary">포인트 사용내역 조회</button><br><br><br>
    </form>

    <form action="/mypage/address/list.do" method="GET">
      <button type="submit" class="btn btn-primary">내 주소 관리</button><br><br><br>
    </form>

    <form action="/mypage/unsubscribe/unsubscribe.do" method="GET">
      <button type="submit" class="btn btn-primary">회원 탈퇴</button><br><br><br>
    </form>

  </div>

  <div id="editInfo" class="content active">
    <h2>회원 정보 수정</h2><br>
    <div style="margin: auto; width:600px;">
      <div class="p-2">
        <form method="post" action="/mypage/mymodify/modifyAction.do">

          <h1 class="h3 mb-3 fw-normal">회원 정보 수정</h1>

          <div class="form-floating">
            <input type="text" name="user_id" class="form-control" id="user_id" value="${user.userId}">
            <label for="user_id">아이디(수정 불가능 합니다)</label>
          </div>

          <div class="form-floating">
            <input type="password" name="user_password" class="form-control" id="user_password" value="${user.userPassword}">
            <label for="user_password">비밀번호</label>
          </div>

          <div class="form-floating">
            <input type="text" name="user_name" class="form-control" id="user_name" value="${user.userName}">
            <label for="user_name">닉네임</label>
          </div>

          <div class="form-floating">
            <input type="text" name="user_birth" class="form-control" id="user_birth" value="${user.userBirth}">
            <label for="user_birth">생년월일 ex) 19981218</label>
          </div>

          <div class="form-floating">
            <input type="text" name="user_point" class="form-control" id="user_point" value="${user.userPoint}">
            <label for="user_birth">포인트(수정 불가능 합니다.)</label>
          </div>

          <div class="form-floating">
            <input type="text" name="createdAt" class="form-control" id="createdAt" value="${user.createdAt}">
            <label for="user_birth">계정 생성일(수정 불가능 합니다.)</label>
          </div>

          <div class="form-floating">
            <input type="text" name="latestLoginAt" class="form-control" id="latestLoginAt" value="${user.latestLoginAt}">
            <label for="user_birth">최근 접속일(수정 불가능 합니다.)</label>
          </div>

          <input type="radio" name="user_auth" value="ROLE_USER"
                 <c:if test="${user.userAuth == 'ROLE_USER'}">checked</c:if>>유저
          <input type="radio" name="user_auth" value="ROLE_ADMIN"
                 <c:if test="${user.userAuth == 'ROLE_ADMIN'}">checked</c:if>>관리자

          <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">수정하기</button>
          <p class="mt-5 mb-3 text-muted">@ 2022-2024</p>

        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>

