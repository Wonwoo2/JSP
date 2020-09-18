package kr.or.ddit.zip.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipVO;

public interface IZipDAO {
	public int selectTotalCount(PagingVO<ZipVO> pagingVo);
	public List<ZipVO> selectZipList(PagingVO<ZipVO> pagingVo);
}