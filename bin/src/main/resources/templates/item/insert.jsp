<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>물품 등록</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    
    
    <div style="padding: 20px;">
        <h3>물품 등록</h3>

        <form th:action="@{/item/insert}" method="post"
            enctype="multipart/form-data">

            이름: <input type="text" name="name" /> <br />
            가격: <input type="text" name="price" /> <br />
            수량: <input type="text" name="quantity" /> <br />
            <!-- 파일은 name 값을 줄 수 없음 -->
            <!-- 이유: entity에 있는 파일의 변수가 여러개 -->
            이미지: <input type="file" name="image" /> <br />
            <input type="submit" value="물품등록" />

        </form>
    </div>
    
</body>
</html>