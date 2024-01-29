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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {
	@Autowired
	private BoardMapper mapper;
	
	@RequestMapping("/")
	public String home() {
		return "main";
	}
	@RequestMapping("boardList.do")
	public @ResponseBody List<Board> boardList(){
		List<Board> list = mapper.getLists();
		return list;
	}
	@RequestMapping("boardInsert.do")
	public @ResponseBody void boardInsert(Board vo) {
		mapper.boardInsert(vo);
	}
	@RequestMapping("boardDelete.do")
	public @ResponseBody void boardDelete(@RequestParam("idx") int idx) {
		mapper.boardDelete(idx);
	}
	@RequestMapping("boardUpdate.do")
	public @ResponseBody void boardUpdate(Board vo) {
		mapper.boardUpdate(vo);
	}
}
