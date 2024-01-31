package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
@RestController
public class BoardRestController {

		@Autowired
		private BoardMapper mapper;
	
	//boardList.do 요청 
		@GetMapping("/all")
		public List<Board> boardlist(){
			List<Board> list = mapper.getLists();
			return list;
		}
		@PostMapping("/new")
		public void boardInsert(Board vo) { 
			mapper.boardInsert(vo); //등록
		}
		@DeleteMapping("/{idx}")
		public void boadDelete(@PathVariable("idx") int idx) {
			mapper.boardDelete(idx);
		}
		@GetMapping("/{idx}")
		public Board getContent(@PathVariable("idx") int idx) {
			Board vo = mapper.boardContent(idx);
			return vo;
		}
		@PutMapping("/{idx}")
		public Board updateReadCount(@PathVariable("idx") int idx) {
			mapper.boardCount(idx);
			Board vo = mapper.boardContent(idx);
			return vo;
		}
		@PutMapping("/update")
		public void updateBoard(@RequestBody Board vo) {
			mapper.boardUpdate(vo);
		}
		
}
