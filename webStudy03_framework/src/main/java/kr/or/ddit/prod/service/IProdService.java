package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 Business Logic Layer
 */
public interface IProdService {
	
	/**
	 * 신규 상품 등록
	 * @param prod
	 * @return OK, FAILED
	 */
	public ServiceResult createProd(ProdVO prod);
	
	/**
	 * 상품 상세 조회
	 * @param prod_id
	 * @return 존재하지 않는다면, CustomException 발생
	 */
	public ProdVO retrieveProd(String prod_id);
	
	public int retrieveProdCount(PagingVO<ProdVO> pagingVo);
	
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVo);
}