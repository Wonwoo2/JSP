package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardService {
	
	public ServiceResult createBoard(BoardVO board);
	
	public BoardVO readBoard(int bo_no); 
	
	public int readBoardCount(PagingVO<BoardVO> pagingVo);
	
	public List<BoardVO> readBoardList(PagingVO<BoardVO> pagingVo);
	
	public ServiceResult modifyBoard(BoardVO board);
	
	public ServiceResult removeBoard(int bo_no);
	
	public AttatchVO downloadAttatch(int att_no);
}