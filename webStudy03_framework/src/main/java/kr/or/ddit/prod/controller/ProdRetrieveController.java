package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelData;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdRetrieveController {
	
	private IProdService service = ProdServiceImpl.getInstance();
	private IOtherDAO dao = OtherDAOImpl.getInstance();

	@URIMapping(value = "/prod/prodView.do", method = HttpMethod.GET)
	public String prodView(@RequestParameter(name = "what") String what, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProdVO prod = service.retrieveProd(what);
		req.setAttribute("prod", prod);
		String goPage = "prod/prodView";
		return goPage;
	}
	
	@URIMapping(value = "/prod/prodList.do", method = HttpMethod.GET)
	public String prodListView(@RequestParameter(name="page", required = false, defaultValue = "1") int currentPage,
			@ModelData(name = "searchDetail") ProdVO searchDetail,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribue(req);
		
		PagingVO<ProdVO> pagingVo = new PagingVO<>();
		pagingVo.setSearchDetail(searchDetail);
		
		int totalRecord = service.retrieveProdCount(pagingVo);
		pagingVo.setTotalRecord(totalRecord);
		pagingVo.setCurrentPage(currentPage);
		
		List<ProdVO> prodList = service.retrieveProdList(pagingVo);
		pagingVo.setData(prodList);
		
		String accept = req.getHeader("Accept");
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try (PrintWriter out = resp.getWriter();) {
				mapper.writeValue(out, pagingVo);
			}
			return null;
		} else {
			req.setAttribute("pagingVo", pagingVo);
			String goPage = "prod/prodList";
			return goPage;
		}
	}
	
	@URIMapping(value = "/prod/buyerList.do")
	public String changeBuyer(@RequestParameter(name = "buyer_lgu") String buyer_lgu,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<BuyerVO> buyerList = dao.selectBuyerList(buyer_lgu);
		
		Map<String, List<BuyerVO>> paramMap = new LinkedHashMap<>();
		paramMap.put("buyerList", buyerList);
		String accept = req.getHeader("Accept");
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try (PrintWriter out = resp.getWriter();) {
				mapper.writeValue(out, paramMap);
			}
		}
		return null;
	}
	
	public void addAttribue(HttpServletRequest request) {
		request.setAttribute("lprodList", dao.selectLprodGuList());
		request.setAttribute("buyerList", dao.selectBuyerList(null));
	}
}