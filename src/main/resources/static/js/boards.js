$("#btnSave").click(()=>{
    save();
});

$("#btnUpdateBoard").click(()=>{
    updateBoard();
});

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

    $.ajax("/s/boards/" + id, {
        type: "DELETE",
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

function insertLove(){
    let id = $("#id").val();

    $.ajax("/s/api/boards/"+id+"/loves", {
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
            // $("#lovesID").val(res.data.id);

            let count = $("#lovecount").text();
            $("#lovecount").text(Number(count)+1);
            $("#lovesId").val(res.data.id);

        } else {
            alert("실패");
        }
    });
}

function deleteLove(){
    let id = $("#id").val();
    let lovesId = $("#lovesId").val();

    $.ajax("/s/api/boards/"+id+"/loves/"+lovesId, {
        type: "DELETE",
        dataType: "json",
    }).done((res) => {
        if (res.code == 1) {

            renderCancelLoves();
            //$("#lovecount").load(location.href + ' #lovecount');
            let count = $("#lovecount").text();
            $("#lovecount").text(Number(count)-1);

        } else {
            alert("실패");
        }
    });
}

function renderLove(){
    $("#iconHeart").removeClass("fa-regular");
    $("#iconHeart").addClass("fa-solid");
}
function renderCancelLoves(){
    $("#iconHeart").removeClass("fa-solid");
    $("#iconHeart").addClass("fa-regular");
}
function updateBoard(){
    let data = {
        title: $("#title").val(),
        content: $("#content").val()
    };
    let id = $("#id").val();

    $.ajax("/s/boards/" + id, {
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

function save(){
    let data = {
        title: $("#title").val(),
        content: $("#content").val()
    };

    $.ajax("/s/boards", {
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



