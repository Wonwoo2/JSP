package kr.or.ddit.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"lprod_id"})
public class LProdVO {
	@NotBlank
	private Integer lprod_id;
	@NotBlank
	@Size(max = 4)
	private String lprod_gu;
	@NotBlank
	@Size(max = 40)
	private String lprod_nm;
}