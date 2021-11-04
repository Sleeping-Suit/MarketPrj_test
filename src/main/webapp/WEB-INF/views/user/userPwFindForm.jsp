<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!-- <link href="/resources/css/user.css" rel="stylesheet" /> -->

<body class="bgcolor">
	<form method="post" action="">
		<div class="container login">
			<div class="insert">
				<div class="login_id">
					<h4>Name</h4>
					<input type="text" name="name" placeholder="이름을 입력하세요">
				</div>
				<div class="login_id">
					<h4>Birth</h4>
					<input type="text" name="birth" placeholder="생년월일을 기재하세요">
				</div>
				<br>
				<div class="login_id">
					<h4>Contact</h4>
					<input type="text" name="contact" placeholder="통화가능한 연락처를 기재하세요">
				</div>
				<br>
				<div style="text-align: center; color: white;">※ 빈칸이 없도록 작성하여야
					정보를 찾을 수 있습니다.</div>
				<br>
				<div class="create">

					<input class="but3" type="submit" value="찾기" onclick="idpw_search()"> 
					<input class="but4" type="button" value="취소" onclick="history.back()">
				</div>
			</div>
		</div>
		<!-- The Modal -->
		<!-- The Modal -->
	<div class="modal fade" id="pwdFindModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">비밀번호 변경하기</h4>
					<!-- <button type="button" class="btn-close" data-bs-dismiss="modal"></button>  -->
				</div>

				<!-- Modal body -->
				<form onsubmit="changePw(event)">
					<div class="modal-body">
						<label for="password">비밀번호</label> 
						<input type="password" class="form-control inputs" id="m_password" placeholder="패스워드를 입력하세요" name="m_password" required>
					</div>
				
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="submit" class="btn btn-success">변경</button>
					<button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="window.location.reload()">닫기</button>
				</div>
				</form>	
			</div>
		</div>
	</div>
</form>
<script>
async function findPw(event) {
	event.preventDefault();
  	
	
	console.log(document.querySelector("#name").value);
	console.log(document.querySelector("#birth").value);
	console.log(document.querySelector("#contact").value);

	let IdFindDto = {
		   username: document.querySelector("#username").value,
		   name: document.querySelector("#name").value,
		   email: document.querySelector("#contact").value,
		   birth: document.querySelector("#birth").value
   };
	
	console.log(IdFindDto);

	let response = await fetch("http://localhost:8080/user/findPw", {
		method: "post",
		body: JSON.stringify(IdFindDto),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	});
	
	let parseResponse = await response.json();
	console.dir(parseResponse);
	if(parseResponse.code == 1){
		$("#pwdFindModal").modal('show');
		
	}else{
		alert("업데이트 실패 : "+parseResponse.msg);
	}

}
</script>
<script>
async function changePw(event) {
	event.preventDefault();
	
	console.log(document.querySelector("#m_password").value);
	
	let pwChange = {
		   password: document.querySelector("#m_password").value
   };
	
	console.log(pwChange);
	
	let response = await fetch("http://localhost:8080/user/changePw", {
		method: "put",
		body: JSON.stringify(pwChange),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	});
	
	let parseResponse = await response.json();
	console.dir(parseResponse);

	if(parseResponse.code == 1){
		alert(parseResponse.msg);
		//location.href= "/user/loginForm";
		
	}else{
		alert("업데이트 실패 : "+parseResponse.msg);
	}

}

</script>

	<%@ include file="../layout/footer.jsp"%>