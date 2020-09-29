package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardDAOImpl implements IBoardDAO {

	private static IBoardDAO dao;
	private SqlSessionFactory sqlSession =  CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private BoardDAOImpl() { }
	public static IBoardDAO getInstance() {
		if (dao == null) {
			dao = new BoardDAOImpl();
		}
		return dao;
	}
	
	@Override
	public int insertBoard(BoardVO board, SqlSession session) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public BoardVO selectBoard(int bo_no) {
		try (
				SqlSession session = sqlSession.openSession(true);
		) {
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.selectBoard(bo_no);
		}
	}
	
	
	@Override
	public int selectBoardCount(PagingVO<BoardVO> pagingVo) {
		try (
			SqlSession session = sqlSession.openSession();	
		) {
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.selectBoardCount(pagingVo);
		}
	}

	@Override
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVo) {
		try (
			SqlSession session = sqlSession.openSession();	
		) {
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.selectBoardList(pagingVo);
		}
	}
	
	@Override
	public int incrementHit(int bo_no) {
		try (
				SqlSession session = sqlSession.openSession(true);
		) {
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.incrementHit(bo_no);
		}
	}
	
	@Override
	public int updateBoard(BoardVO boardVo, SqlSession session) {
		return session.update("kr.or.ddit.board.dao.BoardDaoImpl.updateBoard", boardVo);
	}
	@Override
	public int deleteBoard(int bo_no) {
		return 0;
	}
}