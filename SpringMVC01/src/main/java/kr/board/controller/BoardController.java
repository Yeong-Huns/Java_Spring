package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;

@Controller //식별을 위한 어노테이션 ex)mapper...
public class BoardController {
	
	//boardList.do 요청 
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		Board vo = new Board();
		vo.setIdx(1);
		vo.setTitle("게시판실습");
		vo.setContent("게시판실습");
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
