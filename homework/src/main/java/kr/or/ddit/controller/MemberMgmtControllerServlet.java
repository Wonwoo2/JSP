package kr.or.ddit.controller;

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
		String accept = req.getHeader("Accept");
		
		String userid = req.getParameter("userid");
		
		String submitType = req.getParameter("");
		
		if(StringUtils.isNotBlank(userid) && StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			MemberVO memVo = service.getMember(userid);
			
			ObjectMapper mapper = new ObjectMapper();
			try (
					PrintWriter out = resp.getWriter();
			) {
				mapper.writeValue(out, memVo);
			}
		} else {
			List<MemberVO> memberList = service.readMembers(null);
			
			req.setAttribute("memberList", memberList);
			
			req.getRequestDispatcher("/WEB-INF/MemberMgmt.jsp").forward(req, resp);
		}
	}
	
/*	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(StringUtils.isNotBlank(userid) && StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			MemberVO memVo = service.getMember(userid);
			
			ObjectMapper mapper = new ObjectMapper();
			try (
					PrintWriter out = resp.getWriter();
			) {
				mapper.writeValue(out, memVo);
			}
		}
	}
*/}