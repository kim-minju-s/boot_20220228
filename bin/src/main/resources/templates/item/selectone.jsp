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

            물품번호: <input type="text" name="code" th:value="${item.code}" readonly /> <br />
            물품이름: <input type="text" name="name" th:value="${item.name}" /> <br />
            물품가격: <input type="text" name="price" th:value="${item.price}" /> <br />
            물품수량: <input type="text" name="quentity" th:value="${item.quantity}" /> <br />
            이미지: <img th:src="@{/item/image(code=${item.code})}" >

        </form>
        <!-- 버튼 -->
        <a th:href="@{/item/selectlist}" class="btn btn-primary btn-sm"role="button">목록으로<a>
    </div>
    
</body>
</html>