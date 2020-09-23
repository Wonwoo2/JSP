package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class BuyerViewController {

	
	private IBuyerService service = BuyerServiceImpl.getInstance();
	
	@URIMapping(value = "/buyer/buyerView.do", method = HttpMethod.GET)
	public String doGet(@RequestParameter(name = "what") String buyer_id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BuyerVO buyer = service.retrieveBuyer(buyer_id);
		
		req.setAttribute("buyer", buyer);
		
		String goPage = "buyer/buyerView";
		return goPage;
	}	
}