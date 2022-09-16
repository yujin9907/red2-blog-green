<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form >
		<input id="id" type="hidden" value="${principal.id}"> <!-- 스크립트에 el 표현식 대신 사용하기 위해서. 세션에 값이 있는데 왜? -->
		<div class="mb-3 mt-3">
			<input id="password"
				type="text" class="form-control"
				placeholder="Enter password" value="${principal.password}">
		</div>
		<div class="mb-3">
			<input id="email"
				type="text" class="form-control" 
				placeholder="Enter email" value="${principal.email}">
		</div>
		<button  id="btnUpdate" type="button" class="btn btn-primary">수정</button>
	</form>
	<button id="btnDelete" type="button" class="btn btn-primary">회원탈퇴</button>
</div>

<script src="/js/users.js">
</script>

<%@ include file="../layout/footer.jsp"%>

