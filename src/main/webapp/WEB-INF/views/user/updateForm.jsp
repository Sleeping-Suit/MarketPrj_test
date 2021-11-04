<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!-- <link href="/resources/css/user.css" rel="stylesheet" /> -->

<body class="bgcolor">
	<form method="post"
		action="/update(event, ${sessionScope.principal.id})">
		<div class="container login">
			<div class="insert">
				<div class="login_pw">
					<h4>Password</h4>
					<input type="password" name="password"
						value="${sessionScope.principal.password}"
						placeholder="새롭게 사용할 패스워드를 입력하세요" readonly="readonly">
				</div>
				<div class="login_pw">
					<h4>New Password</h4>
					<input type="password" name="password" placeholder="새롭게 사용할 패스워드를 입력하세요">
				</div>
				<br>
				<div class="login_id">
					<h4>Contact</h4>
					<input type="text" name="contact"
						value="${sessionScope.principal.contact}"
						placeholder="변경할 연락처를 기재해주세요">
				</div>
				<br>
				<div class="create">
					<input class="but3" type="button" value="수정완료" onclick="">
					<input class="but4" type="button" value="수정취소"
						onclick="formCheck(this.form)">
				</div>
			</div>
		</div>
	</form>
	<script>
<script>
async function update(event, id){ 
      event.preventDefault();

      let userUpdateDto = {
            password: document.querySelector("#password").value
            contact: document.querySelector("#contact").value
      };

      let response = await fetch("http://localhost:8080/user/"+id, {
         method: "put",
         body: JSON.stringify(userUpdateDto),
         headers: {
            "Content-Type": "application/json; charset=utf-8"
         }
      });
      
      let parseResponse = await response.json();

      if(parseResponse.code == 1){
         alert("업데이트 성공");
         location.href = "/user/"+id
      }else{
         alert("업데이트 실패 : "+parseResponse.msg);
      }
}
</script>


	<%@ include file="../layout/footer.jsp"%>