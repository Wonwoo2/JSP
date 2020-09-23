package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Persistence Layer
 */
public interface IProdDAO {
	
	public int insertProd(ProdVO prod);
	
	public ProdVO selectProd(String prod_id);
	
	public int selectProdCount(PagingVO<ProdVO> pagingVo);
	/**
	 * 검색 조건 : 분류, 거래처, 상품명
	 * @param pagingVo
	 * @return
	 */
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVo);
}