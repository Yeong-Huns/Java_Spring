package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		@GetMapping("/boardForm.do")
		public String boardFrom() {
			return "boardForm"; //WEB-INF/vies/ + boardForm.jsp;
		}
		@GetMapping("/boardContent.do")
		public String boardContent(@RequestParam("idx") int idx, Model model) { // ex)idx=6
			Board vo = mapper.boardContent(idx);
			//조회수 증가 처리
			mapper.boardCount(idx);
			model.addAttribute("vo", vo);
			return "boardContent";
		}
		
		
		@GetMapping("boardUpdateForm.do")
		public String boardUpdateForm(@RequestParam("idx") int idx, Model model) {
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
