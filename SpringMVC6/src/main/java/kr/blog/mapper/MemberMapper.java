package kr.blog.mapper;

import kr.blog.entity.AuthVO;
import kr.blog.entity.Member;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
	
	public Member registerCheck(String id);
	public int register(Member m); // 회원등록(return 1 || 0)
	public Member memLogin(String username);
	public int memUpdate(Member m);
	public Member getMember(String memId);
	public void memProfileUpdate(Member mvo);
	public void authDelete(String memId);
	void authInsert(AuthVO saveVo);
	// 로그인 체크
}
