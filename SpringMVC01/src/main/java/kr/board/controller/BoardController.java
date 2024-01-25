package kr.board.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller //�ĺ��� ���� ������̼� ex)mapper...
public class BoardController {
	
	@Autowired
	private BoardMapper mapper;
	
	//boardList.do ��û 
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		
		List<Board> list= mapper.getLists();
	
		model.addAttribute("list", list);
		return "boardList";//WEB-INF/views/ + 
	}
	
	
}
