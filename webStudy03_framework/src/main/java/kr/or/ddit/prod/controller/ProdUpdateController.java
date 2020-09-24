package kr.or.ddit.prod.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelData;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdUpdateController {
	
	private IProdService service = ProdServiceImpl.getInstance();
	private IOtherDAO dao = OtherDAOImpl.getInstance();
	
	@URIMapping(value = "/prod/prodUpdate.do", method = HttpMethod.GET)
	public String getUpdateForm(@RequestParameter(name = "what") String prod_id, 
			HttpServletRequest request) {
		addAttribute(request);
		String goPage = null;
		
		ProdVO prod = service.retrieveProd(prod_id);
		request.setAttribute("prod", prod);
		request.setAttribute("command", "update");
		
		goPage = "prod/prodForm";
		return goPage;
	}
	
	@URIMapping(value = "/prod/prodUpdate.do", method = HttpMethod.POST)
	public String modifyProd(@ModelData(name = "prod") ProdVO prod,
			HttpServletRequest request) {
		String goPage = null;
		String msg = null;
	
		Map<String, StringBuffer> errors = new LinkedHashMap<>();
		request.setAttribute("errors", errors);
		CommonValidator<ProdVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(prod, errors, UpdateGroup.class);
		if (valid) {
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case FAILED:
				goPage = "prod/prodForm";
				msg = "서버 문제로 수정이 완료되지 않았습니다. 잠시 후 다시 시도해주세요.";
				break;
			default:
				// PostRedirectGet Pattern(prg pattern)
				goPage = "redirect:/prod/prodView.do?what=" + prod.getProd_id();
				break;
			}
		} else {
			goPage = "prod/prodForm";
		}
		
		request.setAttribute("msg", msg);
		return goPage;
	}
	
	public void addAttribute(HttpServletRequest req){
		req.setAttribute("lprodList", dao.selectLprodGuList());
		req.setAttribute("buyerList", dao.selectBuyerList(null));
	}
}
