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
			// redirect�� ��ü ���ε��� �ϴ� ���? RedirectAttributes
			rdAb.addFlashAttribute("msgType", "���� �޼���");
			rdAb.addFlashAttribute("msg", "��� ������ �Է��ϼ���.");
			return "redirect:/memJoin.do"; //${msgType}, ${msg} ... memJoin.jsp�� �̵��Ŀ��� EL �±׷� ���� ������ �ִ�.
		}
		if(!memPwd1.equals(memPwd2)) {
			rdAb.addFlashAttribute("msgType", "���� �޼���");
			rdAb.addFlashAttribute("msg", "��й�ȣ�� ���� �ٸ��ϴ�.");
			return "redirect:/memJoin.do"; // ${msgType}, ${msg}
		}
		m.setMemProfile(""); // ������ ���� ����(default)���� ""�� ���� / �������� ������ null ���� ��
		//ȸ���� ���̺� ����
		int result = mapper.register(m);
		if(result==1) { //ȸ������ ���� �޼���
			rdAb.addFlashAttribute("msgType", "ȸ������ ����!");
			rdAb.addFlashAttribute("msg", "���ԵǼ̽��ϴ�.");
			//ȸ�������� �����ϸ� �α��� ó��
			session.setAttribute("mvo", m); // ${!empty m} == �α��� 
			return "redirect:/";
		}else {
			rdAb.addFlashAttribute("msgType", "���� �޼���");
			rdAb.addFlashAttribute("msg", "�̹� ���Ե� ȸ���Դϴ�.");
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
			rdAb.addFlashAttribute("msgType", "���� �޼���");
			rdAb.addFlashAttribute("msg", "��� ������ �Է����ּ���.");
			return "redirect:/memLoginForm.do";
		}
		Member mvo = mapper.memLogin(m);
			if(mvo!= null) { // �α��ο� ����
			rdAb.addFlashAttribute("msgType", "�α��� ����");
			rdAb.addFlashAttribute("msg", "�α��ο� �����ϼ̽��ϴ�.");
			session.setAttribute("mvo", mvo); //${!emepy mvo}
			return "redirect:/" ; // ����
			}
			else{
			rdAb.addFlashAttribute("msgType", "���� �޼���");
			rdAb.addFlashAttribute("msg", "�ٽ� �α��� ���ּ���.");
			return "redirect:/memLoginForm.do";
			}
		}
	@RequestMapping("/memUpdateForm.do")
	public String memUpdateForm() {
		return "member/memUpdateForm";
	}
	}

