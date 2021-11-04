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
		<div class="modal fade" id="idFindModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="">
						<h4 class="">아이디 찾기 완료</h4>
						<!-- <button type="button" class="btn-close" data-bs-dismiss="modal"></button>  -->
					</div>

					<!-- Modal body -->
					<div class="" id="" value="">${userEntity }</div>

					<!-- Modal footer -->
					<div class="">
						<button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="window.location.reload()">닫기</button>
					</div>
				</div>
			</div>
		</div>
</form>
<script>
/* async function findId(event, name, birth, contact) {
	event.preventDefault();
  	
	
	console.log(document.querySelector("#contact").value);
	console.log(document.querySelector("#birth").value);

	let IdFindDto = {
		   name: document.querySelector("#name").value,
		   contact: document.querySelector("#contact").value,
		   birth: document.querySelector("#birth").value
   };
	
	console.log(IdFindDto);
	let response = await fetch("http://localhost:8080/user/findId", {
		method: "post",
		body: JSON.stringify(IdFindDto),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	});
	
	let parseResponse = await response.json();
	console.dir(parseResponse);
	if(parseResponse.code == 1){
		$("#idFindModal").modal('show');
		$("#modal-body").html(parseResponse.body);
	}else{
		alert("업데이트 실패 : "+parseResponse.msg);
	}

} */

</script>

	<%@ include file="../layout/footer.jsp"%>