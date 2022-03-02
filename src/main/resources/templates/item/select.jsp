<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>물품 조회</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
    
</head>
<body>

    <div style="padding: 20px;" class="container">
        <h3>물품 조회</h3>

        <form th:action="@{/item/insert}" method="get">
            <input type="submit" value="물품추가">
        </form>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">번호</th>
                    <th>물품번호</th>
                    <th>물품이름</th>
                    <th>이미지</th>
                    <th>물품가격</th>
                    <th>물품수량</th>
                    <th>물품등록날짜</th>
                    <th>버튼</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="tmp, idx : ${list}">
                    <th th:text="${idx.count}" scope="row" ></th>
                    <td th:text="${tmp.code}"></td>
                    <td th:text="${tmp.name}"></td>
                    <!-- src는 경로를 줘야함 -->
                    <td><img th:src="@{/item/image(code=${tmp.code})}" style="width: 50px;height: 50px;" /></td>
                    <td th:text="${tmp.price}"></td>
                    <td th:text="${tmp.quantity}"></td>
                    <td th:text="${tmp.regdate}"></td>
                    <td>
                        <a href="#">상세보기</a>
                        <a href="#">수정</a>
                        <a href="#">삭제</a>
                    </td>
                </tr>
            </tbody>
            
        </table>
        <!-- 페이지네이션 -->
        <!-- <th:block th:each="i : ${#numbers.sequence(1,pages)}">
            <a th:href="@{/item/selectlist(page=${i})}" th:text="${i}"></a>
        </th:block> -->

        <nav>
            <ul class="pagination">
                <th:block th:each="i : ${#numbers.sequence(1,pages)}">
                    <li class="page-item">
                        <a class="page-link" th:href="@{/item/selectlist(page=${i})}" th:text="${i}"></a>
                    </li>
                </th:block>
            </ul>
        </nav>

    </div>
    
</body>
</html>