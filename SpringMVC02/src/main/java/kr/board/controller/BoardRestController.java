package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;
@RequestMapping("/board")
@RestController //자동으로 ResPonseBody
public class BoardRestController {
	@Autowired
	private BoardMapper mapper;
	
	@GetMapping("/all")
	public List<Board> boardList(){
		List<Board> list = mapper.getLists();
		return list;
	}
	@PostMapping("/new")
	public void boardInsert(Board vo) {
		mapper.boardInsert(vo);
	}
	@DeleteMapping("/{idx}")
	public void boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
	}
	@PutMapping("/update")
	public void boardUpdate(@RequestBody Board vo) {
		mapper.boardUpdate(vo);
	}
	@GetMapping("/{idx}")
	public Board boardContent(@PathVariable("idx") int idx) {
		Board vo = mapper.boardContent(idx);
		return vo;
	}  
	@PutMapping("/count/{idx}")
	public Board boardCount(@PathVariable("idx") int idx) {
		mapper.boardCount(idx);
		Board vo = mapper.boardContent(idx);
		return vo;
	}
}
