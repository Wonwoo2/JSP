package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;

public class OtherDAOImpl implements IOtherDAO {

	private static IOtherDAO dao;
	private SqlSessionFactory sqlSession = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private OtherDAOImpl() {}
	
	public static IOtherDAO getInstance() {
		if (dao == null) {
			dao = new OtherDAOImpl();
		}
		return dao;
	}

	@Override
	public List<Map<String, Object>> selectLprodGuList() {
		try (
			SqlSession session = sqlSession.openSession();	
		) {
			IOtherDAO mapper = session.getMapper(IOtherDAO.class);
			return mapper.selectLprodGuList();
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(String buyer_lgu) {
		try (
				SqlSession session = sqlSession.openSession();	
			) {
				IOtherDAO mapper = session.getMapper(IOtherDAO.class);
				return mapper.selectBuyerList(buyer_lgu);
			}
	}
}