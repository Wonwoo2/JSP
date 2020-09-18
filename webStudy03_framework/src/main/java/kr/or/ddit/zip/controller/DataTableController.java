package kr.or.ddit.zip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipVO;
import kr.or.ddit.zip.dao.IZipDAO;
import kr.or.ddit.zip.dao.ZipDAOImpl;

@WebServlet("/dataTable.do")
public class DataTableController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IZipDAO dao = ZipDAOImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String draw = req.getParameter("draw");
		String startParam = req.getParameter("start");
		String lengthParam = req.getParameter("length");
		String search = req.getParameter("search[value]");
		
		PagingVO<ZipVO> pagingVo = new PagingVO<ZipVO>();
		
		int currentPage = 1;
		if (StringUtils.isNumeric(startParam) && StringUtils.isNumeric(lengthParam)) {
			int start = Integer.parseInt(startParam);
			int length = Integer.parseInt(lengthParam);
			pagingVo.setScreenSize(length);
			currentPage = (start + length) / length;
		}
		
		int totalCount = dao.selectTotalCount(pagingVo);
		pagingVo.setSearchWord(search);
		
		int totalSearchCount = dao.selectTotalCount(pagingVo);
		
		pagingVo.setTotalRecord(totalCount);
		pagingVo.setCurrentPage(currentPage);
		List<ZipVO> zipList = dao.selectZipList(pagingVo);
		
		Map<String, Object> target = new HashMap<>();
		target.put("draw", draw);
		target.put("recordsTotla", totalCount);
		target.put("recordsFiltered", totalSearchCount);
		target.put("data", zipList);
		
		resp.setContentType("application/json;charset=UTF-8");
		try (
			PrintWriter out = resp.getWriter();	
		) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, target);
		}
	}
}
