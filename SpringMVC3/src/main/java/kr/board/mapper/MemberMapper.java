package kr.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.board.entity.Member;

@Mapper
public interface MemberMapper {
	
	public Member registerCheck(String id);
	public int register(Member m); // ȸ�����(return 1 || 0)
	public Member memLogin(Member mvo);
	public int memUpdate(Member m);
	 // �α��� üũ
}
