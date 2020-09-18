package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "zipcode")
public class ZipVO {
	private String zipcode;
	private String sido;
	private String gugun;
	private String dong;
	private String bunji;
	
	public String getAddress() {
		StringBuffer address = new StringBuffer();
		address.append(sido);
		if (gugun != null) {
			address.append(" " + gugun);
		}
		if (dong != null) { 
			address.append(" " + dong);
		}
		if (bunji != null) {
			address.append(" " + bunji);
		}
		return address.toString();
	}
}