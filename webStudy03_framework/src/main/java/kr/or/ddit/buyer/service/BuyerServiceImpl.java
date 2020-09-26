package kr.or.ddit.buyer.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImpl implements IBuyerService {
	
	private static IBuyerService service;
	private IBuyerDAO dao = BuyerDAOImpl.getInstance();
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private BuyerServiceImpl() {}
	
	public static IBuyerService getInstance() {
		if (service == null) {
			service = new BuyerServiceImpl();
		}
		return service;
	}

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			ServiceResult result = ServiceResult.FAILED;
			int insertResult = dao.insertBuyer(buyer, session);
			
			if (insertResult > 0) {
				result = ServiceResult.OK;
				session.commit();
			}
			return result;
		}
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			ServiceResult result = ServiceResult.FAILED;
			int updateResult = dao.updateBuyer(buyer, session);
			
			if (updateResult > 0) {
				result = ServiceResult.OK;
				session.commit();
			}
			return result;
		}
	}
	

	@Override
	public ServiceResult deleteBuyer(String buyer_id) {
		ServiceResult result = ServiceResult.FAILED;
		int deleteResult = dao.deleteBuyer(buyer_id);
		if (deleteResult > 0) {
			result = ServiceResult.OK;
		}
		return result;
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

	@Override
	public int retrieveBuyerCount(PagingVO<BuyerVO> pagingVo) {
		return dao.selectBuyerCount(pagingVo);
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVo) {
		List<BuyerVO> buyerList = dao.selectBuyerList(pagingVo);
		if (buyerList == null || buyerList.size() == 0) {
			throw new CustomException("거래처가 존재하지 않습니다.");
		}
		return buyerList;
	}
}