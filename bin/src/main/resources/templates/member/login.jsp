<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">

<head th:replace="~{/member/header :: headerFragment}"></head> 

<body>
    <div style="padding: 20px;">
        <h3>로그인</h3>
        <!-- th는 thymeleaf -->
        <!-- thymeleaf: 템플릿 엔진, 컨트롤러가 전달하는 데이터를 이용하여 화면 구현 -->
        <form th:action="@{/member/login}" method="post">
            아이디: <input type="text" name="id" /> <br />
            암호: <input type="password" name="pw" /> <br />
            <input type="submit" class="btn btn-primary" value="로그인" />
        </form>

    </div>
</body>

<footer th:replace="~{/member/footer :: footerFragment}"></footer>
</html>