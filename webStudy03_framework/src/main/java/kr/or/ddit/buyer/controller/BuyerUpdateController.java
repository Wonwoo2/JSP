package kr.or.ddit.buyer.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.resolvers.ModelData;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class BuyerUpdateController {
	private IBuyerService service = BuyerServiceImpl.getInstance();
	
	public String getBuyerUpdateForm(@RequestParameter(name = "what") String buyer_id, HttpServletRequest req) {
		BuyerVO buyer = service.retrieveBuyer(buyer_id);
		req.setAttribute("buyer", buyer);
		return "buyer/buyerUpdateForm";
	}
	
	public String buyerModify(@ModelData(name = "buyer") BuyerVO buyer, HttpServletRequest req) {
		Map<String, StringBuffer> errors = new LinkedHashMap<>();
		req.setAttribute("buyer", buyer);
		req.setAttribute("errors", errors);
		
		CommonValidator<BuyerVO> validator = new CommonValidator<>();
		
		String goPage = null;
		String msg = null;
		boolean valid = validator.validate(buyer, errors, UpdateGroup.class);
		if (valid) {
			ServiceResult result = service.modifyBuyer(buyer);
			switch (result) {
			case FAILED:
				goPage = "buyer/buyerUpdateForm";
				msg = "서버 문제로 등록이 완료되지 않았습니다. 잠시 후 다시 시도해주세요.";
				break;
			default:
				// PostRedirectGet Pattern(prg pattern)
				goPage = "redirect:/buyer/buyerView.do?what=" + buyer.getBuyer_id();
				break;
			}
		} else {
			goPage = "buyer/buyerRegistForm";
		}
		
		req.setAttribute("msg", msg);
		return goPage;
	}
}