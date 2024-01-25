package kr.board.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/boardForm.do")
	public String boardFrom() {
		return "boardForm"; //WEB-INF/vies/ + boardForm.jsp;
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) { 
		mapper.boardInsert(vo); //등록
		return "redirect:/boardList.do"; // redirect
	}
	@GetMapping("/boardContent.do")
	public String boardContent(@RequestParam("idx") int idx, Model model) { // ex)idx=6
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo);
		return "boardContent";
	}
	
	@GetMapping("/boadDelete.do/{idx}")
	public String boadDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	@GetMapping("boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo);
		return "boardUpdate"; 
	}
	
	@PostMapping("boardUpdate.do")
	public String boardUpdate(Board vo) {
		mapper.boardUpdate(vo); // 수정
		return "redirect:/boardList.do";
	}
}
