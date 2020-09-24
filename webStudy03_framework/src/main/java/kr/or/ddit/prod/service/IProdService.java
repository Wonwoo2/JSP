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
	
	/**
	 * 검색 조건에 맞는 전체 상품 레코드 수
	 * @param pagingVo
	 * @return
	 */
	public int retrieveProdCount(PagingVO<ProdVO> pagingVo);
	
	/**
	 * 검색 조건에 맞는 상품 목록
	 * @param pagingVo
	 * @return
	 */
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVo);
	
	/**
	 * 상품 수정
	 * @param prod
	 * @return
	 */
	public ServiceResult modifyProd(ProdVO prod);
}