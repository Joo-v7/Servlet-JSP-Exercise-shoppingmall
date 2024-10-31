<%--
  Created by IntelliJ IDEA.
  User: joo
  Date: 2024. 10. 30.
  Time: 오후 1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<div style="margin: auto; width:600px;">
    <div class="p-2">
        <form method="post" action="/signup/signupAction.do">

            <h1 class="h3 mb-3 fw-normal">회원가입</h1>

            <div class="form-floating">
                <input type="text" name="user_id" class="form-control" id="user_id" placeholder="회원 아이디" required>
                <label for="user_id">아이디</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password" class="form-control" id="user_password" placeholder="회원 비밀번호" required>
                <label for="user_password">비밀번호</label>
            </div>

            <div class="form-floating">
                <input type="text" name="user_name" class="form-control" id="user_name" placeholder="닉네임" required>
                <label for="user_name">닉네임</label>
            </div>

            <div class="form-floating">
                <input type="text" name="user_birth" class="form-control" id="user_birth" placeholder="ex) 19981218" required>
                <label for="user_birth">생년월일 ex) 19981218</label>
            </div>

                <input type="radio" name="user_auth" value="ROLE_USER">유저
                <input type="radio" name="user_auth" value="ROLE_ADMIN" >관리자

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">회원가입</button>
            <p class="mt-5 mb-3 text-muted">@ 2022-2024</p>

        </form>
    </div>
</div>
</body>
</html>
