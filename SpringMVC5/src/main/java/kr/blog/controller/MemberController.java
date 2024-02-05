package kr.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.blog.entity.AuthVO;
import kr.blog.entity.Member;
import kr.blog.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper mapper;
	@Autowired
	PasswordEncoder PwdEnc;
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
		   m.getMemAge()==0 || m.getAuthList().size()==0 ||
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
		// 추가 : 비밀번호를 암호화 하기(API)
		String EncPwd = PwdEnc.encode(m.getMemPwd());
		m.setMemPwd(EncPwd);
		//register
		int result = mapper.register(m); 
		if(result==1) { //회원가입 성공 메세지
			//권한 테이블에 회원의 권한을 저장하기
			List<AuthVO>list = m.getAuthList();
			for(AuthVO authVO : list){
				if(authVO.getAuth()!=null){
					AuthVO saveVo= new AuthVO(); // 이름&권한 설정을 위한 새로운 Auth 생성
					saveVo.setMemId(m.getMemId()); //
					saveVo.setAuth(authVO.getAuth());
					mapper.authInsert(saveVo);
				}
			}
			rdAb.addFlashAttribute("msgType", "회원가입 성공!");
			rdAb.addFlashAttribute("msg", "가입되셨습니다.");
			//회원가입이 성공하면 로그인 처리
			Member mvo = mapper.getMember(m.getMemId());
			System.out.println(mvo);
			session.setAttribute("mvo", mvo); /// ${!empty m} == 로그인
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
					// redirect시 객체 바인딩을 하는 방법? RedirectAttributes
					rdAb.addFlashAttribute("msgType", "누락 메세지");
					rdAb.addFlashAttribute("msg", "모든 내용을 입력해주세요.");
					return "redirect:/memUpdateForm.do"; //${msgType}, ${msg} ... memJoin.jsp�� �̵��Ŀ��� EL �±׷� ���� ������ �ִ�.
				}
				if(!memPwd1.equals(memPwd2)) {
					rdAb.addFlashAttribute("msgType", "실패 메세지");
					rdAb.addFlashAttribute("msg", "비밀번호가 서로 다릅니다.");
					return "redirect:/memUpdateForm.do"; // ${msgType}, ${msg}
				}
				int result = mapper.memUpdate(m); 
				if(result==1) { //회원가입 성공 메세지
					rdAb.addFlashAttribute("msgType", "수정완료");
					rdAb.addFlashAttribute("msg", "회원정보수정이 완료되었습니다.");
					//회원가입이 성공하면 로그인 처리
					Member mvo = mapper.getMember(m.getMemId());
					session.setAttribute("mvo", mvo); // ${!empty m} == �α���
					return "redirect:/";
				}else {
					rdAb.addFlashAttribute("msgType", "오류!");
					rdAb.addFlashAttribute("msg", "회원 정보 수정에 실패하였습니다.");
					return "redirect:/memUpdateForm.do";
				}
	}
	
	@RequestMapping("memImageForm.do")
	public String memImageForm() {
		return "member/memImageForm";
	}
	@RequestMapping("/memImageUpdate.do")
	public String memImageUpdate(HttpServletRequest request, RedirectAttributes rdAb, HttpSession session) throws IOException {
		//파일 업로드 API 필요 (3가지 cos.jar)
		MultipartRequest multi = null;
		int fileMaxSize =40*1024*1024;
		String savePath = request.getRealPath("resources/upload");
		try {
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		}catch(Exception e){
			e.printStackTrace();
			rdAb.addFlashAttribute("msgType", "오류!");
			rdAb.addFlashAttribute("msg", "파일의 크기는 10MB를 넘을 수 없습니다..");
			return "redirect:/memImageForm.do";
		} //성공
		//데이터베이스 테이블에 회원이미지를 업데이트
		String memId = multi.getParameter("memId");
		String newProfile="";
		File file = multi.getFile("memProfile"); //memImageForm 에서 #memProfile로 넘어온 파일은 내가 업로드한 그 파일이다.
		if(file == null) System.out.println("nothing");
		if(file != null) { // 업로드가 된 상태 (.png, .jpg, .gif)
			//이미지 파일 여부를 체크 -> 이미지 파일이 아니면 삭제(1.png)
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1); 
			ext = ext.toUpperCase(); // PNG, GIF, JPG
			if(ext.equals("PNG") || ext.equals("GIF") || ext.equals("JPG")) {
				//이미지일 경우
				// 새로 업로드 된 이미지(new->1.PNG) , 현재 DB에 있는 이미지(DB=>4.PNG)
				String oldProfile = mapper.getMember(memId).getMemProfile();
				File oldfile = new File(savePath+"/"+oldProfile);
				if(oldfile.exists()) {
					oldfile.delete();
				}
				newProfile=file.getName();
			}else { //이미지 파일이 아니면
				if(file.exists()) {
					file.delete();  //삭제
				}
				rdAb.addFlashAttribute("msgType", "오류!");
				rdAb.addFlashAttribute("msg", "이미지 파일만 업로드 가능합니다.");
				return "redirect:/memImageForm.do";
			}
		}
		//새로운 ProFile 저장
		Member mvo= new Member();
		mvo.setMemId(memId);
		mvo.setMemProfile(newProfile);
		mapper.memProfileUpdate(mvo); // 이미지 업데이트 성공
		Member m=mapper.getMember(memId);
		// 세션을 새롭게 생성한다.
		session.setAttribute("mvo", m);
		rdAb.addFlashAttribute("msgType", "성공 메세지");
		rdAb.addFlashAttribute("msg", "이미지 변경 성공!");
		return "redirect:/";
	}
	}

