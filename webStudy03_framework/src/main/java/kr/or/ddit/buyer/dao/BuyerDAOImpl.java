package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImpl implements IBuyerDAO {
	private SqlSessionFactory sqlSession = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	private static IBuyerDAO dao;
	
	private BuyerDAOImpl() { }
	
	public static IBuyerDAO getInstance() {
		if (dao == null) {
			dao = new BuyerDAOImpl();
		}
		return dao;
	}

	@Override
	public int insertBuyer(BuyerVO buyer, SqlSession session) {
		return session.insert("kr.or.ddit.buyer.dao.IBuyerDAO.insertBuyer", buyer);
	}

	@Override
	public int updateBuyer(BuyerVO buyer, SqlSession session) {
		return session.update("kr.or.ddit.buyer.dao.IBuyerDAO.updateBuyer", buyer);
	}
	
	@Override
	public int deleteBuyer(String buyer_id) {
		try (
				SqlSession session = sqlSession.openSession(true);
		) {
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.deleteBuyer(buyer_id);
		}
	}
	
	@Override
	public BuyerVO selectBuyer(String buyer_id) {
		try (
				SqlSession session = sqlSession.openSession();
		) {
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyer(buyer_id);
		}
	}

	@Override
	public int selectBuyerCount(PagingVO<BuyerVO> pagingVo) {
		try (
				SqlSession session = sqlSession.openSession();
		) {
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerCount(pagingVo);
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVo) {
		try (
				SqlSession session = sqlSession.openSession();
		) {
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerList(pagingVo);
		}
		
	}
}