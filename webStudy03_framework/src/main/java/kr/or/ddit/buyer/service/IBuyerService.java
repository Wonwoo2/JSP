package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerService {
	public ServiceResult createBuyer(BuyerVO buyer);
	
	public ServiceResult modifyBuyer(BuyerVO buyer);
	
	public ServiceResult deleteBuyer(String buyer_id);
	
	public BuyerVO retrieveBuyer(String buyer_id);
	
	public int retrieveBuyerCount(PagingVO<BuyerVO> pagingVo);
	
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVo);
}