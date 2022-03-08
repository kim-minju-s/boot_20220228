<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>도서 목록</title>
    <!-- bootstrap/css -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <!-- bootstrap/js -->
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    
    
    <div style="padding: 20px;">
        <h3>도서 목록</h3>

		<a th:href="@{/book/insert}">글쓰기</a>
		
		<!-- 검색 -->
		<form th:action="@{/book/select}" method="get">
            <input type="hidden" name="page" value="1" />
            <input type="text" name="text" placeholder="검색" />
            <input type="submit" value="검색" />
        </form>
		
        <form th:action="@{/book/select}" method="get">

            <table class="table">
            	<tr>
            		<th>체크</th>
            		<th>번호</th>
            		<th>제목</th>
            		<th>가격</th>
            		<th>저자</th>
            		<th>분류</th>
            		<th>등록일</th>
            	</tr>
            	
            	<tr th:each="tmp,idx : ${list}">
            		<td><input type="checkbox" name="chk" th:value="${tmp.code}" /> </td>
            		<td th:text="${idx.count}"></td>
            		<td th:text="${tmp.title}"></td>
            		<td th:text="${tmp.price}"></td>
            		<td th:text="${tmp.writer}"></td>
            		<td th:text="${tmp.category}"></td>
            		<td th:text="${tmp.regdate}"></td>
            	</tr>
            </table>

        </form>
        
        <!-- 페이지네이션 -->
        <nav>
	         <ul class="pagination">
	             <th:block th:each="i : ${#numbers.sequence(1,pages)}">
	                 <li class="page-item">
	                     <a class="page-link" th:href="@{/book/select(page=${i}, text=${param.text})}" th:text="${i}"></a>
	                 </li>
	             </th:block>
	         </ul>
	     </nav>
    </div>
    
</body>
</html>