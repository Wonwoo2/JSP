package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface IReplyService {
	
	public int readReplyCount(PagingVO<ReplyVO> pagingVo);
	
	public List<ReplyVO> readReplyList(PagingVO<ReplyVO> pagingVo);
}