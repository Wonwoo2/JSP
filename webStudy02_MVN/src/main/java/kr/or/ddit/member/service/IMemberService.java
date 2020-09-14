package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.vo.MemberVO;

/**
 *  회원관리 Business Logic Layer
 */
public interface IMemberService {
	
	/**
	 * 회원 가입
	 * @param member
	 * @return PKDUPLICATED, OK, FAILED 
	 */
	public ServiceResult registMember(MemberVO member);
	
	/**
	 * 회원 목록 조회(관리자용)
	 * @return
	 */
	public List<MemberVO> retrieveMemberList();
	
	/**
	 * 회원 상세 조회(관리자용)
	 * @param mem_id
	 * @return 존재하지 않는 경우, {@link CustomException} 발생
	 */
	public MemberVO retrieveMember(String mem_id);
	
	/**
	 * 자기 정보 수정
	 * @param member
	 * @return 존재하지 않는 경우, {@link CustomException} 발생,  INVALIDPASSWORD, OK, FALIED
	 */
	public ServiceResult modifyMember(MemberVO member);
	
	/**
	 * 회원 탈퇴
	 * @param member
	 * @return 존재하지 않는 경우, {@link CustomException} 발생,  INVALIDPASSWORD, OK, FALIED
	 */
	public ServiceResult removeMember(MemberVO member);
}