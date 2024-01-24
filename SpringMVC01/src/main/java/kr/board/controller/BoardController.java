package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;

@Controller //�ĺ��� ���� ������̼� ex)mapper...
public class BoardController {
	
	//boardList.do ��û 
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		Board vo = new Board();
		vo.setIdx(1);
		vo.setTitle("�Խ��ǽǽ�");
		vo.setContent("�Խ��ǽǽ�");
		vo.setWriter("hesil");
		vo.setIndate("2024-01-24");
		vo.setCount(0);
		List<Board> list = new ArrayList<Board>();
		list.add(vo);
		list.add(vo);
		list.add(vo);
		model.addAttribute("list", list);
		return "boardList";//WEB-INF/views/ + 
	}
	
	
}
