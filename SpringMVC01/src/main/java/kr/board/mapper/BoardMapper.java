package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;

@Mapper //- mybatis API
public interface BoardMapper {
	public List<Board> getLists(); //��ü����Ʈ�� �������� �޼���
	public void boardInsert(Board vo);
	public Board boardContent(int idx);
	public void boardDelete(int idx);
	public void boardUpdate(Board vo);
	
	@Update("UPDATE BOARD SET readCount=readCount+1 WHERE idx=#{idx}")
	public void boardCount(int idx);
}
