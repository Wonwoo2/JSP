package kr.or.ddit.buyer.service;

import kr.or.ddit.vo.BuyerVO;

public interface IBuyerService {
	public BuyerVO retrieveBuyer(String buyer_id);
}