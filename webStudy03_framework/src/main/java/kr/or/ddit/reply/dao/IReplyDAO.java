package kr.or.ddit.reply.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface IReplyDAO {
	public int insertReply(ReplyVO reply);
	
	public int selectReplyCount(PagingVO<ReplyVO> pagingVo);
	
	public List<ReplyVO> selectReplyList(PagingVO<ReplyVO> pagingVo);
	
	public int updateReply(ReplyVO reply);
	
	public int deleteReply(int rep_no);
}