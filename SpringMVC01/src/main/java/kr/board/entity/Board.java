package kr.board.entity;
//@data - lombok API
public class Board {
	private int idx; //번호
	private String title; //제목
 	private String content; //내용
	private String writer; //작성자
	private String indate; //작성일
	private int count; //조회수
	//게터 세터 자동 api : Lombok 
}
