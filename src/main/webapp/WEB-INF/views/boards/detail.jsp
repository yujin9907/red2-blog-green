<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<input id = "page" type="hidden" value="${sessionScope.referer.page}">
<input id = "keyword" type="hidden" value="${sessionScope.referer.keyword}">


<div class="container">
    <br/> <br/>
    <c:choose>
        <c:when test="${empty principal}">
        </c:when>
        <c:otherwise>
            <div class="d-flex">

                <a href="/boards/${boards.id}/updateForm" class="btn btn-warning">수정하러가기</a>
                <button id="btnDelete" class="btn btn-danger">삭제</button>

            </div>
        </c:otherwise>
    </c:choose>


    <br/>


    <div class="d-flex justify-content-between">
        <input id="id" type="hidden" value="${boards.id}">
        <h3 id="title">${boards.title}</h3>
        <div> 좋아요수 : ${loves.loveCount}
        <c:choose>
            <c:when test="${check==true}">
                <i id="iconHeart" class="fa-solid fa-heart"></i>
            </c:when>
            <c:otherwise>
                <i id="iconHeart" class="fa-regular fa-heart"></i>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
    <hr/>

    <div id="content">${boards.content}</div>

</div>

<script>

    $("#iconHeart").css("cursor","pointer").click((event) => {

        let boardsId = $("#id").val();
        let sendType = "";
        let check = $("#iconHeart").hasClass("fa-regular");
        if(check==true){
            $("#iconHeart").removeClass("fa-regular");
            $("#iconHeart").addClass("fa-solid");
            sendType = "POST";
        } else {
            $("#iconHeart").removeClass("fa-solid");
            $("#iconHeart").addClass("fa-regular");
            sendType = "DELETE";
        }
        console.log($("#iconHeart"));

        $.ajax("/loves/"+boardsId, {
           type: sendType,
           dataType: "json"
        }).done((res) => {
            if (res.code == 1) {
                location.reload();
            } else {
                alert("에러");
            }
        });

    });
</script>

<script>
    $("#btnDelete").click(() => {
        deleteBoard();
    });
    function deleteBoard(){


        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        let id = $("#id").val();

        let page = $("#page").val();
        let keyword = $("#keyword").val();

        $.ajax("/boards/" + id, {
            type: "put",
            dataType: "json",
            data: JSON.stringify(data),
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            }
        }).done((res) => {
            if (res.code == 1) {
                //location.href= document.referrer;
                location.href="/?page="+page+"&keyword="+keyword;
            } else {
                alert("업데이트 실패");
            }
        });
    }
</script>
<%@ include file="../layout/footer.jsp" %>

