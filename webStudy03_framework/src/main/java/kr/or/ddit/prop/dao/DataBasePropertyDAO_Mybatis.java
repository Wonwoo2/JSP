package kr.or.ddit.prop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAO_Mybatis implements IDataBasePropertyDAO {

	private SqlSessionFactory sqlSession = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	private static IDataBasePropertyDAO dao;
	
	private DataBasePropertyDAO_Mybatis() { }
	
	public static IDataBasePropertyDAO getInstance() {
		if (dao == null) {
			dao = new DataBasePropertyDAO_Mybatis();
		}
		return dao;
	}
	
	@Override
	public List<DataBasePropertyVO> selectDataBaseProperties(DataBasePropertyVO param) {
		try (
				SqlSession session = sqlSession.openSession();
		) {
			IDataBasePropertyDAO mapper = session.getMapper(IDataBasePropertyDAO.class);
			return mapper.selectDataBaseProperties(param);
		}
	}

	@Override
	public List<String> selectAllProperty_names() {
		try (
				SqlSession session = sqlSession.openSession();
		) {
			IDataBasePropertyDAO mapper = session.getMapper(IDataBasePropertyDAO.class);
			return mapper.selectAllProperty_names();
		}
	}	
}