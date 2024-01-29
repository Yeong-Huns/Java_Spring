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
  			url: "board/all",
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
  	  		listHtml+="<td id='t"+obj.idx+"'><a href='javascript:goContent("+obj.idx+")'>"+obj.title+"</a></td>";
  	  		listHtml+="<td>"+obj.writer+"</td>";
  	  		listHtml+="<td>"+obj.indate.split(" ")[0]+"</td>";
  	  		listHtml+="<td id='cnt"+obj.idx+"'>"+obj.readCount+"</td>";
  	  		listHtml+="</tr>";
  	  		
  	  		listHtml+="<tr style='display:none' id='c"+obj.idx+"'>";
  	  		listHtml+="<td>내용</td>";
  	  		listHtml+="<td colspan='4'>";
  	  		listHtml+="<textarea rows='7' class='form-control' readonly id='ta"+obj.idx+"'></textarea>";
	  	  	listHtml+="<br/>";
	  		listHtml+="<span id='up"+obj.idx+"'><button class='btn btn-primary btn-sm' onclick='goUpdateForm("+obj.idx+")'>수정하기</button></span>&nbsp;";
	  		listHtml+="<button class='btn btn-danger btn-sm' onclick='goDelete("+obj.idx+")'>삭제하기</button>";
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
  			url: "board/new",
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
  		if($("#c"+idx).css("display")=="none"){
  			$.ajax({
  				url: "board/count/"+idx,
  				type: "put",
  				data: {"idx" : idx},
  				dataType: "json",
  				success: function(data){
  					$("#cnt"+idx).text(data.readCount);
  				},
  				error: function(){ alert("error"); }
  			});
  			$.ajax({
  				url: "board/"+idx,
  				type: "get",
  				data: {"idx": idx},
  				dataType: "json",
  				success: function(data){
  					$("#ta"+idx).val(data.content)
  				},
  				error: function(){ alert("error"); }
  			});
  			$("#c"+idx).css("display", "table-row");	
  			$("#ta"+idx).attr("readonly",true);
  		}else{
  			$("#c"+idx).css("display", "none");
  		}
  	}
  	function goDelete(idx){
  		$.ajax({
  			url: "board/"+idx,
  			type: "delete",
  			data: {"idx":idx},
  			success: loadList,
  			error: function(){alert("error");}
  		});
  	}
  	function goUpdateForm(idx){ //idx 
  		$("#ta"+idx).attr("readonly", false); // attr = attribute
  		var title= $("#t"+idx).text();
  		var newInput="<input id='nt"+idx+"' type='text' class='form-control' value='"+title+"' name=''/>";
  		$("#t"+idx).html(newInput);
  		
  		var newButton = "<button class='btn btn-sm btn-success' onclick='goUpdate("+idx+")'>수정완료</button>";
  		newButton += "&nbsp;<button class='btn btn-sm btn-basic' onclick='loadList()'>목록으로</button>";
  		$("#up"+idx).html(newButton);
  	}
  	function goUpdate(idx){
  		var title = $("#nt"+idx).val();
  		var content = $("#ta"+idx).val();
  		$.ajax({
  			url: "board/update",
  			type: "put",
  			contentType: 'application/json;charset=utf-8',
  			data: JSON.stringify({"idx":idx, "title":title, "content":content}),
  			success: loadList,
  			error: function(){ alert("error"); }
  		});
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
    