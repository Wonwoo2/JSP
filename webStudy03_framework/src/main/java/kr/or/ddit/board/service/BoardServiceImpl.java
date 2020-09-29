package kr.or.ddit.board.service;

import java.io.File;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService {
	
	private static IBoardService service;
	private IBoardDAO dao = BoardDAOImpl.getInstance();
	private SqlSessionFactory sqlSession = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private File saveFolder = new File("e:/saveFiles");
	
	private BoardServiceImpl() { }
	
	public static IBoardService getInstance() {
		if (service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}

	@Override
	public ServiceResult createBoard(BoardVO board) {
		try (
				SqlSession session = sqlSession.openSession();
		) {
			
		}
		// 1. Board Insert(수행 후에 bo_no가 정해짐)
		
		// 첨부 파일 존재 여부 확인
		
		// 2. Attatch Insert n번
		
		// 3. binary 저장
		
		return null;
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

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeBoard(int bo_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttatchVO downloadAttatch(int att_no) {
		// TODO Auto-generated method stub
		return null;
	}
}