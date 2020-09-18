package kr.or.ddit.prop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prop.service.DataBasePropertyServiceImpl;
import kr.or.ddit.prop.service.IDataBasePropertyService;
import kr.or.ddit.vo.DataBasePropertyVO;

@WebServlet("/10/jdbcDesc.do")
public class DataBasePropertyControllerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	IDataBasePropertyService service = new DataBasePropertyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String property_name = req.getParameter("property_name");
		String property_value = req.getParameter("property_value");
		String description = req.getParameter("description");
		
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
			}
		} else {
			req.setAttribute("property", property);
			req.setAttribute("propertyList", propertyList);
			req.setAttribute("propertyNameList", propertyNameList);
			
			String goPage = "/WEB-INF/10/jdbcDesc.jsp";
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}