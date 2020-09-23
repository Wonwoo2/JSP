package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IViewProcessor {
	public void setPrefix(String prefix);
	
	public void setSuffix(String suffix);
	
	public void viewProcess(String viewName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}