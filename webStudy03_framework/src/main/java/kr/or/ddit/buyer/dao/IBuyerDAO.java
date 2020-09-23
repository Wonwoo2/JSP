package kr.or.ddit.buyer.dao;

import kr.or.ddit.vo.BuyerVO;

public interface IBuyerDAO {
	public BuyerVO selectBuyer(String buyer_id);
}