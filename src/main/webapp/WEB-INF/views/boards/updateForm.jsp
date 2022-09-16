<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
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
$("btnUpdateBoard").click(()=>{
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
				location.reload(); // f5
			} else {
				alert("업데이트 실패");
			}
		});
});

</script>

<%@ include file="../layout/footer.jsp"%>

