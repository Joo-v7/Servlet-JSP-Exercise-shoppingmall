<%--
  Created by IntelliJ IDEA.
  User: joo
  Date: 2024. 10. 30.
  Time: 오후 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>접근 제한</title>
    <script type="text/javascript">
        // 페이지가 로드되면 alert을 띄우고 이전 페이지로 이동
        window.onload = function() {
            alert("관리자만 접근 가능한 페이지입니다.");
            history.back(); // 이전 페이지로 돌아갑니다.
        };
    </script>
</head>
<body>
</body>
</html>
