package kr.blog.entity;

import lombok.Data;

@Data
	public class Board {
	private int idx; //��ȣ
	private String title; //����
 	private String content; //����
	private String writer; //�ۼ���
	private String indate; //�ۼ���
	private int readCount; //��ȸ��
}
