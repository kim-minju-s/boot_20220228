<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 목록</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    
    
    <div style="padding: 20px;">
        <h3>게시판 목록</h3>
		
		<a th:href="@{/board/insert}">글쓰기</a>
        <form th:action="@{/board/action}" method="post">
        
        	<input type="submit" name="btn" value="삭제">
        	<input type="submit" name="btn" value="수정">
            
            <table class="table">
                <tr>
                    <th>라디오</th>
                    <th>번호</th>
                    <th>제목</th>
                    <th>저자</th>
                    <th>조회수</th>
                    <th>등록일</th>
                </tr>
                <tr th:each="tmp : ${list}">
					<td><input type="radio" name="rad" th:value="${tmp.no}"> </td>
					<td th:text="${tmp.no}"></td>
					<td th:text="${tmp.title}"></td>
					<td th:text="${tmp.writer}"></td>
					<td th:text="${tmp.hit}"></td>
					<td th:text="${tmp.regdate}"></td>
                </tr>

            </table>
        </form>

        
    </div>
    
</body>
</html>