package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;
import kr.or.ddit.filter.wrapper.PartWrapper;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelData;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdInsertController {
	
	private IProdService service = ProdServiceImpl.getInstance();
	private IOtherDAO dao = OtherDAOImpl.getInstance();
	
	public void addAttribute(HttpServletRequest req){
		req.setAttribute("lprodList", dao.selectLprodGuList());
		req.setAttribute("buyerList", dao.selectBuyerList(null));
	}
	
	@URIMapping(value = "/prod/prodInsert.do", method = HttpMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		
		String goPage = "prod/prodForm";
		return goPage;
	}
	
	@URIMapping(value = "/prod/prodInsert.do", method = HttpMethod.POST)
	public String doPost(@ModelData(name = "prod") ProdVO prod, HttpServletRequest req) throws ServletException, IOException {
		// 검증 전에 prod_img 결정
		if (req instanceof FileUploadRequestWrapper) {
			
			
			PartWrapper partWrapper = ((FileUploadRequestWrapper) req).getPartWrapper("prod_image");
			if (partWrapper != null) {
				prod.setProd_image(partWrapper);
			}
		}
		
		Map<String, StringBuffer> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		CommonValidator<ProdVO> validator = new CommonValidator<>();
		
		String goPage = null;
		String msg = null;
		boolean valid = validator.validate(prod, errors, InsertGroup.class);
		if (valid) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case FAILED:
				goPage = "prod/prodForm";
				msg = "서버 문제로 등록이 완료되지 않았습니다. 잠시 후 다시 시도해주세요.";
				break;
			default:
				// PostRedirectGet Pattern(prg pattern)
				goPage = "redirect:/prod/prodView.do?what=" + prod.getProd_id();
				break;
			}
		} else {
			goPage = "prod/prodForm";
		}
		
		req.setAttribute("msg", msg);
		return goPage;
	}
}