package kr.or.ddit.servlet06;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;
import kr.or.ddit.filter.wrapper.PartWrapper;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class FileUploadController { 

	@URIMapping(value = "/fileUpload.do", method = HttpMethod.POST)
	public String doPost(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploader = req.getParameter("uploader");
//		String uploadFile = req.getParameter("uploadFile");
		
		if (req instanceof FileUploadRequestWrapper) {
			// 파일 업로드
			String folderURL = "/images";
			String folderPath = req.getServletContext().getRealPath(folderURL);
			
			File folder = new File(folderPath);
			
			List<PartWrapper> partWrapperList = ((FileUploadRequestWrapper) req).getPartWrappers("uploadFile");
			if (partWrapperList != null) {
				List<String> saveURLs = new ArrayList<>();
				session.setAttribute("saveURLs", saveURLs);
				for (PartWrapper wrapper : partWrapperList) {
					wrapper.saveToRealPath(folder);
					System.out.printf("uploader : %s, uploadFile : %s, uploadedURL : %s \n", uploader, 
							wrapper.getOriginalFileName(), folderURL + "/" + wrapper.getSaveName());
					saveURLs.add(folderURL + "/" + wrapper.getSaveName());
				}	// for end
			}	// partWrapperList null 체크 end
		}
		
		return "redirect:/13/fileUploadForm.jsp";
		/*Part uploadFilePart = req.getPart("uploadFile");
		if (!uploadFilePart.getContentType().startsWith("image/")) {
			resp.sendError(400, "이미지 외에는 업로드가 불가합니다.");
			return;
		}
		
		
		// Content-Disposition: form-data; name="uploadFile"; filename=""
		String header = uploadFilePart.getHeader("Content-Disposition");
		int idx = header.indexOf("filename");
		idx = header.indexOf("=", idx);
		header = header.substring(idx + 1);
		String fileName = header.replace("\"", "");		// 원본 파일명
		String saveName = UUID.randomUUID().toString();
		File saveFile = new File(folder, saveName);
		// 파일 읽기
		try (
				InputStream is = uploadFilePart.getInputStream();	
		) {
			FileUtils.copyInputStreamToFile(is, saveFile);
		}*/
	}
}
