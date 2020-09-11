package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDao {
	public int insertMember(MemberVO memVo);
	
	public int updateMember(MemberVO memVo);
	
	public int deleteMember(String userid);
	
	public MemberVO getMember(String userid);
	
	public List<MemberVO> selectMember(MemberVO memVo);
}