package kr.or.ddit.vo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import kr.or.ddit.filter.wrapper.PartWrapper;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.rule.TelNumberCheck;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Getter
//@Setter
/**
 * 한명의 회원 정보를 상세 조회할때 그 회원의 구매 상품 목록을 동시 조회. 회원 정보(MemberVO) 상품 정보(ProdVO) 테이블
 * 조인 결과 매핑 방법 
 * 1. 각 테이블로부터 결과를 매핑할 각각의 VO 정의. 
 * 2. 테이블간의 관계를 반영하여 VO 간의 관계 모델링 
 * 		1:N - has many 
 * 		1:1 - has a 
 * 3. resultType 대신 resultMap 사용. 
 * 		1:N - collection (주의! 중복 제거를 위한 식별자 사용ex) id) 
 * 		1:1 - association
 * 
 */
@EqualsAndHashCode(of = { "mem_id" })
@ToString(exclude = { "mem_regno1", "mem_regno2" })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVO implements Serializable, HttpSessionBindingListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(groups = { Default.class, DeleteGroup.class })
	@Size(max = 15, groups = { Default.class, DeleteGroup.class })
	private String mem_id;
	@NotBlank(groups = { Default.class, DeleteGroup.class })
	@Size(max = 15, groups = { Default.class, DeleteGroup.class })
	private String mem_pass;
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 20, groups = InsertGroup.class)
	private String mem_name;
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 6, groups = InsertGroup.class)
	private String mem_regno1;
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 7, groups = InsertGroup.class)
	private String mem_regno2;
	private String mem_bir;
	@NotBlank
	@Size(max = 7)
	private String mem_zip;
	@NotBlank
	@Size(max = 100)
	private String mem_add1;
	@NotBlank
	@Size(max = 80)
	private String mem_add2;
	@NotBlank
	@Size(max = 14)
	@TelNumberCheck
	private String mem_hometel;
	@NotBlank
	@Size(max = 14)
	@TelNumberCheck
	private String mem_comtel;
	@Size(max = 15)
	@TelNumberCheck
	private String mem_hp;
	@Size(max = 40)
	private String mem_job;
	@Size(max = 40)
	private String mem_like;
	@Size(max = 40)
	private String mem_memorial;
	private String mem_memorialday;
	private Integer mem_mileage;
	private String mem_delete;
	private String mem_role;
	private byte[] mem_img;
	private PartWrapper mem_image;
	
	public void setMem_image(PartWrapper mem_image) throws IOException {
		this.mem_image = mem_image;
		if (mem_image != null) {
			// this.mem_img = IOUtils.toByteArray(mem_image.getInputStream());
			try (
					InputStream is = mem_image.getInputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
			) {
				byte[] buffer = new byte[1024];
				int pointer = -1;
				while ((pointer = is.read(buffer)) != -1) {
					baos.write(buffer, 0, pointer);
				}
				baos.flush();
				this.mem_img = baos.toByteArray();
			}
		}
	}
	
	public String getMem_imgBase64() {
		String base64Str = null;
		if (mem_img != null) {
			// commons - codec => base64 대신 사용할 수 있는 라이브러리
			base64Str = Base64.getEncoder().encodeToString(mem_img);
		}
		return base64Str;
	}

	private List<ProdVO> prodList; // Member has many Prod 관계(1:N 관계의 테이블 조인시 사용되는 모델)

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		String name = event.getName();
		if (!"member".equals(name)) {
			return;
		}
		
		Map<String, MemberVO> userList = (Map<String, MemberVO>) event.getSession().getServletContext().getAttribute("userList");
		userList.put(mem_id, this);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		String name = event.getName();
		if (!"member".equals(name)) {
			return;
		}
		
		Map<String, MemberVO> userList = (Map<String, MemberVO>) event.getSession().getServletContext().getAttribute("userList");
		userList.remove(mem_id);
	}
}
