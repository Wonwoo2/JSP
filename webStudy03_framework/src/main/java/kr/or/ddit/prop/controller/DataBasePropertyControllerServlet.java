package kr.or.ddit.prop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.prop.service.DataBasePropertyServiceImpl;
import kr.or.ddit.prop.service.IDataBasePropertyService;
import kr.or.ddit.vo.DataBasePropertyVO;

@CommandHandler
public class DataBasePropertyControllerServlet {
	
	private IDataBasePropertyService service = DataBasePropertyServiceImpl.getInstance(); 
	
	@URIMapping(value = "/10/jdbcDesc.do", method = HttpMethod.GET)
	public String doGet(@RequestParameter(name = "property_name", required = true) String property_name,
				@RequestParameter(name = "property_value", required = true) String property_value,
				@RequestParameter(name = "description", required = true) String description, 
				HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		
		DataBasePropertyVO property = DataBasePropertyVO.getBuilder()
												.property_name(property_name)
												.property_value(property_value)
												.description(description)
												.build();
		List<DataBasePropertyVO> propertyList = service.readDataBaseProperties(property);
		List<String> propertyNameList = service.readAllProperty_names();
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			// Marshalling
			ObjectMapper mapper = new ObjectMapper();
			try(
					PrintWriter out = resp.getWriter();
			) {
				mapper.writeValue(out, propertyList);
				return null;
			}
		} else {
			req.setAttribute("property", property);
			req.setAttribute("propertyList", propertyList);
			req.setAttribute("propertyNameList", propertyNameList);
			
			String goPage = "10/jdbcDesc";
			return goPage;
		}
		
	}
}