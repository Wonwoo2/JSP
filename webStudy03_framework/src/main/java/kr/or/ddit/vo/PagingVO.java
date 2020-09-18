package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagingVO<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int totalRecord;
	private int currentPage;
	private int screenSize = 10;
	private int blockSize = 5;
	
	private int totalPage;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private String searchWord;
	private List<T> data; 
	
	private PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (totalRecord + (screenSize - 1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize - 1);
		endPage = (currentPage + (blockSize - 1)) / blockSize * blockSize;
		startPage = endPage - (blockSize - 1);
	}
	
	private final String PATTERN = "<a href='#' data-page='%d' class='%s'>%s</a>";
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		endPage = totalPage < endPage ? totalPage : endPage;
		if (startPage > blockSize) {
			html.append(String.format(PATTERN, (startPage - blockSize), "previous", "이전"));
		}
		for (int page = startPage; page <= endPage; page ++) {
			if (currentPage == page) {
				html.append(String.format(PATTERN, page, "current", page));
			} else {
				html.append(String.format(PATTERN, page, "", page));
			}
		}
		if (endPage < totalPage) {
			html.append(String.format(PATTERN, (endPage + 1), "next", "다음"));
		}
		return html.toString();
	}
}