package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

public class IProdDAOTest {
	IProdDAO dao;

	@Before
	public void setUp() throws Exception {
		dao = ProdDAOImpl.getInstance();
	}

	@Test
	public void testInsertProd() {
		ProdVO prod = ProdVO.builder()
						.prod_buyer("P10101")
						.prod_color("asd")
						.prod_cost(1234123)
						.prod_delivery("asdfa")
						.prod_detail("dsdasd")
						.prod_img("asdasd.jpg")
						.prod_lgu("P101")
						.prod_mileage(100)
						.prod_name("ㅇㅇㅇㅇ")
						.prod_price(1000)
						.prod_properstock(1212)
						.prod_qtyin(123)
						.prod_qtysale(1000)
						.prod_size("123")
						.prod_totalstock(123)
						.prod_sale(123)
						.prod_outline("asd")
						.prod_unit("ㅁㄴ")
						.build();
		
		assertNotEquals(0, dao.insertProd(prod, null));
	}
}