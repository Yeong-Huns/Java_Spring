<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  	 $(document).ready(function(){
	  if(${!empty msgType}){
		  $("#messageType").attr("class", "modal-content panel-success");			  
		  $("#myMessage").modal("show")
	  }
  });
  </script>
</head>
<body>
<div class="container">
<jsp:include page="../common/header.jsp"/>
  <h3>SpringMVC03</h3>
  <p>Nav_Bar 테스트입니다. 크기를 조절해보세요. Nav_Bar 테스트입니다. 크기를 조절해보세요. Nav_Bar 테스트입니다. 크기를 조절해보세요.
  <p>Spring MVC03</p>
</div>
 
 
 <!-- 성공 메세지 출력(modal) -->
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
 
 
</body>
</html>
