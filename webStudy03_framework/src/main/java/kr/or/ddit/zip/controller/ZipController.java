package kr.or.ddit.zip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.ZipVO;
import kr.or.ddit.zip.dao.IZipDAO;
import kr.or.ddit.zip.dao.ZipDAOImpl;

@WebServlet("/searchZip.do")
public class ZipController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IZipDAO dao = ZipDAOImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String pageParam = req.getParameter("page");
		String searchWord = req.getParameter("searchWord");
		String searchType = req.getParameter("searchType");
		PagingVO<ZipVO> pagingVo = new PagingVO<ZipVO>();
		SearchVO searchVo = new SearchVO();
		searchVo.setSearchType(searchType);
		searchVo.setSearchWord(searchWord);
		pagingVo.setSearchVo(searchVo);
		int totalRecord = dao.selectTotalCount(pagingVo);
		pagingVo.setTotalRecord(totalRecord);
		int currentPage = 1;
		if (StringUtils.isNotBlank(pageParam) && StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		pagingVo.setCurrentPage(currentPage);
		
		List<ZipVO> zipList = dao.selectZipList(pagingVo);
		
		pagingVo.setData(zipList);
		
		resp.setContentType("application/json;charset=UTF-8");
		try (
			PrintWriter out = resp.getWriter();	
		) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, pagingVo);
		}
	}
}