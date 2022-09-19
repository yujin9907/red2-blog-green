<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<input id = "page" type="hidden" value="${sessionScope.referer.page}">
<input id = "keyword" type="hidden" value="${sessionScope.referer.keyword}">
<input id="id" type="hidden" value="${detailDto.boards.id}">


<div class="container">
    <br/> <br/>
    <c:choose>
        <c:when test="${empty principal}">
        </c:when>
        <c:otherwise>
            <div class="d-flex">

                <a href="/boards/${detailDto.boards.id}/updateForm" class="btn btn-warning">수정하러가기</a>
                <button id="btnDelete" class="btn btn-danger">삭제</button>

            </div>
        </c:otherwise>
    </c:choose>


    <br/>


    <div class="d-flex justify-content-between">
        <h3 id="title">${detailDto.boards.title}</h3>
        <div> <div id="lovecount"> 좋아요수 : ${detailDto.lovesDto.count}</div>
                <i id="iconHeart" class='${detailDto.lovesDto.checkLove ? "fa-solid":"fa-regular"} fa-heart'></i>
        </div>
    </div>
    <hr/>

    <div id="content">${detailDto.boards.content}</div>

</div>

<script>

    $("#iconHeart").css("cursor","pointer").click(() => {

        let isLovedState = $("#iconHeart").hasClass("fa-solid");
        if(isLovedState){
            deleteLove();
        }else {
            insertLove();
        }
        // insertOrDeleteLove(isLovedState); 이것보다 위에처럼 가독성 좋게 읽히도록
        // renderLove(isLovedState); 가독성을 위해 여기보단 아래 함수에 포함시킴
    });

    function insertLove(){
        let id = $("#id").val();

        $.ajax("/boards/"+id+"/loves", {
            type: "POST",
            dataType: "json",
        }).done((res) => {
            if (res.code == 1) {
                // 렌더, 부분 리로드
                // renderLove(isLovedState); db에 인서트하는 건데, 그림까지 그림 .. 잘못
                // 비동기 통신이기 때문에 return 1을 할 수 없음. 메인스레드 작동방식 때문에
                // 아작스라서 결국 여기서 그릴 수 밖에 없음.
                renderLove();
                // $("#div의 id").load(window.location.href + "#div의 id");
                // let count = $("#countLove").text();

                // $("#lovecount").load(location.href + ' #lovecount');

            } else {
                alert("실패");
            }
        });
    }

    function deleteLove(){

    }

    function renderLove(isLovedState){
        $("#iconHeart").removeClass("fa-regular");
        $("#iconHeart").addClass("fa-solid");
    }
    function renderCancelLoves(isLovedState){
        $("#iconHeart").removeClass("fa-solid");
        $("#iconHeart").addClass("fa-regular");
    }
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

