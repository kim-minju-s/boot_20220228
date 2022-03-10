<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">

<head th:replace="~{/member/header :: headerFragment}"></head> 

<body>
    
    <div>
        <h3>회원가입</h3>
        <!-- th는 thymeleaf -->
        <!-- thymeleaf: 템플릿 엔진, 컨트롤러가 전달하는 데이터를 이용하여 화면 구현 -->
        <form th:action="@{/member/insert}" method="post">
            아이디: <input type="text" name="id" /> <br />
            암호: <input type="password" name="pw" /> <br />
            암호2: <input type="password" name="pw1" /> <br />
            이름: <input type="text" name="name" /> <br />
            나이: <input type="text" name="age" /> <br />
            <input type="submit" class="btn btn-primary" value="회원가입" />
        </form>
        

    </div>
    
</body>

<footer th:replace="~{/member/footer :: footerFragment}"></footer>
</html>