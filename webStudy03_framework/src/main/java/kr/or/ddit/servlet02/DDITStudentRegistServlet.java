package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.TemplateUtils;

@CommandHandler
public class DDITStudentRegistServlet {

	@URIMapping(value = "/ddit/studentRegist.do", method = HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		String pattern = "<tr><th>%s</th><td>%s</td></tr>";
		StringBuffer content = new StringBuffer();
		for(Entry<String, String[]> entry :parameterMap.entrySet()) {
			content.append( String.format(pattern, entry.getKey(), Arrays.toString(entry.getValue())) );
		}
		
		Map<String, Object> attributeMap = new LinkedHashMap<>();
		attributeMap.put("content", content);
		
		String html = TemplateUtils.readAndReplace("/kr/or/ddit/servlet02/dditRegist.tmpl", attributeMap);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(html);
		out.close();
		return null;
	}
}