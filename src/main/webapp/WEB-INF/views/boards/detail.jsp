<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />


		<div class="d-flex">
		
			<a href="#" class="btn btn-warning">수정하러가기</a>

			<form>
				<button class="btn btn-danger">삭제</button>
			</form>
		</div>


	<br />
	디플렉스 : 안에있는 돔이 인라인블럭으로 바뀜 => 스페이스 비트윈으로 바깥쪽으로 몰기(부트스트랩)
	https://fontawesome.com/ 사용해서 하트 아이콘 넣을 거임 : 서드퍼티라이브러리(외부에서 제공하느 라이브러리)
	
	<div class="d-flex justify-content-between">
		<h3>${boards.title}</h3>
		<div><i class="fa-regular fa-heart"></i></div>
	</div>
	<hr />

	<div>${boards.content}</div>


</div>

<%@ include file="../layout/footer.jsp"%>

