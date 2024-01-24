package kr.board.mapper;

import java.util.List;

import kr.board.entity.Board;

@mapper //- mybatis API
public interface BoardMapper {
	private List<Board> getLists(); //전체리스트를 가져오는 메서드
}
