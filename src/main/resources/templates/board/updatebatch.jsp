<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 수정</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    
    
    <div style="padding: 20px;">
        
        <h3>게시글 수정</h3>
        <hr />
        <form th:action="@{/board/updatebatch}" method="post">

           	제목: <input type="text" name="title" th:value="${board.title}" /> <br />
            내용: <input type="text" name="content" th:value="${board.content}" /> <br />
            작성자: <input type="text" name="writer" th:value="${board.writer}" /> <br />
            
            <input type="submit" value="수정하기" />

        </form>
        
    </div>
    
</body>
</html>