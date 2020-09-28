package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "bo_no")
public class BoardVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rn;
	private Integer bo_no;
	@NotBlank
	@Size(max = 120)
	private String bo_title;
	@NotBlank
	@Size(max = 60)
	private String bo_writer;
	@NotBlank
	@Size(max = 7)
	private String bo_date;
	@Size(max = 4000)
	private String bo_content;
	@NotBlank
	@Size(max = 200)
	private String bo_pass;
	@NotBlank
	@Size(max = 200)
	private String bo_ip;
	private Integer bo_hit;
	private Integer bo_parent;
	
	private List<ReplyVO> replyList;
	private List<AttatchVO> attatchList;
}