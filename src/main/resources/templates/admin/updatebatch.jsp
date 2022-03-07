<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일괄 수정</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    
    
    <div style="padding: 20px;" class="container">
        <h3> 일괄 수정 </h3>
		<hr />
		
		<form th:action="@{/admin/updatebatch}" method="post">
            <table class="table">
                <tr>
                    <th>코드</th>
                    <th>제목</th>
                    <th>가격</th>
                    <th>저자</th>
                    <th>분류</th>
                    <th>등록일</th>
                </tr>

                <tr th:each="tmp : ${list} ">
                    <td><input type="text" name="code" th:value="${tmp.code}" readonly /></td>
                    <td><input type="text" name="title" th:value="${tmp.title}" /></td>
                    <td><input type="text" name="price" th:value="${tmp.price}"/></td>
                    <td><input type="text" name="writer" th:value="${tmp.writer}"/></td>
                    <td>
                        <select name="category">
                        
                            <option th:selected="${#strings.equals(tmp.category, 'A')}">A</option>
                            <option th:selected="${#strings.equals(tmp.category, 'B')}">B</option>
                            <option th:selected="${#strings.equals(tmp.category, 'C')}">C</option>
                        </select>
                    </td>
                    <td th:text="${tmp.regdate}" ></td>
                </tr>
            </table>
            
            <input type="submit" class="btn btn-success" value="일괄수정" />

        </form>
        
    </div>
    
</body>
</html>