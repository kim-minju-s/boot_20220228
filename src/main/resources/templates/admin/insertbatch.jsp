<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일괄등록</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    
    
    <div style="padding: 20px;">
        <h3>일괄등록</h3>

        <a th:href="@{/admin/selectlist}">도서목록</a>

        <form th:action="@{/admin/insertbatch}" method="post">
            <table class="table">
                <tr>
                    <th>제목</th>
                    <th>가격</th>
                    <th>저자</th>
                    <th>분류</th>
                </tr>

                <tr th:each="i : ${#numbers.sequence(1,2)}">
                    <td><input type="text" name="title" value="1" /></td>
                    <td><input type="text" name="price" value="2"/></td>
                    <td><input type="text" name="writer" value="sr"/></td>
                    <td>
                        <select name="category">
                            <option>A</option>
                            <option>B</option>
                            <option>C</option>
                        </select>
                    </td>
                </tr>
            </table>
            
            <input type="submit" class="btn btn-success" value="도서일괄등록" />

        </form>
    </div>
    
</body>
</html>