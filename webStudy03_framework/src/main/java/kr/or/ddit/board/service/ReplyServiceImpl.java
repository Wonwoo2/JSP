package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IReplyDAO;
import kr.or.ddit.board.dao.ReplyDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyServiceImpl implements IReplyService {
	
	private static IReplyService service;
	private IReplyDAO dao = ReplyDAOImpl.getInstance();
	
	private ReplyServiceImpl() { }
	
	public static IReplyService getInstance() {
		if (service == null) {
			service = new ReplyServiceImpl();
		}
		return service;
	}
	
	@Override
	public int readReplyCount(PagingVO<ReplyVO> pagingVo) {
		return dao.selectReplyCount(pagingVo);
	}

	@Override
	public List<ReplyVO> readReplyList(PagingVO<ReplyVO> pagingVo) {
		return dao.selectReplyList(pagingVo);
	}
}