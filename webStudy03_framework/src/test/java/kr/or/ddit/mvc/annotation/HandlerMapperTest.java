package kr.or.ddit.mvc.annotation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HandlerMapperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindCommandHandler() {
		IHandlerMapper handlerMapper = new HandlerMapper("kr.or.ddit");
		handlerMapper.findCommandHandler(null);
		
	}
}