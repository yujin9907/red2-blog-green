<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="mb-3 mt-3">
			<input type="text" class="form-control" placeholder="Enter title" value="제목입니다">
		</div>
		<div class="mb-3">
			<textarea class="form-control" rows="8" >내용입니다</textarea>
		</div>
		<button id="btnUpdateBoard" type="button" class="btn btn-primary">수정완료</button>
	</form>
</div>

<script>
let data = {
		password: $("#password").val(),
		email: $("#email").val()
	};
	let id = $("#id").val();
	$.ajax("/users/" + id, { // "/users/${users.id}" 이렇게 바로 적으면 안 됨. 파일을 따로 빼는 순간 안 먹음. jsp 파일에서만 작동하기 때문에
		// js 나중에 빼서 쓰고 html 파일에 바인딩만 시켜서 사용함
		// 규칙 스크립트 안에는 el 표현식을 쓰지 않음
		type: "put",
		dataType: "json", // 받을 거
		data: JSON.stringify(data), // 줄 거
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			location.reload(); // f5
		} else {
			alert("업데이트 실패");
		}
	});
</script>

<%@ include file="../layout/footer.jsp"%>

