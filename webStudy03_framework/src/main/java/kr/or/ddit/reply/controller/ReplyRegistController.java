package kr.or.ddit.reply.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.resolvers.ModelData;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.vo.ReplyVO;

@CommandHandler
public class ReplyRegistController {

	private IReplyService service = ReplyServiceImpl.getInstance();

	public String registReply(@ModelData(name = "reply") ReplyVO reply, HttpServletResponse resp)
			throws ServletException, IOException {
		ServiceResult result = service.createReply(reply);
		
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		
		resp.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = resp.getWriter();) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, resultMap);
		}
		return null;
	}
}
