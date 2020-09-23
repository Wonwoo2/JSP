package kr.or.ddit.zip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.ZipVO;
import kr.or.ddit.zip.dao.IZipDAO;
import kr.or.ddit.zip.dao.ZipDAOImpl;

@CommandHandler
public class DataTableController {

	private IZipDAO dao = ZipDAOImpl.getInstance();
	
	@URIMapping(value = "/dataTable.do", method = HttpMethod.GET)
	public String doGet(@RequestParameter(name = "draw", required = true) String draw, 
						@RequestParameter(name = "start", required = true, defaultValue = "0") int start,
						@RequestParameter(name = "length", required = true, defaultValue = "0") int length,
						@RequestParameter(name = "search[value]", required = false) String search,
						HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*String draw = req.getParameter("draw");
		String startParam = req.getParameter("start");
		String lengthParam = req.getParameter("length");
		String search = req.getParameter("search[value]");*/
		
		PagingVO<ZipVO> pagingVo = new PagingVO<ZipVO>();
		
		SearchVO searchVo = new SearchVO();
		pagingVo.setSearchVo(searchVo);
		
		int currentPage = 1;
//		if (StringUtils.isNumeric(startParam) && StringUtils.isNumeric(lengthParam)) {
//			int start = Integer.parseInt(startParam);
//			int length = Integer.parseInt(lengthParam);
//			pagingVo.setScreenSize(length);
//			currentPage = (start + length) / length;
//		}
		pagingVo.setScreenSize(length);
		currentPage = (start + length) / length;
		
		int totalCount = dao.selectTotalCount(pagingVo);
		searchVo.setSearchWord(search);
		pagingVo.setSearchVo(searchVo);
		
		int totalSearchCount = dao.selectTotalCount(pagingVo);
		
		pagingVo.setTotalRecord(totalCount);
		pagingVo.setCurrentPage(currentPage);
		
		List<ZipVO> zipList = dao.selectZipList(pagingVo);
		
		Map<String, Object> target = new HashMap<>();
		
		target.put("draw", draw);
		target.put("recordsTotal", totalCount);
		target.put("recordsFiltered", totalSearchCount);
		target.put("data", zipList);
		
		resp.setContentType("application/json;charset=UTF-8");
		try (
			PrintWriter out = resp.getWriter();	
		) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, target);
		}
		return null;
	}
}
