package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

@CommandHandler
public class BuyerRetrieveController {

	private IBuyerService service = BuyerServiceImpl.getInstance();
	
	@URIMapping(value = "/buyer/buyerView.do", method = HttpMethod.GET)
	public String getBuyerView(@RequestParameter(name = "what") String buyer_id, HttpServletRequest req) throws ServletException, IOException {
		BuyerVO buyer = service.retrieveBuyer(buyer_id);
		req.setAttribute("buyer", buyer);
		return "buyer/buyerView";
	}
	
	@URIMapping(value = "/buyer/buyerList.do", method = HttpMethod.GET)
	public String getBuyerListView(@RequestParameter(name = "page", required = false, defaultValue = "1") int currentPage, 
			HttpServletRequest req) {
		PagingVO<BuyerVO> pagingVo = new PagingVO<>();
		int totalRecord = service.retrieveBuyerCount(pagingVo);
		pagingVo.setTotalRecord(totalRecord);
		pagingVo.setCurrentPage(currentPage);
		
		List<BuyerVO> buyerList = service.retrieveBuyerList(pagingVo);
		pagingVo.setData(buyerList);
		
		req.setAttribute("pagingVo", pagingVo);
		return "buyer/buyerList";
	}
}