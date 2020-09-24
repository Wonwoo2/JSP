package kr.or.ddit.filter.wrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

public class FileUploadRequestWrapper extends HttpServletRequestWrapper {

	private Map<String, List<PartWrapper>> partWrapperMap;
	
	public FileUploadRequestWrapper(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		partWrapperMap = new LinkedHashMap<>();
		
		parseRequest(request);
	}

	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			if (part.getContentType() == null || part.getSize() == 0) {
				continue;
			}
			String partName = part.getName();
			PartWrapper wrapper = new PartWrapper(part);
			List<PartWrapper> partWrapperList = null;
			if (partWrapperMap.containsKey(partName)) {
				partWrapperList = partWrapperMap.get(partName);
			} else {
				partWrapperList = new ArrayList<>();
				partWrapperMap.put(partName, partWrapperList);
			}
			partWrapperList.add(wrapper);
		}
	}
	
	public PartWrapper getPartWrapper(String partName) {
		List<PartWrapper> partWrapperList = partWrapperMap.get(partName);
		PartWrapper wrapper = null;
		if (partWrapperList != null && partWrapperList.size() > 0) {
			wrapper = partWrapperList.get(0);
		}
		return wrapper;
	}
	
	public List<PartWrapper> getPartWrappers(String partName) {
		return partWrapperMap.get(partName);
	}
	
	public Map<String, List<PartWrapper>> getPartWrapperMap() {
		return partWrapperMap;
	}
	
	public void deleteAll() throws IOException {
		for (Entry<String, List<PartWrapper>> entry : partWrapperMap.entrySet()) {
			for (PartWrapper partWrapper : entry.getValue()) {
				partWrapper.delete();
			}
		}
	}
}