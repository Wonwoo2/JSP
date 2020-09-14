package kr.or.ddit.commons.dao;

import kr.or.ddit.vo.MemberVO;

/**
 *  인증처리를 위한 Persistence Layer
 */
public interface IAuthenticateDAO {
	public MemberVO selectMember(MemberVO member);
}