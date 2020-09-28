package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyDAOImpl implements IReplyDAO {
	
	private static IReplyDAO dao;
	private SqlSessionFactory sqlSession = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private ReplyDAOImpl() { }
	
	public static IReplyDAO getInstance() {
		if (dao == null) {
			dao = new ReplyDAOImpl();
		}
		return dao;
	}

	@Override
	public int selectReplyCount(PagingVO<ReplyVO> pagingVo) {
		try (
				SqlSession session = sqlSession.openSession();
		) {
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectReplyCount(pagingVo);
		}
	}

	@Override
	public List<ReplyVO> selectReplyList(PagingVO<ReplyVO> pagingVo) {
		try (
				SqlSession session = sqlSession.openSession();
		) {
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectReplyList(pagingVo);
		}
	}
}