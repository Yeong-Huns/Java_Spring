package kr.board.mapper;

import java.util.List;

import kr.board.entity.Board;

@mapper //- mybatis API
public interface BoardMapper {
	private List<Board> getLists(); //��ü����Ʈ�� �������� �޼���
}
