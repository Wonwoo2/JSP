package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

public interface IAttatchDAO {
	public int insertAttatchs(BoardVO board, SqlSession session);
	
	public AttatchVO selectAttatch(int att_no);
}
