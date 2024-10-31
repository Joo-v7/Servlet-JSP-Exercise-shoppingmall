<%--
  Created by IntelliJ IDEA.
  User: joo
  Date: 2024. 10. 31.
  Time: 오전 10:58
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

    <div class="content active" id="addressManagement">
        <h2>회원 주소 관리</h2>
        <br>

        <!-- 주소 목록 출력 -->
        <div class="address-list">
            <c:forEach var="address" items="${addressList}">
                <c:if test="${empty address}">
                    <h1>등록된 주소가 없습니다.</h1>
                </c:if>
                <div class="form-floating">
                    <div class="address-info">
                           ${address.address}
                        <form method="POST" action="/mypage/address/update.do" style="display:inline;">
                            <input type="hidden" name="addressId" value="${address.address}" />
                            <button type="submit" class="btn btn-secondary">수정</button>
                        </form>
                        <form method="POST" action="/mypage/address/delete.do" style="display:inline;">
                            <input type="hidden" name="addressId" value="${address.address}" />
                            <button type="submit" class="btn btn-danger">삭제</button>
                        </form>
                    </div>
                </div>
                <br>
            </c:forEach>
        </div>


        <form method="POST" action="/mypage/address/add.do">
            <div class="form-floating">
                <input type="text" name="newaddress" class="form-control" id="newaddress" placeholder="새 주소 입력">
                <label for="newaddress">주소 입력</label>
            </div>
            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">추가</button>
        </form>

    </div>


</div>
</body>
</html>


