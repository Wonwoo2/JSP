package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {

	private static IProdDAO dao;
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private ProdDAOImpl() {	}
	
	public static IProdDAO getInstance() {
		if (dao == null) {
			dao = new ProdDAOImpl();
		}
		return dao;
	}
	

	@Override
	public int insertProd(ProdVO prod) {
		try (
			SqlSession session = sqlSessionFactory.openSession(true);	
		) {
			IProdDAO mapper = session.getMapper(IProdDAO.class);
			return mapper.insertProd(prod);
		}
	}
	
	@Override
	public ProdVO selectProd(String prod_id) {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			IProdDAO mapper = session.getMapper(IProdDAO.class);
			return mapper.selectProd(prod_id);			
		}
	}

	@Override
	public int selectProdCount(PagingVO<ProdVO> pagingVo) {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			IProdDAO mapper = session.getMapper(IProdDAO.class);
			return mapper.selectProdCount(pagingVo);
		}
	}

	@Override
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVo) {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			IProdDAO mapper = session.getMapper(IProdDAO.class);
			return mapper.selectProdList(pagingVo);
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try (
				SqlSession session = sqlSessionFactory.openSession(true);
		) {
			IProdDAO mapper = session.getMapper(IProdDAO.class);
			return mapper.updateProd(prod);
		}
	}
}