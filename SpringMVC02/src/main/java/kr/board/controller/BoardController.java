package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {
	@Autowired
	private BoardMapper mapper;
	
	@RequestMapping("/")
	public String home() {
		return "redirect:boardList.do";
	}
	@RequestMapping("boardList.do")
	public String boardLIst(Model model) {
		List<Board> list = mapper.getLists();
		model.addAttribute("list", list);
		return "boardList";
	}
	@GetMapping("boardForm.do")
	public String boardForm() {
		return "boardForm";
	}
	@PostMapping("boardInsert.do")
	public String boardList(Board vo) {
		mapper.boardInsert(vo);
		return "redirect:boardList.do";
	}
	@GetMapping("boardContent.do/{idx}")
	public String boardContent(@PathVariable("idx") int idx, Model model) {
		Board vo = mapper.boardContent(idx);
		mapper.boardCount(idx);
		model.addAttribute("vo", vo);
		return "boardContent";
	}
	@GetMapping("boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
		return "redirect:../boardList.do";
	}
	@GetMapping("boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo);
		return "boardUpdateForm";
	}
	@PostMapping("boardUpdate.do")
	public String boardUpdate(Board vo) {
		mapper.boardUpdate(vo);
		return "redirect:boardList.do";
	}
}
