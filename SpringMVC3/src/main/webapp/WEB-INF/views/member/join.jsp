<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring-mvc03</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 <jsp:include page="../common/header.jsp"/>
<div class="container">
  <h2>Spring mvc03</h2>
  <div class="panel panel-default">
    <div class="panel-heading">회원가입</div>
    <div class="panel-body">
    	<form>
    		<table class="table table-bordered" style="text-align: center; border: 1px solid #dddddd;">
    			<tr>
    				<td style="width:110px; vertical-align:middle">아이디</td>
    				<td><input type="text" class="form-control" maxlength="20" placeholder="아이디를 입력하세요."/></td>
    				<td style="width:110px"><button class="btn btn-primary">중복확인</button></td>
    			</tr>
    		</table>
    	</form>
    </div>
     <div class="panel-footer">Yeong_Huns_SpringMVC03</div>
  </div>
</div>

</body>
</html>
    