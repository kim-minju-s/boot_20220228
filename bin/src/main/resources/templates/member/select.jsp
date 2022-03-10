<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head th:replace="~{/member/header :: headerFragment}"></head>

<body>
    
    
    <div>
        <h3>회워목록</h3>
        <hr />
        <table class="table">
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>나이</th>
                <th>버튼</th>
            </tr>
            <!-- entity 의 변수를 사용 -->
            <tr th:each="tmp, idx : ${list}">
                <td th:text="${tmp.id}"></td>
                <td th:text="${tmp.name}"></td>
                <td th:text="${tmp.age}"></td>
                <td>
                    <a th:href="@{/member/update(id=${tmp.id})}">수정</a>
                    <a th:href="@{/member/delete(id=${tmp.id})}">삭제</a>

                    <!-- <form th:action="@{/member/delete}" method="get">
                        <input type="hidden" name="id" th:value="${tmp.id}" />
                        <input type="submit" value="삭제1">
                    </form> -->
                </td>
            </tr>
        </table>

    </div>
    
</body>
<footer th:replace="~{/member/footer :: footerFragment}"></footer>
</html>