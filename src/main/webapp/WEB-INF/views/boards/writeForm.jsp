<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="mb-3 mt-3">
			<input id="title" type="text" class="form-control"
				placeholder="Enter title">
		</div>
		<div class="mb-3">
			<textarea id="content" class="form-control" rows="8"></textarea>
		</div>
		<button id="btnSave" type="button" class="btn btn-primary">글쓰기완료</button>
	</form>
</div>
<script>
$("#btnSave").click(()=>{
	save();

});


function save(){
	let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax("/boards", {
			type: "POST",
			dataType: "json",
			data: JSON.stringify(data),
			headers: {
				"Content-Type": "application/json"
			}
		}).done((res) => {
			if (res.code == 1) {
				//console.log(res);
				location.href = "/";
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

