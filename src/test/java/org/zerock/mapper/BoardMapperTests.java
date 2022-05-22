package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;
	
	//@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
//	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		mapper.insert(board);
		log.info(board);	// bno 값이 0로 반환
	}
	
//	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		mapper.insertSelectKey(board);	// 
		log.info(board);	// bno 값이 변경이 되는지 확인한다.
	}

//	@Test
	public void testRead() {
		BoardVO board = mapper.read(30L);	// 현재 DB에 있는 게시글 번호를 사용한다.
		log.info(board);
	}
	
//	@Test
	public void testDelete() {
		int result = mapper.delete(30L);
		log.info("게시글 삭제 : " + result);	// 삭제되는 result=1
	}
	
//	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(31L);	// 변경하고자 하는 게시글 번호를 사용
		board.setTitle("새로 작성하는 글 작성");
		board.setContent("새로 작성하는 내용 변경");
		board.setWriter("newbie 변경");
		int result = mapper.update(board);
		log.info("Update Count : " + result);	// 변경된 열의 수가 출력
	}
	
//	@Test
	public void testPaging() {
		Criteria cri = new Criteria();	// 1페이지, 10개
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
	
//	@Test
	public void testPaging2() {
		Criteria cri = new Criteria(3, 20);	// 3페이지, 10개
		List<BoardVO> list = mapper.getListWithPaging2(cri);
		list.forEach(board -> log.info(board));
	}
	
//	@Test
	public void testSearch() {
		Criteria cri = new Criteria();	// 1페이지, 10개
		cri.setKeyword("user00");
		cri.setType("W");
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
	
	@Test
	public void testSearch2() {
		Criteria cri = new Criteria();	// 1페이지, 10개
		cri.setKeyword("user00");
		cri.setType("W");
		List<BoardVO> list = mapper.getListWithPaging2(cri);
		list.forEach(board -> log.info(board));
	}
}
