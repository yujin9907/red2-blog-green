//리팩토링
// 1. 파일로 빼기
// 2. 함수 밖 변수(전역변수) 맨위로 빼기
// 3. 함수 생성으로 코드 간결하게 -> 맨 밑으로 함수 빼기 ; 가독성 좋아짐 => 조인버튼을 클릭하면 조인함수가 실행됨 이런식으로
// 주의) el 표현식을 이래서 자바스크립트 안에 넣으면 안 됨(파일로 뺐을 때 이해할 수 없음)
// "/users/usernameSameCheck?username=" + username (유저세임체크) 수정 => `/users/usernameSameCheck?username=${username}` : jsp 밖으로 뺐음으로



let isUsernameSameCheck = false;

// 회원가입
$("#btnJoin").click(() => {
	join();
});

// 유저네임 중복 체크
$("#btnUsernameSameCheck").click(() => {
	checkUsername();
});

// 로그인
$("#btnLogin").click(() => {
	login();
});

$("#btnDelete").click(() => {
	resing();
});


$("#btnUpdate").click(() => {
	update();
});




// 함수

function join() {
	if (isUsernameSameCheck == false) {
		alert("유저네임 중복 체크를 진행해주세요");
		return;
	}


	// 0. 통신 오브젝트 생성
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		password2: $("#password2").val(),
		email: $("#email").val()
	};

	if (data.password != data.password2) {
		alert("비밀번호를 확인해주세요");
		return;
	}


	$.ajax("/join", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json"
		}
	}).done((res) => {
		if (res.code == 1) {
			// console.log(res);
			location.href = "/";
		}
	});
}

function checkUsername() {
	// 0. 통신 오브젝트 생성 (Get 요청은 body가 없다.)

	// 1. 사용자가 적은 username 값을 가져오기
	let username = $("#username").val();

	// 2. Ajax 통신
	$.ajax(`/users/usernameSameCheck?username=${username}`, {
		type: "GET",
		dataType: "json",
		async: true
	}).done((res) => {
		console.log(res);
		if (res.code == 1) {
			if (res.data == false) {
				alert("아이디가 중복되지 않았습니다.");
				isUsernameSameCheck = true;
			} else {
				alert("아이디가 중복되었어요. 다른 아이디를 사용해주세요.");
				isUsernameSameCheck = false;
				$("#username").val("");
			}
		}
	});
}

function login() {
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		remember: $("#remember").prop("checked")
	};

	$.ajax("/login", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/";
		} else {
			alert("로그인 실패, 아이디 패스워드 확인");
		}
	});
}



function resing() {
	let id = $("#id").val();

	$.ajax("/s/api/users/" + id, {
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

function update() {
	let data = {
		password: $("#password").val(),
		email: $("#email").val()
	};
	let id = $("#id").val();
	$.ajax("/s/api/users/" + id, {
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
}