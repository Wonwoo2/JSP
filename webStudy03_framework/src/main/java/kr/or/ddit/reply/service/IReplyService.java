package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface IReplyService {
	public ServiceResult createReply(ReplyVO reply);
	
	public int readReplyCount(PagingVO<ReplyVO> pagingVo);
	
	public List<ReplyVO> readReplyList(PagingVO<ReplyVO> pagingVo);
	
	public ServiceResult modifyReply(ReplyVO reply);
	
	public ServiceResult removeReply(ReplyVO reply);
}