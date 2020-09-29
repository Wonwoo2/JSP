package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

public class AttatchDAOImpl implements IAttatchDAO {
	
	private static IAttatchDAO dao;
	
	public static IAttatchDAO getInstance() {
		if (dao == null) {
			dao = new AttatchDAOImpl();
		}
		return dao;
	}

	@Override
	public int insertAttatchs(BoardVO board, SqlSession session) {
		
		return 0;
	}

	@Override
	public AttatchVO selectAttatch(int att_no) {
		return null;
	}
}
