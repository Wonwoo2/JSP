package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardService {
	
	public BoardVO readBoard(int bo_no); 
	
	public int readBoardCount(PagingVO<BoardVO> pagingVo);
	
	public List<BoardVO> readBoardList(PagingVO<BoardVO> pagingVo);
}