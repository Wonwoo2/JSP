package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerDAO {
	public int insertBuyer(BuyerVO buyer, SqlSession session);
	
	public int updateBuyer(BuyerVO buyer, SqlSession session);
	
	public int deleteBuyer(String buyer_id);
	
	public BuyerVO selectBuyer(String buyer_id);
	
	public int selectBuyerCount(PagingVO<BuyerVO> pagingVo);
	
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVo);
}