<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>물품 상세조회</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    
    
    <div style="padding: 20px;">
        <h3>물품 상세조회</h3>

        <form th:action="@{/item/selectone}" method="get"
            enctype="multipart/form-data">

            물품번호: <input type="text" name="code" /> <br />
            물품이름: <input type="text" name="name" /> <br />
            물품가격: <input type="text" name="price" /> <br />
            물품수량: <input type="text" name="quentity" /> <br />
            이미지: 

        </form>
    </div>
    
</body>
</html>