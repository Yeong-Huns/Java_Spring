<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring-mvc02</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  	$(document).ready(function(){
  		loadList();
  	});
  	function loadList(){
  		$.ajax({
  			url: "boardList.do",
  			type: "get",
  			dataType: "json",
  			success: makeView,
  			error: function(){alert("error");}
  		});
  	}
  	function makeView(data){
  		
  		var listHtml = "<table class='table table-bordered'>";
  		listHtml+="<tr>";
  		listHtml+="<td>번호</td>";
  		listHtml+="<td>제목</td>";
  		listHtml+="<td>작성자</td>";
  		listHtml+="<td>작성일</td>";
  		listHtml+="<td>조회수</td>";
  		listHtml+="</tr>";
  		$.each(data, function(index,obj){
  			listHtml+="<tr>";
  	  		listHtml+="<td>"+obj.idx+"</td>";
  	  		listHtml+="<td><a href='javascript:goContent("+obj.idx+")'>"+obj.title+"</a></td>";
  	  		listHtml+="<td>"+obj.writer+"</td>";
  	  		listHtml+="<td>"+obj.indate+"</td>";
  	  		listHtml+="<td>"+obj.readCount+"</td>";
  	  		listHtml+="</tr>";
  	  		
  	  		listHtml+="<tr style='display:none' id='c"+obj.idx+"'>";
  	  		listHtml+="<td>내용</td>";
  	  		listHtml+="<td colspan='4'>";
  	  		listHtml+="<textarea rows='7' class='form-control'>"+obj.content+"</textarea>";
  	  		listHtml+="</td>";
  	  		listHtml+="</tr>";
  		});
  		listHtml+="<tr>";
  		listHtml+="<td colspan='5'>";
  		listHtml+="<button onclick='goForm()' class='btn btn-primary btn-sm'>글쓰기</button>"
  		listHtml+="</td>";
  		listHtml+="</tr>";
  		listHtml+="</table>";
  		$("#view").html(listHtml);
  		$("#view").css('display', 'block');
  		$("#wForm").css('display', 'none');
  	}
  	function goForm(){
  		$("#view").css('display', 'none');
  		$("#wForm").css('display', 'block');
  	}
  	function goList(){
  		$("#view").css('display', 'block');
  		$("#wForm").css('display', 'none');
  	}
  	function goInsert(){
  		//var title = $("#title").val();
  		//var content = $("#content").val();
  		//var writer = $("#writer").val();
  		
  		var fData=$("#frm").serialize(); //자동으로 forme데이타를 자동으로 가져옴
  		//alert(fData);
  		$.ajax({
  			url: "boardInsert.do",
  			type: "post",
  			data: fData,
  			success: loadList,
  			error: function(){alert("error")}
  		})
  		//$("#title").val("");
  		//$("#content").val("");
  		//$("#writer").val("");
  		$("#fclear").trigger("click");
  	}
  	function goContent(idx){
  		$("#c"+idx).css("display", "table-row");
  	}
  	
  </script>
</head>
<body>
 
<div class="container">
  <h2>Spring mvc02</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body" id ="view"></div>
    <div class="panel-body" id="wForm" style="display: none">
    	<form id="frm">
		<table class="table">
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" id="title" class="form-control"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="7" name="content" id="content" class="form-control"></textarea></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" id="writer" class="form-control"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" class="btn btn-success btn-sm" onclick="goInsert()">등록</button>
					<button type="reset" class="btn btn-warning btn-sm" id="fclear">취소</button>
					<button onclick='goList()' class="btn btn-primary btn-sm">목록으로</button>
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
    