package kr.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
	@RequestMapping("/memUpdate.do")
	public String memUpdate(Member m, RedirectAttributes rdAb, 
			String memPwd1, String memPwd2, HttpSession session) {
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
					return "redirect:/memUpdateForm.do"; //${msgType}, ${msg} ... memJoin.jsp�� �̵��Ŀ��� EL �±׷� ���� ������ �ִ�.
				}
				if(!memPwd1.equals(memPwd2)) {
					rdAb.addFlashAttribute("msgType", "���� �޼���");
					rdAb.addFlashAttribute("msg", "��й�ȣ�� ���� �ٸ��ϴ�.");
					return "redirect:/memUpdateForm.do"; // ${msgType}, ${msg}
				}
				int result = mapper.memUpdate(m); 
				if(result==1) { //ȸ������ ���� �޼���
					rdAb.addFlashAttribute("msgType", "�����Ϸ�");
					rdAb.addFlashAttribute("msg", "ȸ������������ �Ϸ�Ǿ�����");
					//ȸ�������� �����ϸ� �α��� ó��
					session.setAttribute("mvo", m); // ${!empty m} == �α��� 
					return "redirect:/";
				}else {
					rdAb.addFlashAttribute("msgType", "����!");
					rdAb.addFlashAttribute("msg", "ȸ�� ���� ������ �����Ͽ����ϴ�.");
					return "redirect:/memUpdateForm.do";
				}
	}
	
	@RequestMapping("memImageForm.do")
	public String memImageForm() {
		return "member/memImageForm";
	}
	@RequestMapping("/memImageUpdate.do")
	public String memImageUpdate(HttpServletRequest request, RedirectAttributes rdAb, HttpSession session) throws IOException {
		//���� ���ε� API �ʿ� (3���� cos.jar)
		MultipartRequest multi = null;
		int fileMaxSize =40*1024*1024;
		String savePath = request.getRealPath("resources/upload");
		try {
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		}catch(Exception e){
			e.printStackTrace();
			rdAb.addFlashAttribute("msgType", "����!");
			rdAb.addFlashAttribute("msg", "������ ũ��� 10MB�� ���� �� �����ϴ�.");
			return "redirect:/memImageForm.do";
		} //����
		//�����ͺ��̽� ���̺� ȸ���̹����� ������Ʈ
		String memId = multi.getParameter("memId");
		String newProfile="";
		File file = multi.getFile("memProfile"); //memImageForm ���� #memProfile�� �Ѿ�� ������ ���� ���ε��� �� �����̴�.
		if(file == null) System.out.println("nothing");
		if(file != null) { // ���ε尡 �� ���� (.png, .jpg, .gif) 
			//�̹��� ���� ���θ� üũ -> �̹��� ������ �ƴϸ� ����(1.png)
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1); 
			ext = ext.toUpperCase(); // PNG, GIF, JPG
			if(ext.equals("PNG") || ext.equals("GIF") || ext.equals("JPG")) {
				//�̹����� ��� 
				// ���� ���ε� �� �̹���(new->1.PNG) , ���� DB�� �ִ� �̹���(DB=>4.PNG)
				String oldProfile = mapper.getMember(memId).getMemProfile();
				File oldfile = new File(savePath+"/"+oldProfile);
				if(oldfile.exists()) {
					oldfile.delete();
				}
				newProfile=file.getName();
			}else { //�̹��� ������ �ƴϸ�
				if(file.exists()) {
					file.delete(); //���� 
				}
				rdAb.addFlashAttribute("msgType", "����!");
				rdAb.addFlashAttribute("msg", "�̹��� ���ϸ� ���ε� �����մϴ�.");
				return "redirect:/memImageForm.do";
			}
		}
		//���ο� ProFile ����
		Member mvo= new Member();
		mvo.setMemId(memId);
		mvo.setMemProfile(newProfile);
		mapper.memProfileUpdate(mvo); // �̹��� ������Ʈ ����
		Member m=mapper.getMember(memId);
		// ������ ���Ӱ� �����Ѵ�.
		session.setAttribute("mvo", m);
		rdAb.addFlashAttribute("msgType", "���� �޼���");
		rdAb.addFlashAttribute("msg", "�̹��� ������ �����߽��ϴ�.");	
		return "redirect:/";
	}
	}

