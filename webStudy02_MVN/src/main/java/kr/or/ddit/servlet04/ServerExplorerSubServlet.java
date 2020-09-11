package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/subExplorer.do")
public class ServerExplorerSubServlet extends HttpServlet {
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
		String currentPath = req.getParameter("url");
		
		String realPath = application.getRealPath(currentPath);
		
		File folder = new File(realPath); 
		File[] listFiles = folder.listFiles();
		
		String contextRealPath = application.getRealPath("/");
		
		String pattern = "<li class='%s' id='%s'>%s</li>";
		
		PrintWriter out = resp.getWriter();
		out.print("<ul>");
		for (File subFile : listFiles) {
			String clz = subFile.isDirectory() ? "dir":"file";
			String fileAbPath = subFile.getAbsolutePath();
			String tmp = StringUtils.remove(fileAbPath, contextRealPath);
			String fileURI = tmp.replace(File.separatorChar, '/');
			fileURI = fileURI.startsWith("/") ? fileURI:"/" + fileURI;
			
			out.println(String.format(pattern, clz, fileURI, subFile.getName()));
		}
		out.print("</ul>");
	}
}
