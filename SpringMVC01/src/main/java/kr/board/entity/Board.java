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
	private int idx; //��ȣ
	private String title; //����
 	private String content; //����
	private String writer; //�ۼ���
	private String indate; //�ۼ���
	private int readCount; //��ȸ��
	//���� ���� �ڵ� api : Lombok 
}
