package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"rep_no", "bo_no"})
public class ReplyVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer rep_no;
	private Integer bo_no;
	@Size(max = 800)
	private String rep_content;
	@NotBlank
	@Size(max = 60)
	private String rep_writer;
	@NotBlank
	@Size(max = 7)
	private String rep_date;
	@NotBlank
	@Size(max = 200)
	private String rep_ip;
	@NotBlank
	@Size(max = 200)
	private String rep_pass;
}
