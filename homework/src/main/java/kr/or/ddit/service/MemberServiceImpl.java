package kr.or.ddit.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.dao.IMemberDao;
import kr.or.ddit.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	IMemberDao dao = new MemberDaoImpl();
			
	@Override
	public int registMember(MemberVO memVo) {
		int result = -1;
		if(memVo != null) {
			result = dao.insertMember(memVo);
		}
		return result;
	}

	@Override
	public int editMember(MemberVO memVo) {
		int result = -1;
		if(memVo != null) {
			result = dao.updateMember(memVo);
		}
		return result;
	}

	@Override
	public int deleteMember(String userid) {
		int result = -1;
		if(StringUtils.isNotBlank(userid)) {
			result =  dao.deleteMember(userid);
		}
		return result;
	}

	@Override
	public MemberVO getMember(String userid) {
		return dao.getMember(userid);
	}
	
	@Override
	public List<MemberVO> readMembers(MemberVO memVo) {
		return dao.selectMember(memVo);
	}
}