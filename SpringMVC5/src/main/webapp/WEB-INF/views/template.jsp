<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Spring-mvc05</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 
<div class="container">
  <h2>Spring mvc01</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body">

      <table class="table">
        <tr>
          <td>번호</td>
          <td>제목</td>
          <td>내용</td>
          <td>작성자</td>
          <td>작성일</td>
          <td>조회수</td>
        </tr>
        <c:if test="${list.size() eq 0}">conn error</c:if>
        <h2>TEST</h2>
        <c:forEach var="vo" items="${list}">
          <tr>
            <td>${vo.idx}</td>
            <td>${vo.title}</td>
            <td>${vo.content}</td>
            <td>${vo.writer}</td>
            <td>${vo.indate}</td>
            <td>${vo.readCount}</td>
          </tr>
        </c:forEach>
      </table>

    </div>
     <div class="panel-footer">스프링 연습_Yeong_Huns</div>
  </div>
</div>

</body>
</html>
    