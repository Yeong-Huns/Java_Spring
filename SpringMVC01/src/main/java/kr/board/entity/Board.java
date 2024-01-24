package kr.board.entity;
//@data - lombok API
public class Board {
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public int getCount() {
		return readCount;
	}
	public void setCount(int count) {
		this.readCount = count;
	}
	private int idx; //번호
	private String title; //제목
 	private String content; //내용
	private String writer; //작성자
	private String indate; //작성일
	private int readCount; //조회수
	//게터 세터 자동 api : Lombok 
}
