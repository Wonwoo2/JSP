package kr.or.ddit.zip.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipVO;

public class ZipDAOImpl implements IZipDAO {
	
	private static IZipDAO dao;
	
	private ZipDAOImpl() { }
	
	public static IZipDAO getInstance() {
		if (dao == null) {
			dao = new ZipDAOImpl();
		}
		return dao;
	}
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int selectTotalCount(PagingVO<ZipVO> pagingVo) {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			IZipDAO mapper = session.getMapper(IZipDAO.class);
			return mapper.selectTotalCount(pagingVo);
		}
	}
	
	@Override
	public List<ZipVO> selectZipList(PagingVO<ZipVO> pagingVo) {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			IZipDAO mapper = session.getMapper(IZipDAO.class);
			return mapper.selectZipList(pagingVo);
		}
	}

	
}