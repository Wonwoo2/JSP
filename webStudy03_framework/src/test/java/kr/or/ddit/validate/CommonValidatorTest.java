package kr.or.ddit.validate;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class CommonValidatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("beforeClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("before");
	}

	@Test
	public void testValidate() {
		MemberVO member = MemberVO.builder().build();
		CommonValidator<MemberVO> validator = new CommonValidator<>();
		Map<String, StringBuffer> errors = new HashMap<>();
		boolean valid = validator.validate(member, errors, InsertGroup.class);
		if (!valid) {
			System.out.println(errors.get("mem_regno1"));
		}
	}
}