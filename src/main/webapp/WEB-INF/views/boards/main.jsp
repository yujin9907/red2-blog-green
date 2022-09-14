<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<!-- 반응형 때문에 컨테이너 안에 넣어줘야 됨
	폼컨트룰 블럭속성으로 바뀜 -->
	<!-- !!!!!!!부모의 크기를 벗어날 수 없음 -->
	<br/>
	<div class="d-flex justify-content-end">
	<div style="width:300px">
	<form class="d-flex" method="get" action="/"> <!-- 셀렉트할 거니까 겟 -->
        <input class="form-control me-2" type="text" placeholder="Search" name="keyword"> <!-- 폼태그니까 이걸 쿼리스트링으로 보내도 됨 직접 액션에 주소 안 걸고 하이퍼링크도 되는데 정적인 값이므로.. -->
        <button class="btn btn-primary" type="submit">Search</button>
      </form>
	</div>
	</div>
	
	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글제목</th>
				<th>작성자이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="boards" items="${pagingDto.mainDto}">
				<tr>
					<td>${boards.id}</td>
					<td><a href="/boards/${boards.id}">${boards.title}</a></td>
					<td>${boards.username}</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>

	<div class="d-flex justify-content-center">
	<ul class="pagination">
		<li class='page-item'><a class="page-link"
			href="/?keyword=${pagingDto.keyword}&page=${pagingDto.currentPage -1}">previous</a></li>
			 <c:forEach var="num" begin="${pagingDto.startPageNum}" end="${pagingDto.lastPageNum}" step="1">
            <a class="page-link" href='?page=${num-1}&keyword=${pagingDto.keyword}'>${num}</a>
         </c:forEach>
			<li class='page-ite'><a class="page-link"
			href="/?keyword=${pagingDto.keyword}&page=${pagingDto.currentPage+1}">Next</a></li>
	</ul>
	</div>


</div>

<%@ include file="../layout/footer.jsp"%>
