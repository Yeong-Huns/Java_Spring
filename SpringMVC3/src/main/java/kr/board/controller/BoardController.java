package kr.board.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller //�ĺ��� ���� ������̼� ex)mapper...
public class BoardController {
	
	@RequestMapping("/boardMain.do")
	public String home() {
		return "board/main";
	};
}
