package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {
	@Setter(onMethod_=@Autowired)	// 구현객체를 주입 받는다.
	private TimeMapper timeMapper;
	
	//@Test
	public void testGetTime() {
		log.info(timeMapper.getClass().getName());	// 구현객체를 출력
		log.info(timeMapper.getTime());				// 현재 시간을 출력
	}
	
	@Test
	public void testGetTime2() {
		log.info(timeMapper.getClass().getName());	// 구현객체를 출력
		log.info(timeMapper.getTime2());			// 현재 시간을 출력
	}
}
