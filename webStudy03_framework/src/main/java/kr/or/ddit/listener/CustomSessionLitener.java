package kr.or.ddit.listener;

import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import kr.or.ddit.vo.MemberVO;

//@WebListener
public class CustomSessionLitener implements HttpSessionAttributeListener {

	private static final String ADD = "ADD";
	private static final String REMOVE = "REMOVE";
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		eventHandle(event, ADD);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		eventHandle(event, REMOVE);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}
	
	public void eventHandle(HttpSessionBindingEvent event, String command) {
		String name = event.getName();
		if (!"member".equals(name)) {
			return;
		}
		
		MemberVO member = (MemberVO) event.getValue();
		Map<String, MemberVO> userList = (Map<String, MemberVO>) event.getSession().getServletContext().getAttribute("userList");
		
		if (ADD.equals(command)) {
			userList.put(member.getMem_id(), member);
		} else if (REMOVE.equals(command)) {
			userList.remove(member.getMem_id());
		}
	}
}