package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

@CommandHandler
public class BoardReadController {
	
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@URIMapping(value = "/board/boardList.do", method = HttpMethod.GET)
	public String getBoadList(@RequestParameter(name = "page", required = false, defaultValue = "1") int currentPage,
								HttpServletRequest request, HttpServletResponse response) throws IOException {
		PagingVO<BoardVO> pagingVo = new PagingVO<>();
		
		int totalRecord = service.readBoardCount(pagingVo);
		
		pagingVo.setTotalRecord(totalRecord);
		pagingVo.setCurrentPage(currentPage);
		
		List<BoardVO> boardList = service.readBoardList(pagingVo);
		
		pagingVo.setData(boardList);
		String accept = request.getHeader("Accept");
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			response.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try (PrintWriter out = response.getWriter();) {
				mapper.writeValue(out, pagingVo);
			}
			return null;
		} else {
			request.setAttribute("pagingVo", pagingVo);
			return "board/boardList";
		}
	}
	
	@URIMapping(value = "/board/boardView.do", method = HttpMethod.GET)
	public String getBoardView(@RequestParameter(name = "what", required = true) int bo_no,
								HttpServletRequest request) {
		BoardVO boardVo = service.readBoard(bo_no);
		request.setAttribute("board", boardVo);
		return "board/boardView";
	}
}