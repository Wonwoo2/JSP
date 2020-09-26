package kr.or.ddit.buyer.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelData;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class BuyerInsertController {
	
	private IBuyerService service = BuyerServiceImpl.getInstance();
	private IOtherDAO dao = OtherDAOImpl.getInstance();
	
	@URIMapping(value = "/buyer/buyerRegist.do", method = HttpMethod.GET)
	public String getBuyerRegistForm(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = dao.selectLprodGuList();
		req.setAttribute("lprodList", lprodList);
		return "buyer/buyerInsertForm";
	}
	
	@URIMapping(value = "/buyer/buyerRegist.do", method = HttpMethod.POST)
	public String buyerRegist(@ModelData(name = "buyer") BuyerVO buyer, HttpServletRequest req) {
		Map<String, StringBuffer> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		CommonValidator<BuyerVO> validator = new CommonValidator<>();
		
		String goPage = null;
		String msg = null;
		boolean valid = validator.validate(buyer, errors, InsertGroup.class);
		if (valid) {
			ServiceResult result = service.createBuyer(buyer);
			switch (result) {
			case FAILED:
				goPage = "buyer/buyerInsertForm";
				msg = "서버 문제로 등록이 완료되지 않았습니다. 잠시 후 다시 시도해주세요.";
				break;
			default:
				// PostRedirectGet Pattern(prg pattern)
				goPage = "redirect:/buyer/buyerView.do?what=" + buyer.getBuyer_id();
				break;
			}
		} else {
			goPage = "buyer/buyerInsertForm";
		}
		
		req.setAttribute("msg", msg);
		
		return goPage;
	}
}