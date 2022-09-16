<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />


		<div class="d-flex">
		
			<a href="/boards/${boards.id}/updateForm" class="btn btn-warning">수정하러가기</a>

			<form>
				<button id="btnDelete" class="btn btn-danger">삭제</button>
			</form>
		</div>


	<br />
	디플렉스 : 안에있는 돔이 인라인블럭으로 바뀜 => 스페이스 비트윈으로 바깥쪽으로 몰기(부트스트랩)
	https://fontawesome.com/ 사용해서 하트 아이콘 넣을 거임 : 서드퍼티라이브러리(외부에서 제공하느 라이브러리)
	
	<div class="d-flex justify-content-between">
		<input id="id" type="hidden" value="${boards.id}">
		<h3 id="title">${boards.title}</h3>
		<div> 좋아요수 : 10 <i id="iconHeart" class="fa-regular fa-heart"></i></div>
	</div>
	<hr />

	<div id="content">${boards.content}</div>

</div>

<script>
$("#iconHeart").click((event)=>{
	console.log(event.target); // 하트를 채워진하트로 햇다 빈하트로 햇다
	// 제이쿼리 css 문법 참고해서
	// 튜토리얼 -> css 에서, 리무브 애드 리무브 애드 해야 됨.
	
	let check = ${"#iconHeart"}.hasClass("fa-reqular");
	
	if(체크가 트루면){
		리무브
		애드
	} else 
		애드
		리무브 뭐 이런 식으로 작성
});
</script>

<script>
$("#btnDelete").click(()=>{
	let id = $("#id").val();

	$.ajax("/boards/" + id, { // "/users/${users.id}" 이렇게 바로 적으면 안 됨. 파일을 따로 빼는 순간 안 먹음. jsp 파일에서만 작동하기 때문에
		// js 나중에 빼서 쓰고 html 파일에 바인딩만 시켜서 사용함
		// 규칙 스크립트 안에는 el 표현식을 쓰지 않음
		type: "DELETE",
		dataType: "json"
	}).done((res) => {
		if (res.code == 1) {
			alert("회원탈퇴완료");
			location.href = "/";
		} else {
			alert("회원탈퇴실패");
		}
	});
}
});


</script>

<%@ include file="../layout/footer.jsp"%>

