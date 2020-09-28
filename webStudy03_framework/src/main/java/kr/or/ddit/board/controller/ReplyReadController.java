package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

@CommandHandler
public class ReplyReadController {
	
	private IReplyService service = ReplyServiceImpl.getInstance();
	
	@URIMapping(value = "/reply/replyView.do", method = HttpMethod.GET)
	public String getReplyList(@RequestParameter(name = "what", required = true) int bo_no, 
							@RequestParameter(name = "page", required = false, defaultValue = "1") int currentPage, 
							HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ReplyVO searchDetail = new ReplyVO();
		searchDetail.setBo_no(bo_no);
		PagingVO<ReplyVO> pagingVo = new PagingVO<>(3, 5);
		pagingVo.setSearchDetail(searchDetail);
		
		int totalRecord = service.readReplyCount(pagingVo);
		pagingVo.setTotalRecord(totalRecord);
		pagingVo.setCurrentPage(currentPage);
		
		List<ReplyVO> replyList = service.readReplyList(pagingVo);
		pagingVo.setData(replyList);
		
		String accept = request.getHeader("accept");
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			response.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try (
					PrintWriter out = response.getWriter();
			) {
				mapper.writeValue(out, pagingVo);
			}
		}
		return null;
	}
}