package kr.or.ddit.member.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class MemberControllerPsedo {
	@URIMapping("/command1")
	public void handler1() {
		
	}
	
	@URIMapping("/command2")
	public void handler2(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	@URIMapping("/command3")
	public String handler3() {
		return "view.jsp";
	}
	
	@URIMapping("/command4")
	public String handler4(HttpServletRequest request, HttpServletResponse response) {
		return "view.jsp";
	}
}
