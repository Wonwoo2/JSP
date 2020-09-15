package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.commons.service.AuthenticateServiceImpl;
import kr.or.ddit.commons.service.IAuthenticateService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private IMemberDAO memberDao = MemberDAOImpl.getInstance();
	private IAuthenticateService authService = new AuthenticateServiceImpl(); 
	private static MemberServiceImpl memberService;
	
	private MemberServiceImpl() {
		super();
	}

	public static MemberServiceImpl getInstance() {
		if (memberService == null) {
			memberService = new MemberServiceImpl();
		}
		return memberService;
	}
	
	@Override
	public ServiceResult registMember(MemberVO member) {
		ServiceResult result = null;
		if (memberDao.selectMember(member.getMem_id()) != null) {
			result = ServiceResult.PKDUPLICATED;
		} else {
			if(memberDao.insertMember(member) > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		return memberDao.selectMemberList();
	}

	@Override
	public MemberVO retrieveMember(String mem_id) {
		MemberVO savedMember = memberDao.selectMember(mem_id);
		if (savedMember == null) {
			throw new CustomException(mem_id + "는 존재하지 않는 아이디입니다.");
		}
		return savedMember;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result = null;
		MemberVO authMember = (MemberVO) authService.authenticate(member);
		if (authMember == null) {
			result = ServiceResult.NOTEXIST;
		} else {
			if (authMember.equals(member)) {
				int updateResult = memberDao.updateMember(member);
				if (updateResult > 0) {
					result = ServiceResult.OK;
				} else {
					result = ServiceResult.FAILED;
				}
			}
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = null;
		MemberVO authMember = (MemberVO) authService.authenticate(member);
		if (authMember == null) {
			result = ServiceResult.NOTEXIST;
		} else {
			if (authMember.equals(member)) {
				int deleteResult = memberDao.deleteMember(member.getMem_id());
				if (deleteResult > 0) {
					result = ServiceResult.OK;
				} else {
					result = ServiceResult.FAILED;
				}
			}
		}
		return result;
	}
}