package kr.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.board.entity.Member;
import kr.board.mapper.MemberMapper;

@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper mapper;
	
	@RequestMapping("/memJoin.do")
	public String memJoin() {
		return "member/join";
	}
	@RequestMapping("/memRegisterCheck.do")
	public @ResponseBody int memRegisterCheck(@RequestParam("memId") String memId) {
		Member mem = mapper.registerCheck(memId);
		if(mem!=null||memId.equals("")) {
			return 0;
		}
		return 1;
	};
	@RequestMapping("/memRegister.do")
	public String memRegister(Member m, String memPwd1, String memPwd2, RedirectAttributes rdAb, HttpSession session) {
		if(m.getMemId()==null || m.getMemId().equals("") ||
		   memPwd1==null || memPwd1.equals("") ||
		   memPwd2==null || memPwd2.equals("") ||
		   m.getMemName()==null || m.getMemName().equals("") ||
		   m.getMemAge()==0 ||
		   m.getMemGender()==null || m.getMemGender().equals("") ||
		   m.getMemEmail()==null || m.getMemEmail().equals("")) {
			// redirect시 객체 바인딩을 하는 방법? RedirectAttributes
			rdAb.addFlashAttribute("msgType", "누락 메세지");
			rdAb.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			return "redirect:/memJoin.do"; //${msgType}, ${msg} ... memJoin.jsp로 이동후에도 EL 태그로 값을 받을수 있다.
		}
		if(!memPwd1.equals(memPwd2)) {
			rdAb.addFlashAttribute("msgType", "실패 메세지");
			rdAb.addFlashAttribute("msg", "비밀번호가 서로 다릅니다.");
			return "redirect:/memJoin.do"; // ${msgType}, ${msg}
		}
		m.setMemProfile(""); // 사진이 없는 상태(default)값을 ""로 지정 / 설정하지 않으면 null 값이 들어감
		//회원을 테이블에 저장
		int result = mapper.register(m);
		if(result==1) { //회원가입 성공 메세지
			rdAb.addFlashAttribute("msgType", "회원가입 성공!");
			rdAb.addFlashAttribute("msg", "가입되셨습니다.");
			//회원가입이 성공하면 로그인 처리
			session.setAttribute("mvo", m); // ${!empty m} == 로그인 
			return "redirect:/";
		}else {
			rdAb.addFlashAttribute("msgType", "실패 메세지");
			rdAb.addFlashAttribute("msg", "이미 가입된 회원입니다.");
			return "redirect:/memJoin.do";
		}
	}
	@RequestMapping("/memLogout.do")
	public String memLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@RequestMapping("/memLoginForm.do")
	public String memLoginForm() {
		return "member/memLoginForm";
	}
	
	@RequestMapping("/memLogin.do")
	public String memLogin(Member m, HttpSession session, RedirectAttributes rdAb) {
		if(m.getMemId()==null || m.getMemId().equals("") ||
		   m.getMemPwd()==null || m.getMemPwd().equals("")) {
			rdAb.addFlashAttribute("msgType", "실패 메세지");
			rdAb.addFlashAttribute("msg", "모든 내용을 입력해주세요.");
			return "redirect:/memLoginForm.do";
		}
		Member mvo = mapper.memLogin(m);
			if(mvo!= null) { // 로그인에 성공
			rdAb.addFlashAttribute("msgType", "로그인 성공");
			rdAb.addFlashAttribute("msg", "로그인에 성공하셨습니다.");
			session.setAttribute("mvo", mvo); //${!emepy mvo}
			return "redirect:/" ; // 메인
			}
			else{
			rdAb.addFlashAttribute("msgType", "실패 메세지");
			rdAb.addFlashAttribute("msg", "다시 로그인 해주세요.");
			return "redirect:/memLoginForm.do";
			}
		}
	@RequestMapping("/memUpdateForm.do")
	public String memUpdateForm() {
		return "member/memUpdateForm";
	}
	}

