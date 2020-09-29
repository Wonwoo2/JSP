package kr.or.ddit.commons.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService {

	private IMemberDAO dao = MemberDAOImpl.getInstance();
	
	@Override
	public Object authenticate(MemberVO member) {
		MemberVO savedMember = dao.selectMember(member.getMem_id());
		Object result = null;
		if (savedMember == null) {
			result = ServiceResult.NOTEXIST;
		} else {
			String inputPass = SecurityUtils.encryptSha512(member.getMem_pass());
			String savedPass = savedMember.getMem_pass();
			
			if (savedPass.equals(inputPass)) {
				result = savedMember;
			} else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}
		return result;
	}
}