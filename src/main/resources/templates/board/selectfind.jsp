<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>조회</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    
    <div style="padding: 20px;">
        <h3>조회</h3>
		
		<a th:href="@{/board/selectlist}">목록으로</a>
		<a th:href="@{/board/selectfind}">조회 목록</a>
		
		<h5>1. 정확하게 일치하는 항목 가져오기</h5>
        <form th:action="@{/board/selectfind}" method="get">
            <select name="type">
            	<option value="title">제목</option>
            	<option value="writer">작성자</option>
            	<option value="hit">조회수</option>
            </select>
            <input type="text" name="text" placeholder="검색어" />
            <input type="submit" value="검색" />
        </form>
        <hr />
        
        <h5>2. 조회수가 이상, 미만 조회하기</h5>
        <form th:action="@{/board/selectfind}" method="get">
            <select name="type1">
            	<option value="1">이상</option>
            	<option value="2">미만</option>
            </select>
            <input type="text" name="text1" placeholder="검색어" />
            <input type="submit" value="검색" />
        </form>
        <hr />
        
        <h5>3. 포함, 미포함</h5>
        <form th:action="@{/board/selectfind}" method="get">
            <select name="type2">
                <option value="1">포함</option>
                <option value="2">미포함</option>
            </select>
            <input type="text" name="no" placeholder="글번호1" />
            <input type="text" name="no" placeholder="글번호2" />
            <input type="text" name="no" placeholder="글번호3" />
            <input type="submit" value="검색" />
        </form>
            
        <table class="table">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>저자</th>
                <th>조회수</th>
                <th>등록일</th>
            </tr>
            <tr th:each="tmp : ${list}">
				<td th:text="${tmp.no}"></td>
				<td th:text="${tmp.title}"></td>
				<td th:text="${tmp.writer}"></td>
				<td th:text="${tmp.hit}"></td>
				<td th:text="${tmp.regdate}"></td>
            </tr>

        </table>
        
    </div>
    
</body>
</html>