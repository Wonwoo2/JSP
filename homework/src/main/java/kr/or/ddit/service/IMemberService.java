package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMemberService {
	public int registMember(MemberVO memVo);
	
	public int editMember(MemberVO memVo);
	
	public int deleteMember(String userid);
	
	public MemberVO getMember(String userid);
	
	public List<MemberVO> readMembers(MemberVO memVo);
}