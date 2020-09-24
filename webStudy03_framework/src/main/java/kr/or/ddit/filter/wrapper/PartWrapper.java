package kr.or.ddit.filter.wrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

public class PartWrapper implements Part {
	private Part part;
	private String originalFileName;
	private String saveName;
	
	public PartWrapper(Part part) {
		this.part = part;
		String header = part.getHeader("Content-Disposition");
		int idx = header.indexOf("filename");
		idx = header.indexOf("=", idx);
		header = header.substring(idx + 1);
		originalFileName = header.replace("\"", "");
		saveName = UUID.randomUUID().toString();
	}
	
	public void saveToRealPath(File folder) throws IOException {
		File saveFile = new File(folder, saveName);
		try (
				InputStream is = getInputStream();	
		) {
			FileUtils.copyInputStreamToFile(is, saveFile);
		}
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public String getSaveName() {
		return saveName;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return part.getInputStream();
	}

	@Override
	public String getContentType() {
		return part.getContentType();
	}

	@Override
	public String getName() {
		return part.getName();
	}

	@Override
	public long getSize() {
		return part.getSize();
	}

	@Override
	public void write(String fileName) throws IOException {
		
	}

	@Override
	public void delete() throws IOException {
		part.delete();
	}

	@Override
	public String getHeader(String name) {
		return part.getHeader(name);
	}

	@Override
	public Collection<String> getHeaders(String name) {
		return part.getHeaders(name);
	}

	@Override
	public Collection<String> getHeaderNames() {
		return part.getHeaderNames();
	}
}