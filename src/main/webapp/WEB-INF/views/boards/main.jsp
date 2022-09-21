<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br/>
	<div class="d-flex justify-content-end">
	<div style="width:300px">
	<form class="d-flex" method="get" action="/">
        <input class="form-control me-2" type="text" placeholder="Search" name="keyword">
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

	<h1>${pagingDto.currentPage}</h1>
	<h1>${pagingDto.startPageNum}</h1>

	<div class="d-flex justify-content-center">
	<ul class="pagination">
		<li class='page-item ${pagingDto.first ? "disabled" : ""}'>
			<a class="page-link" href="/?keyword=${pagingDto.keyword}&page=${pagingDto.currentPage -1}">previous</a></li>
			 <c:forEach var="num" begin="${pagingDto.startPageNum}" end="${pagingDto.lastPageNum}" step="1">
            <a class="page-link" href='?keyword=${pagingDto.keyword}&page=${num-1}'>${num}</a>
         </c:forEach>
			<li class='page-ite'>
				<a class='page-link ${pagingDto.last ? "disabled" : ""}' href="/?keyword=${pagingDto.keyword}&page=${pagingDto.currentPage+1}">Next</a></li>
	</ul>
	</div>


</div>

<%@ include file="../layout/footer.jsp"%>
