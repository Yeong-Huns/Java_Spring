<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring-mvc03</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
	  if(${!empty msgType}){
		  $("#messageType").attr("class", "modal-content panel-warning");			  
		  $("#myMessage").modal("show")
	  }
  });
  function registerCheck() {
	var memId = $("#memId").val();
	  $.ajax({
		  url: "${contextPath}/memRegisterCheck.do",
		  type: "get",
		  data: {"memId": memId},
		  success: function(result){
			  //중복여부 출력
			  if(result==1){
				  $("#checkMessage").html("사용할 수 있는 아이디입니다.");
				  $("#checkType").attr("class","modal-content panel-success");
			  }else{
				  $("#checkMessage").html("사용할 수 없는 아이디입니다.");
				  $("#checkType").attr("class","modal-content panel-warning");
			  }
			  $("#myModal").modal("show");
		  },
		  error: function(){alert("CheckId Error!!")}
	  });
  }
  function passwordCheck() {
	var Pwd1 = $("#memPwd1").val();
	var Pwd2 = $("#memPwd2").val();
	if(Pwd1 != Pwd2){
		$("#PwdMessage").css("color", "red");
		$("#PwdMessage").html("비밀번호가 서로 일치하지 않습니다.");
	}else{
		$("#PwdMessage").css("color", "blue")
		$("#PwdMessage").html("비밀번호가 서로 일치합니다.");
		$("#memPwd").val(Pwd1);
  }
  }
  function goInsert(){
	  var memAge=$("#memAge").val();
	  if(memAge==null||memAge=="" || memAge==0){
		  alert("나이를 입력하세요.");
		  return false;
	  }
	  document.frm.submit();
  }
  </script>
</head>
<body>
<div class="container">
 <jsp:include page="../common/header.jsp"/>
  <h2>Spring mvc03</h2>
  <div class="panel panel-default">
    <div class="panel-heading">회원가입</div>
    <div class="panel-body">
    	<form method="post" action="${contextPath}/memRegister.do" name="frm">
    	<input type="hidden" name="memPwd" id="memPwd" value=""/>
    		<table class="table table-bordered" style="text-align: center; border: 1px solid #dddddd;">
    			<tr>
    				<td style="width:110px; vertical-align:middle">아이디</td>
    				<td><input id="memId" name="memId" type="text" class="form-control" maxlength="20" placeholder="아이디를 입력하세요."/></td>
    				<td style="width:110px"><button type="button" class="btn btn-primary" onclick="registerCheck()">중복확인</button></td>
    			</tr>
    			<tr>
    				<td style="width:110px; vertical-align:middle">비밀번호</td>
    				<td colspan="2"><input onkeyup="passwordCheck()" id="memPwd1" name="memPwd1" type="password" class="form-control" maxlength="20" placeholder="비밀번호를 입력하세요."/></td>
    			</tr>
    			<tr>
    				<td style="width:110px; vertical-align:middle">비밀번호확인</td>
    				<td colspan="2"><input onkeyup="passwordCheck()" id="memPwd2" name="memPwd2" type="password" class="form-control" maxlength="20" placeholder="비밀번호를 다시 입력하세요."/></td>
    			</tr>
    			<tr>
    				<td style="width:110px; vertical-align:middle">성 명</td>
    				<td colspan="2"><input id="memName" name="memName" type="text" class="form-control" maxlength="20" placeholder="이름을 입력하세요."/></td>
    			</tr>
    			<tr>
    				<td style="width:110px; vertical-align:middle">나 이</td>
    				<td colspan="2"><input  id="memAge" name="memAge" type="number" class="form-control" maxlength="20" placeholder="나이를 입력하세요."/></td>
    			</tr>
    			<tr>
    				<td style="width:110px; vertical-align:middle">성 별</td>
    				<td colspan="2"><div class="form-group" style="text-align:center; margin:0 auto">
    					<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-success active">
								<input name="memGender" type="radio" autocomplete="off" value="남자" checked/>남자
							</label>
							<label class="btn btn-warning">
								<input name="memGender" type="radio" autocomplete="off" value="여자"/>여자
							</label>
    					</div>
    				</div></td>
				</tr>
    			<tr>
    				<td style="width:110px; vertical-align:middle">이메일</td>
    				<td colspan="2"><input id="memEmail" name="memEmail" type="email" class="form-control" maxlength="20" placeholder="이메일을 입력하세요."/></td>
    			</tr>
    			<tr>
    				<td colspan="3" style="text-align:left">
    				<span id="PwdMessage" style="color: red" ></span>
    				<input type="button" class="btn btn-primary btn-sm pull-right" value="등록" onclick="goInsert()">
    				</td>
    			</tr>
    		</table>s
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			//_csrf.parameterName : spring header
			//_csrf.token : spring token
    	</form>
    </div>
    <!-- 다이얼 로그 창(모달) -->
    <!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  
  <div class="modal-dialog">
    <!-- Modal content-->
    <div id="checkType" class="modal-content panel-info">
      <div class="modal-header panel-heading">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">메세지 확인</h4>
      </div>
      <div class="modal-body">
        <p id="checkMessage"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
	<!-- content END -->
  </div>

</div>

	<!-- 실패 메세지 출력(modal) -->
	<!-- 다이얼 로그 창(모달) -->
	    <!-- Modal -->
	<div id="myMessage" class="modal fade" role="dialog">
	  
	  <div class="modal-dialog">
	    <!-- Modal content-->
	    <div id="messageType" class="modal-content panel-info">
	      <div class="modal-header panel-heading">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">${msgType}</h4>
	      </div>
	      <div class="modal-body">
	        <p>${msg}</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
		<!-- content END -->
	  </div>

</div>
     <div class="panel-footer">Yeong_Huns_SpringMVC03</div>
  </div>
</div>

</body>
</html>
    