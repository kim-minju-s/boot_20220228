<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>home</title>
</head>

<body>
    <div>
        
        <h3>홈화면</h3>
        <hr />

        <div th:if="${session.USERID}">
            <a th:href="@{/member/logout}">로그아웃</a>
            <a th:href="@{/member/mypage}">마이페이지</a>
        </div>

        <div th:unless="${session.USERID}">
            <a th:href="@{/member/login}">로그인</a>
        </div>
        
        

    </div>


</body>
</html>