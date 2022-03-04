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
        <p th:text="|${session.USERNAME}님 로그인|"></p>
        <div th:if="${param.menu.toString().equals('1')}">
            <h3>정보수정</h3>

            <form th:action="@{/member/mypage(menu=1)}" method="post">
                아이디: <input type="text" name="id" th:value="${mem.id}" readonly /> <br />
                변경할 이름: <input type="text" name="name" th:value="${mem.name}" /> <br />
                변경할 나이: <input type="text" name="age"  th:value="${mem.age}"/> <br />
                <input type="submit" value="정보수정">
                <a th:href="@{/member/mypage(menu=1)}" class="btn btn-primary" role="button">정보수정</a>
            </form>

        </div>

        <div th:if="${param.menu.toString().equals('2')}">
            <h3>암호변경</h3>
            <form th:action="@{/member/mypage(menu=2)}" method="post">
                현재 암호: <input type="text" name="pw" /> <br />
                변경할 암호: <input type="password" name="newPw" /> <br />
                암호 확인: <input type="password" /> <br />
                <input type="submit" value="암호변경">

                <a href="#" class="btn btn-primary" role="button">암호변경</a>
            </form>
        </div>

        <div th:if="${param.menu.toString().equals('3')}">
            <h3>회원탈퇴</h3>
            <form th:action="@{/member/mypage(menu=3)}" method="post">
                아이디: <input type="text" name="id" > <br />
                암호: <input type="text" name="pw"> <br />
                <input type="submit" value="회원탈퇴">
                <a href="#" class="btn btn-primary" role="button">회원탈퇴</a>
            </form>
        </div>

    </div>
</body>

<footer th:replace="~{/member/footer :: footerFragment}"></footer>
</html>