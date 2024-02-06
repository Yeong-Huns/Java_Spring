<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring-mvc05</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
	  var header = "${_csrf.headerName}";
	  var token = "${_csrf.token}";
  	$(document).ready(function(){
  		loadList();
  	});
  	function loadList(){
  		$.ajax({
  			url: "board/all",
  			type: "get",
  			dataType: "json",
  			success: makeView,
  			error: function(){ alert("makeView 생성 ERROR"); }
  		});
  	}
  	function makeView(data){
  		var listHtml = "<table class='table table-bordered'>"
  		listHtml += "<tr>"
  		listHtml += "<td>번호</td>"
  		listHtml += "<td>제목</td>"
  		listHtml += "<td>작성자</td>"
  		listHtml += "<td>작성일</td>"
  		listHtml += "<td>조회수</td>"
  		listHtml += "</tr>"
  		
  		$.each(data, function(index, obj){
  			listHtml+="<tr>";
  			listHtml+="<td>"+obj.idx+"</td>";
  			listHtml+="<td id='t"+obj.idx+"'><a href='javascript:goContent("+obj.idx+")'>"+obj.title+"</a></td>";
  			listHtml+="<td>"+obj.writer+"</td>";
  			listHtml+="<td>"+obj.indate.split(" ")[0]+"</td>";
  			listHtml+="<td id='cnt"+obj.idx+"'>"+obj.readCount+"</td>";
  			listHtml+="</tr>";
  			
  			listHtml+="<tr id='contentview"+obj.idx+"'style='display:none'>"
  			listHtml+="<td>내용</td>"
  			listHtml+="<td colspan='4'>"
  			listHtml+="<textarea rows='7' class='form-control' id='up"+obj.idx+"'></textarea>"
			listHtml+="<br/>"  			
			if("${mvo.memId}"==obj.writer){
  			listHtml+="<span id='sp"+obj.idx+"'><button class='btn btn-primary btn-sm' onclick='updateForm("+obj.idx+")'>수정하기</button></span>&nbsp;"
  			listHtml+="<button class='btn btn-danger btn-sm' onclick='goDelete("+obj.idx+")'>삭제하기</button>"
			}
  			listHtml+="</td>"
  			listHtml+="</tr>"
  		});
  		//로그인을 해야 볼수있도록 해야함 (session = "mvo")
  		if(${!empty mvo}){
  		listHtml+="<tr>";
  		listHtml+="<td colspan='5'>";
  		listHtml+="<button class='btn btn-primary btn-sm' onclick='goform()'>글쓰기</button>"
  		listHtml+="</td>";
  		listHtml+="</tr>";
  		}
  		if(${empty mvo}){
  			listHtml+="<tr>";
  	  		listHtml+="<td colspan='5'>";
  	  		listHtml+="<span class='glyphicon glyphicon-exclamation-sign'></span> &nbsp;비회원 상태에선 기능이 제한됩니다."
  	  		listHtml+="</td>";
  	  		listHtml+="</tr>";
  		}
  		//
  		listHtml+="</table>";	
  		$("#views").html(listHtml);
  		$("#wform").css("display", "none");
  		$("#views").css("display", "block");	
  	}
  	function goContent(idx){
  		if( $("#contentview"+idx).css("display")=="none" ){
  			$.ajax({
  				url: "board/"+idx,
  				type: "put",
  				data: {"idx": idx},
  				dataType: "json",
				beforeSend: function (xhr){
					  xhr.setRequestHeader(header,token);
				},
  				success: function(data){
  					$("#cnt"+idx).text(data.readCount)
  				},
  				error: function(){ alert("조회수 증가 ERROR!"); }
  			});
  			$.ajax({
  				url: "board/"+idx,
  				type: "get",
  				data: {"idx": idx},
  				dataType: "json",
  				success: function(data){
  					$("#up"+idx).val(data.content)
  				},
  				error: function(){ alert("getContent 에러!"); }
  			});
  			$("#contentview"+idx).css("display","table-row");
  			$("#up"+idx).attr("readonly", true);
  		}else{
  			$("#contentview"+idx).css("display","none");
  		}
  	}
  	function goform(){
  		$("#wform").css("display", "block");
  		$("#views").css("display", "none");
  	}
  	function goList(){
  		$("#wform").css("display", "none");
  		$("#views").css("display", "block");	
  	}
  	function goInsert(){
  		var fData = $("#frm").serialize();
		  console.log(fData)
  		$.ajax({
  			url: "board/new",
  			type: "post",
  			data: fData,
			beforeSend:function (xhr){
				  xhr.setRequestHeader(header, token)
			},
  			success: loadList,
  			error: function(){ alert("글 작성 에러!"); }
  		});
  		$("#formClear").trigger("click");
  	}
  	function goDelete(idx){
  		$.ajax({
  			url: "board/"+idx,
  			type: "delete",
  			data: {"idx": idx},
			beforeSend:function (xhr){
				  xhr.setRequestHeader(header, token)
			},
  			success: loadList,
  			error: function(){ alert("Delete 에러!"); }
  		});
  	}
  	function updateForm(idx){
  		$("#up"+idx).attr("readonly", false)
  		var title = $("#t"+idx).text();
  		var newInput = "<input id='nt"+idx+"' type='text' class='form-control' value='"+title+"'>"
  		$("#t"+idx).html(newInput);
  		var addHtml = "<button class='btn btn-success btn-sm' onclick='goUpdate("+idx+")'>수정완료</button>&nbsp;"
  		addHtml+="<button class='btn btn-warning btn-sm' onclick='loadList()'>목록으로</button>&nbsp;"
  		$("#sp"+idx).html(addHtml);
  	}
  	function goUpdate(idx){
  		var title = $("#nt"+idx).val();
  		var content = $("#up"+idx).val();
  		console.log("title"+idx)
  		console.log("content"+idx)
  		$.ajax({
  			url: "board/update",
  			type: "put",
  			contentType: 'application/json;charset=utf-8',
  			data: JSON.stringify({'idx': idx, 'title': title, 'content': content}),
			beforeSend:function (xhr){
				  xhr.setRequestHeader(header,token)
			},
  			success: loadList,
  			error: function(){alert("글 수정 오류!!")}
  		});
  	}
  </script>
</head>
<body>
<div class="container">
<jsp:include page="../common/header.jsp"/>
  <h2>회원게시판</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body" id="views"></div>
    <div class="panel-body" id="wform" style="display:none">
    <form id="frm">
		<input type="hidden" name="memId" id="memId" value="${mvo.memId}"/>
    	<table class="table">
    		<tr>
    			<td>제목</td>
    			<td><input type='text' class='form-control' id='title' name='title'></td>
    		</tr>
    		<tr>
    			<td>내용</td>
    			<td><textarea rows='7' class='form-control' id='content' name='content'></textarea></td>
    		</tr>
    		<tr>
    			<td>작성자</td>
    			<td><input type='text' class='form-control' id='writer' name='writer' value='${mvo.memId}' readonly></td>
    		</tr>
    	<tr>
    		<td colspan='2' align='center'>
    			<button type="button" class='btn btn-success btn-sm' onclick="goInsert()">등록</button>
    			<button type="reset" class='btn btn-warning btn-sm' id="formClear">취소</button>
    			<button class ='btn btn-primary btn-sm' onclick='goList()'>목록으로</button>
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
    