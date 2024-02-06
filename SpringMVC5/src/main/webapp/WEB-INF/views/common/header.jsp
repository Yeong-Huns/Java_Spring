<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header"> <!-- data-toggle / data-target #myNavbar 를 찾아가서 열림 / (펼쳐져있을땐 동작 x) -->
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="${contextPath}">스프링 홈</a> 
    </div>
    <div class="collapse navbar-collapse" id="myNavbar"> <!-- myNavbar를 누르기 전까지 닫혀져있겠다는 뜻-->
      <ul class="nav navbar-nav">
        <li class="active"><a href="${contextPath}">Home</a></li> <!-- 선택되어있는 메뉴탭 -->
        <li><a href="boardMain.do">게시판</a></li>
        <li><a href="#">Page 1</a></li>
      </ul>
      <c:if test="${empty mvo}">
      <ul class="nav navbar-nav navbar-right"> <!-- 오른쪽 정렬될 탭 -->
            <li><a href="${contextPath}/memLoginForm.do"><span class="glyphicon glyphicon-log-in"></span> &nbsp;로그인</a></li>
            <li><a href="${contextPath}/memJoin.do"><span class="glyphicon glyphicon-user"></span> &nbsp;회원가입</a></li>
      </ul>
      </c:if> 
       <c:if test="${!empty mvo}">
      <ul class="nav navbar-nav navbar-right"> <!-- 오른쪽 정렬될 탭 -->
           <li><a href="memUpdateForm.do"><span class="glyphicon glyphicon-pencil"></span> &nbsp;회원정보수정</a></li>
            <li><a href="${contextPath}/memImageForm.do"><span class="glyphicon glyphicon-picture"></span> &nbsp;사진 등록</a></li>
            <li><a href="${contextPath}/memLogout.do"><span class="glyphicon glyphicon-log-out"></span> &nbsp;로그아웃</a></li>
           <c:if test="${empty mvo.memProfile}">
	  	  	<li><img class="img-circle" src="${contextPath}/resources/images/person.png" style="width: 50px;heigth:50px"/></li>
	      	</c:if>
	      	<c:if test="${!empty mvo.memProfile}">
	  	  	<li><img class="img-circle" src="${contextPath}/resources/upload/${mvo.memProfile}" style="width: 50px; heigth: 50px"/></li>
	      	</c:if>
          (
          <c:forEach items="${mvo.authList}" var="authVo">
              <c:if test="${authVo.auth eq 'ROLE_USER'}">U</c:if>
              <c:if test="${authVo.auth eq 'ROLE_MANAGER'}">M</c:if>
              <c:if test="${authVo.auth eq 'ROLE_ADMIN'}">A</c:if>
          </c:forEach>
          )
      </ul>
      </c:if> 
    </div>
  </div>
</nav>