package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.reply.dao.IReplyDAO;
import kr.or.ddit.reply.dao.ReplyDAOImpl;
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
	public ServiceResult createReply(ReplyVO reply) {
		ServiceResult result = ServiceResult.FAILED;
		int insertResult = dao.insertReply(reply);
		if (insertResult > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}
	
	@Override
	public int readReplyCount(PagingVO<ReplyVO> pagingVo) {
		return dao.selectReplyCount(pagingVo);
	}

	@Override
	public List<ReplyVO> readReplyList(PagingVO<ReplyVO> pagingVo) {
		return dao.selectReplyList(pagingVo);
	}

	@Override
	public ServiceResult modifyReply(ReplyVO reply) {
		return null;
	}

	@Override
	public ServiceResult removeReply(ReplyVO reply) {
		return null;
	}
}