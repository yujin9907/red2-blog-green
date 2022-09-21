<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<input id = "page" type="hidden" value="${sessionScope.referer.page}">
<input id = "keyword" type="hidden" value="${sessionScope.referer.keyword}">
<input id="id" type="hidden" value="${detailDto.id}">
<input id="lovesId" type="hidden" value = "${detailDto.lovesId}">


<div class="container">
    <br/> <br/>
    <c:if test="${detailDto.usersId == sessionScope.principal.id}">
            <div class="d-flex">
                <a href="/s/boards/${detailDto.id}/updateForm" class="btn btn-warning">수정하러가기</a>
                <button id="btnDelete" class="btn btn-danger">삭제</button>
            </div>
    </c:if>


    <br/>


    <div class="d-flex justify-content-between">
        <h3 id="title">${detailDto.title}</h3>
        <div class="d-flex">  좋아요수 : &nbsp; <div id="lovecount"> ${detailDto.loveCount} </div>
                &nbsp;&nbsp;<i id="iconHeart" class='${detailDto.loved ? "fa-solid":"fa-regular"} fa-heart'></i>
        </div>
    </div>
    <hr/>

    <div id="content">${detailDto.content}</div>

</div>

<script src="/js/boards.js"></script>


<script>

</script>
<%@ include file="../layout/footer.jsp" %>

