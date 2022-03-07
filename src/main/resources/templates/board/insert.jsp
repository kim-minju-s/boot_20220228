<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Board</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    
    
    <div style="padding: 20px;">
        <h3>글쓰기</h3>

		<a th:href="@{/board/selectlist}">목록으로</a>
        <form th:action="@{/board/insert}" method="post">

            제목 : <input type="text" name="title" /> <br />
            내용: <input type="text" name="content" /> <br />
            작성자: <input type="text" name="writer" /> <br />
            
            <input type="submit" value="글쓰기" />

        </form>
    </div>
    
</body>
</html>