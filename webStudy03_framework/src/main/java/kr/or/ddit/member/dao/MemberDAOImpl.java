package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {
	
	private static MemberDAOImpl dao;
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private MemberDAOImpl() {
		super();
	}
	
	public static MemberDAOImpl getInstance() {
		if (dao == null) {
			dao = new MemberDAOImpl();
		}
		return dao;
	}

	@Override
	public int insertMember(MemberVO member) {
		try (
				SqlSession session = sqlSessionFactory.openSession(true);
		) {
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			return mapper.insertMember(member);
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			return mapper.selectMemberList();
		} 
	}

	@Override
	public MemberVO selectMember(String mem_id) {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			return mapper.selectMember(mem_id);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			int result = mapper.updateMember(member);
			session.commit();
			return result;
		}
	}

	@Override
	public int deleteMember(String mem_id) {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			int result = mapper.deleteMember(mem_id);
			session.commit();
			return result;
		}
	}
}