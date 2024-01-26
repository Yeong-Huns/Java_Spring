<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring-mvc02</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 
<div class="container">
  <h2>Spring mvc02</h2>
  <div class="panel panel-default">
    <div class="panel-heading">수정하기</div>
    <div class="panel-body">
    	<form action="../boardUpdate.do" method="post">
    	<input type="hidden" name="idx" value="${vo.idx}">
    		<table class="table">
    			<tr>
    				<td>제목</td>
    				<td><input type="text" name="title" value="${vo.title}" class="form-control"></td>
    			</tr>
    			<tr>
    				<td>내용</td>
    				<td><textarea rows="7" name="content" class="form-control">${vo.content}</textarea></td>
    			</tr>
    			<tr>
    				<td>작성자</td>
    				<td><input type="text" name="writer" value="${vo.writer}" class="form-control" readonly="readonly"></td>
    			</tr>
    			<tr>
    				<td colspan="2" align="center">
    					<button type="submit" class="btn btn-success btn-sm">수정하기</button>
    					<button type="reset" class="btn btn-warning btn-sm">취소</button>
    					<a href="../boardList.do" class="btn btn-primary btn-sm">목록으로</a>
    				</td>
    			</tr>
    		</table>
    	</form>
    </div>
     <div class="panel-footer">스프링 연습_Yeong_Huns</div>
  </div>
</div>

</body>
</html>
    