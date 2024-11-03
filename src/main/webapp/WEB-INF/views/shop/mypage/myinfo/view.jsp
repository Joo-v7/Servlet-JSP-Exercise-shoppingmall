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

    <div id="myInfo" class="content active">
        <h2>내 정보</h2><br>
        <p>닉네임: ${user.userName}</p><br>
        <p>아이디: ${user.userId}</p><br>
        <p>비밀번호: ${user.userPassword}</p><br>
        <p>생년월일: ${user.userBirth}</p><br>
        <p>권한: ${user.userAuth}</p><br>
        <p>포인트: ${user.userPoint}</p><br>
        <p>아이디 생성일 ${user.createdAt}</p><br>
        <p>최근 접속일 ${user.latestLoginAt}</p><br>
    </div>

</div>
</body>
</html>
