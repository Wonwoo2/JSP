package kr.or.ddit.buyer.controller;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;

@CommandHandler
public class BuyerDeleteController {
	
	private IBuyerService service = BuyerServiceImpl.getInstance();
	
	@URIMapping(value = "/buyer/buyerDelete.do", method = HttpMethod.GET)
	public String buyerDelete(@RequestParameter(name = "what") String buyer_id) {
		String goPage = null;
		
		ServiceResult result = service.deleteBuyer(buyer_id);
		if (ServiceResult.FAILED.equals(result)) {
			goPage = "redirect:/buyer/buyerView.jsp?what=" + buyer_id;
		} else {
			goPage = "redirect:/buyer/buyerList.do";
		}
		return goPage;
	}
}