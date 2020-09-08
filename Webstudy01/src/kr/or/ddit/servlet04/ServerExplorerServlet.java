package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/serverExplorer.do")
public class ServerExplorerServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentURL = req.getParameter("url");
		String accept = req.getHeader("Accept");
		
		String contextName = req.getParameter("contextName");
		
		ServletContext application = this.application;
		if(StringUtils.isNotBlank(contextName)) {
			application = getServletContext().getContext(contextName);
		}
		
		if(application == null) {
			resp.sendError(404, contextName + "컨텍스트는 존재하지 않습니다.");
			return;
		}
		
		String currentPath = "/";
		
		if(StringUtils.isNotBlank(currentURL)) {
			currentPath = currentURL;
		}
		
		String realPath = application.getRealPath(currentPath);
		File folder = new File(realPath);
		
		int statusCode = HttpServletResponse.SC_OK;
		if(!folder.exists()) {
			statusCode = HttpServletResponse.SC_NOT_FOUND;
		}
		if(folder.isFile()) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		if(statusCode != HttpServletResponse.SC_OK) {
			resp.sendError(statusCode);
			return;
		}
		
		File[] listFiles = folder.listFiles();
		
		FileWrapper[] wrappers = new FileWrapper[listFiles.length];
		for (int i = 0; i < wrappers.length; i ++) {
			wrappers[i] = new FileWrapper(listFiles[i], application);
		}
		
		if(accept.contains("json")) {
			// marshalling, mime
			resp.setContentType("application/json;charset=UTF-8");
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(wrappers);
			try (
				PrintWriter out = resp.getWriter()	
			) {
				// serialize
				out.print(json);
			}
		} else {
			req.setAttribute("listFiles", wrappers);
			req.getRequestDispatcher("/WEB-INF/views/serverExplorer.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> errors = new LinkedHashMap<>();
		boolean valid = validate(req, errors);
		if(valid) {
			// 파일에 대한 명령 처리
			CommandType cmdType = (CommandType) req.getAttribute("commandType");
			File srcFile = (File) req.getAttribute("srcFile");
			File destFolder = (File) req.getAttribute("destFolder");
			
			boolean result = cmdType.commandProcess(srcFile, destFolder);
			Map<String, Object> resultMap = Collections.singletonMap("success", result);
			
			resp.setContentType("application/json;charset=UTF-8");
			
			try (
					PrintWriter out = resp.getWriter();
			) {
				// marshlling + serialize
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, resultMap);
			}
		} else {
			int statusCode = (Integer) errors.get("statusCode");
			String message = (String) errors.get("message");
			resp.sendError(statusCode, message);
		}
	}

	private boolean validate(HttpServletRequest req, Map<String, Object> errors) {
		boolean valid = true;
		
		String cmdParam = req.getParameter("command");
		String srcParam = req.getParameter("srcFile");
		String destParam = req.getParameter("destFolder");
		
		int statusCode = HttpServletResponse.SC_OK;
		StringBuffer message = new StringBuffer();
		CommandType cmdType = null;
		if (StringUtils.isBlank(cmdParam)) {
			valid = false;
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			message.append("명령이 전달되지 않았음");
		} else {
			try {
				cmdType = CommandType.valueOf(cmdParam);
			} catch (IllegalArgumentException e) {
				valid = false;
				statusCode = HttpServletResponse.SC_BAD_REQUEST;
				message.append("처리할 수 없는 명령임");
			}
		}
		
		req.setAttribute("commandType", cmdType);
		
		File srcFile = null;
		if(StringUtils.isBlank(srcParam)) {
			valid = false;
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			message.append("source 파일의 파라미터가 존재하지 않음");
		} else {
			srcFile = new File(application.getRealPath(srcParam));
			if(!srcFile.exists()) {
				valid = false;
				statusCode = HttpServletResponse.SC_BAD_REQUEST;
				message.append("source 파일이 존재하지 않음");
			}
		}
		
		req.setAttribute("srcFile", srcFile);
		File destFolder = null;
		if(!CommandType.DELETE.equals(cmdType) && StringUtils.isBlank(destParam)) {
			valid = false;
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			message.append("대상 폴더 파라미터가 존재하지 않음");
		} else if(!CommandType.DELETE.equals(cmdType)){
			destFolder = new File(application.getRealPath(destParam));
			if(!destFolder.exists() || destFolder.isFile()) {
				valid = false;
				statusCode = HttpServletResponse.SC_BAD_REQUEST;
				message.append("대상 폴더가 존재하지 않거나, 폴더가 아닌 파일이 대상으로 지정됨");
			}
		}
	
		req.setAttribute("destFolder", destFolder);
		
		errors.put("statusCode", statusCode);
		errors.put("message", message.toString());
		
		return valid;
	}
}