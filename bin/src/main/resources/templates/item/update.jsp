<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>물품 수정</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    
    
    <div style="padding: 20px;">
        <h3>물품 수정</h3>

        <form th:action="@{/item/update}" method="post"
            enctype="multipart/form-data">

            물품코드: <input type="text" name="code" th:value="${item.code}" readonly /> <br />
            이름: <input type="text" name="name" th:value="${item.name}" /> <br />
            가격: <input type="text" name="price" th:value="${item.price}" /> <br />
            수량: <input type="text" name="quantity" th:value="${item.quantity}" /> <br />
            <!-- 파일은 name 값은 Item클래스의 변수와 다르게 -->
            <!-- 이유: entity에 있는 파일의 변수가 여러개 -->
            이미지: <img th:src="@{/item/image(code=${item.code})}" style="width: 300px;height: 300px;" /> <br />
            
            <!-- 이미지 첨부 -->
            <input type="file" name="image" /> <br />

            <!-- 버튼 -->
            <input type="submit" value="물품 수정" />

        </form>
    </div>
    
</body>
</html>