<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<c:set var="mvo" value="${SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<c:set var="auth" value="${SPRING_SECURITY_CONTEXT.authentication.authorities}"/>
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
      <security:authorize access="isAnonymous()">
      <ul class="nav navbar-nav navbar-right"> <!-- 오른쪽 정렬될 탭 -->
            <li><a href="${contextPath}/memLoginForm.do"><span class="glyphicon glyphicon-log-in"></span> &nbsp;로그인</a></li>
            <li><a href="${contextPath}/memJoin.do"><span class="glyphicon glyphicon-user"></span> &nbsp;회원가입</a></li>
      </ul>
      </security:authorize>
       <security:authorize access="isAuthenticated()">
      <ul class="nav navbar-nav navbar-right"> <!-- 오른쪽 정렬될 탭 -->
           <li><a href="memUpdateForm.do"><span class="glyphicon glyphicon-pencil"></span> &nbsp;회원정보수정</a></li>
            <li><a href="${contextPath}/memImageForm.do"><span class="glyphicon glyphicon-picture"></span> &nbsp;사진 등록</a></li>
            <li><a href="${contextPath}/memLogout.do"><span class="glyphicon glyphicon-log-out"></span> &nbsp;로그아웃</a></li>
           <c:if test="${empty mvo.member.memProfile}">
	  	  	<li><img class="img-circle" src="${contextPath}/resources/images/person.png" style="width: 50px;heigth:50px"/></li>
	      	</c:if>
	      	<c:if test="${!empty mvo.member.memProfile}">
	  	  	<li><img class="img-circle" src="${contextPath}/resources/upload/${mvo.member.memProfile}" style="width: 50px; heigth: 50px"/></li>
	      	</c:if>
          (
          <security:authorize access="hasRole('ROLE_USER')">U,</security:authorize>
          <security:authorize access="hasRole('ROLE_MANAGER')">M,</security:authorize>
          <security:authorize access="hasRole('ROLE_ADMIN')">A,</security:authorize>
          )
      </ul>
      </security:authorize>
    </div>
  </div>
</nav>