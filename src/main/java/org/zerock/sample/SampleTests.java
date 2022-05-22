package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// 스프링에서 Run As tomcat을 구동하는 환경을 제공
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTests {
	@Setter(onMethod_=@Autowired)
	private Restaurant restaurant;
	
	@Test	// 테스트 코드를 작성
	public void testExist() {
		assertNotNull(restaurant);	// restaurant 객체가 생성이 되었는지 확인
		
		log.info(restaurant);		// 주입이 됐는지 확인
		log.info("------------------");
		log.info(restaurant.getChef());	// Chef객체가 주입이 되었는지 확인
	}
}