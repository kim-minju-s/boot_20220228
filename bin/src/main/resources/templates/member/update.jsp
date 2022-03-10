<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head th:replace="~{/member/header :: headerFragment}"></head>
<body>
    
    
    <div style="padding: 20px;">
        
        <h3>회원정보 수정</h3>
        <hr />
        <form th:action="@{/member/update}" method="post">

            아이디: <input type="text" name="id" th:value="${member.id}" readonly /> <br />
            이름: <input type="text" name="name" th:value="${member.name}" /> <br />
            나이: <input type="text" name="age" th:value="${member.age}" /> <br />
            <input type="submit" value="수정" />

        </form>
        
    </div>
    
</body>
<footer th:replace="~{/member/footer :: footerFragment}"></footer>
</html>