package kr.or.ddit.commons.service;

import kr.or.ddit.vo.MemberVO;

/**
 *  인증 처리를 위한 Business Logic Layer
 */
public interface IAuthenticateService {
	public Object authenticate(MemberVO member);
}