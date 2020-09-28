package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"att_no", "bo_no"})
public class AttatchVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer att_no;
	private Integer bo_no;
	@NotBlank
	@Size(max = 200)
	private String att_filename;
	@NotBlank
	@Size(max = 400)
	private String att_savename;
	@Size(max = 160)
	private String att_mime;
	private Integer att_filesize;
	@NotBlank
	@Size(max = 40)
	private String att_fancysize;
	private Integer att_downcount;
}
