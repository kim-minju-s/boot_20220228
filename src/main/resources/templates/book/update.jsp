<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>도서 수정</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    
    
    <div style="padding: 20px;">
        <h3>도서 수정</h3>

        <form th:action="@{/book/udpate}" method="post">

            책제목: <input type="text" name="title"  /> <br />
            책가격: <input type="text" name="price"> <br />
            책내용: <input type="text" name="content" /> <br />
            책저자: <input type="text" name="writer" /> <br />
            분류:
            <select name="category">
            	<option>소설</option>
            	<option>에세이</option>
            	<option>전문</option>
            </select>
            
            <hr />
            
            <input type="submit" value="수정하기" />

        </form>
    </div>
    
</body>
</html>