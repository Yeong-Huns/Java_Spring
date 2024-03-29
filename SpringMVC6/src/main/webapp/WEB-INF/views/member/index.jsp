<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>spring-mvc06</title>
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
  
 <div class="panel panel-default">
    <div>
    	<img src="${contextPath}/resources/images/mainImage.jpg" style="width: 100%; height: 500px;"/>
    </div>
    <div class="panel-heading">		
    <c:if test="${empty mvo}">
  <label><span class="glyphicon glyphicon-exclamation-sign"></span> &nbsp;비회원 상태에선 기능이 제한됩니다.</label>
  </c:if>
  <c:if test="${!empty mvo}">
  <label>Welcome! ${mvo.memName}, 다시 만나서 반가워요.</label>
  </c:if>
  </div>
    <div class="panel-body">
    <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home">Home</a></li>
    <li><a data-toggle="tab" href="#menu1">게시판</a></li>
    <li><a data-toggle="tab" href="#menu2">공지사항</a></li>
  </ul>

  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      <h3>HOME</h3>
      <p>main_content</p>
    </div>
    <div id="menu1" class="tab-pane fade">
      <h3>게시판</h3>
      <p>게시판_content</p>
    </div>
    <div id="menu2" class="tab-pane fade">
      <h3>공지사항</h3>
      <p>공지사항_content</p>
    </div>
  </div>
    </div>
     <div class="panel-footer">
     스프링 연습_Yeong_Huns</div>
   </div>
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
