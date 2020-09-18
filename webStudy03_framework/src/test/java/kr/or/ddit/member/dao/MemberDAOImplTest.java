package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	
	IMemberDAO dao;

	@Before
	public void setUp() throws Exception {
		 dao = MemberDAOImpl.getInstance();
	}

	@Test
	public void testInsertMember() {
		MemberVO member = MemberVO.builder()
		.mem_id("1234aasd@")
		.mem_pass("12341234")
		.mem_name("ㅇㅇㅇ")
		.mem_regno1("930404")
		.mem_regno2("1341234")
		.mem_zip("123456")
		.mem_add1("대흥동")
		.mem_add2("대흥동")
		.mem_hometel("010-1313-2359")
		.mem_comtel("010-1313-1313")
		.build();
		
		assertNotEquals(0, dao.insertMember(member));
	}

	@Test
	public void testSelectMemberList() {
		
	}

	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		assertNotNull(member);
		member = dao.selectMember("asdfasdf");
		assertNull(member);
	}

	@Test
	public void testUpdateMember() {
		
	}

	@Test
	public void testDeleteMember() {
		
	}

}
