package kr.or.ddit.buyer.service;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.vo.BuyerVO;

public class BuyerServiceImpl implements IBuyerService {
	
	private static IBuyerService service;
	private IBuyerDAO dao = BuyerDAOImpl.getInstance();
	
	private BuyerServiceImpl() {}
	
	public static IBuyerService getInstance() {
		if (service == null) {
			service = new BuyerServiceImpl();
		}
		return service;
	}
	@Override
	public BuyerVO retrieveBuyer(String buyer_id) {
		BuyerVO buyer = null;
		buyer = dao.selectBuyer(buyer_id);
		if (buyer == null) {
			throw new CustomException(buyer_id + "는 존재하지 않는 거래처입니다.");
		}
		return buyer;
	}
}