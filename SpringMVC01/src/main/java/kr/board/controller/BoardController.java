package kr.board.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller //식별을 위한 어노테이션 ex)mapper...
public class BoardController {
	
	@Autowired
	private BoardMapper mapper;
	
	//boardList.do 요청 
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		
		List<Board> list= mapper.getLists();
	
		model.addAttribute("list", list);
		return "boardList";//WEB-INF/views/ + 
	}
	
	
}
