package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDAO dao = BoardDAOImpl.getInstance();
	private static IBoardService service;
	
	private BoardServiceImpl() { }
	
	public static IBoardService getInstance() {
		if (service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}


	@Override
	public BoardVO readBoard(int bo_no) {
		BoardVO boardVo = dao.selectBoard(bo_no);
		if (boardVo == null) {
			throw new CustomException(bo_no + "는 존재하지 않는 게시글입니다.");
		}
		dao.incrementHit(bo_no);
		return boardVo;
	}
	
	@Override
	public int readBoardCount(PagingVO<BoardVO> pagingVo) {
		return dao.selectBoardCount(pagingVo);
	}

	@Override
	public List<BoardVO> readBoardList(PagingVO<BoardVO> pagingVo) {
		return dao.selectBoardList(pagingVo);
	}
}