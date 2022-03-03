<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">

<head th:replace="~{/member/header :: headerFragment}"></head> 

<body>
    <div style="padding: 20px;">
        <h3>마이페이지</h3>
        
        <a th:href="@{/member/mypage?menu=1}" class="btn btn-primary btn-sm" role="button">정보변경</a>
        <a th:href="@{/member/mypage?menu=2}" class="btn btn-primary btn-sm" role="button">암호변경</a>
        <a th:href="@{/member/mypage?menu=3}" class="btn btn-primary btn-sm" role="button">탈퇴하기</a>
        <hr />

        <div th:if="${param.menu.toString().equals('1')}">
            <h3>정보수정</h3>

            변경할 아이디: <input type="text" name="id" /> <br />
            변경할 이름: <input type="text" name="name" /> <br />
            <a href="#" class="btn btn-primary" role="button">정보수정</a>
            

        </div>

        <div th:if="${param.menu.toString().equals('2')}">
            <h3>암호변경</h3>

            현재 암호: <input type="text" name="pw" /> <br />
            변경할 암호: <input type="text" name="pw1" /> <br />
            암호 확인: <input type="text" name="pw2" /> <br />
            <a href="#" class="btn btn-primary" role="button">암호변경</a>

        </div>

        <div th:if="${param.menu.toString().equals('3')}">
            <h3>회원탈퇴</h3>

            아이디: <input type="text" name="id"> <br />
            암호: <input type="text" name="pw"> <br />
            <a href="#" class="btn btn-primary" role="button">암호변경</a>
        </div>

    </div>
</body>

<footer th:replace="~{/member/footer :: footerFragment}"></footer>
</html>