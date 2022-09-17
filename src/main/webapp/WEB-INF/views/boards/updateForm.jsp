<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input id="id" type="hidden" value="${boards.id}">
		<div class="mb-3 mt-3">
			<input id="title" type="text" class="form-control" placeholder="Enter title" value="${boards.title}">
		</div>
		<div class="mb-3">
			<textarea id="content" class="form-control" rows="8" >${boards.content}</textarea>
		</div>
		<button id="btnUpdateBoard" type="button" class="btn btn-primary">수정완료</button>
	</form>
</div>

<script>
	$("#btnUpdateBoard").click(()=>{
		updateBoard();
	});
	function updateBoard(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		let id = $("#id").val();

		$.ajax("/boards/" + id, {
			type: "put",
			dataType: "json",
			data: JSON.stringify(data),
			headers: {
				"Content-Type": "application/json; charset=utf-8"
			}
		}).done((res) => {
			if (res.code == 1) {
				location.href="/";
			} else {
				alert("업데이트 실패");
			}
		});
	}
</script>

<script>
	// 서머노트만 content 아이디로 바꿔주면 끝
	$('#content').summernote({
		height: 400
	});
</script>

<%@ include file="../layout/footer.jsp"%>

