package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	@Setter(onMethod_=@Autowired)	// 주입을 받고
	private BoardService service;
	
//	@Test
	public void testExists() {
		log.info(service);
		assertNotNull(service);
	}
	
//	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		service.register(board);
		log.info("생성된 게시물의 번호: " + board.getBno());
	}
	
//	@Test
//	public void testGetList() {
//		service.getList().forEach(board -> log.info(board));
//	}
	
//	@Test
	public void testGet() {
		BoardVO board = service.get(36L);
		log.info(board);
	}
	
	// 주의사항 : 순서대로 실행되지 않는다
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 수정");
		board.setContent("새로 작성하는 내용 수정");
		board.setWriter("newbie 수정");
		board.setBno(36L);
		boolean result = service.modify(board);
		log.info("수정 성공 여부 : " + result);
	}
	
//	@Test
	public void testDelete() {
		boolean result = service.remove(35L);	// update와 다른 bno를 사용
		log.info("삭제 성공 여부 : " + result);
	}
	
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria(2, 10);
		service.getList(cri).forEach(board -> log.info(board));
	}
}
