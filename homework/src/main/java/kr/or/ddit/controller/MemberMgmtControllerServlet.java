package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/MemberMgmt.do")
public class MemberMgmtControllerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	IMemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = req.getParameter("command");
		
		List<MemberVO> memberList = service.readMembers(null);
		
		req.setAttribute("memberList", memberList);
		
		StringBuffer url = new StringBuffer("/WEB-INF/");
		if(StringUtils.isNotBlank(command)) {
			if("insert".equals(command)) {
				url.append("MemberInsert.jsp");
			} else if("update".equals(command)) {
				url.append("MemberUpdate.jsp");
			} else if("select".equals(command)) {
				url.append("MemberList.jsp");
			}
		} else {
			url.append("MemberMgmt.jsp");
		}
		
		req.getRequestDispatcher(url.toString()).forward(req, resp);
	}
}