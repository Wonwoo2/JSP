package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	
	private IProdDAO dao = ProdDAOImpl.getInstance();
	
	private static IProdService service;
	
	private ProdServiceImpl() { }
	
	public static IProdService getInstance() {
		if (service == null) {
			service = new ProdServiceImpl();
		}
		return service;
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
		int insertResult = dao.insertProd(prod);
		if (insertResult > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prodVo = null;
		prodVo = dao.selectProd(prod_id);
		if (prodVo == null) {
			throw new CustomException(prod_id + "은 존재하지 않는 상품입니다.");
		}
		return prodVo;
	}

	@Override
	public int retrieveProdCount(PagingVO<ProdVO> pagingVo) {
		return dao.selectProdCount(pagingVo);
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVo) {
		return dao.selectProdList(pagingVo);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ServiceResult result = null;
		int updateResult = dao.updateProd(prod);
		if (updateResult > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
}