package kr.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller //식별을 위한 어노테이션 ex)mapper...
public class BoardController {
	
	@RequestMapping("/boardMain.do")
	public String home() {
		return "board/main";
	};
}
