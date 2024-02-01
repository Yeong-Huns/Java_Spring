package kr.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.board.entity.Member;

@Mapper
public interface MemberMapper {
	
	public Member registerCheck(String id);
	public int register(Member m); // 회원등록(return 1 || 0)
	public Member memLogin(Member mvo);
	public int memUpdate(Member m);
	 // 로그인 체크
}
