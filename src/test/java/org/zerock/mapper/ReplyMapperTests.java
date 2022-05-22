package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	// 게시글 -> 댓글 : 여러분의 데이터베이스 테이블의 게시글 번호를 사용
	private Long[] bnoArr = { 1781L, 1770L, 1769L, 1768L, 1767L };
	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;
	
//	@Test
	public void testMapper() {	// 스프링에서 ReplyMapper를 주입하는지?
		log.info(mapper);	// 구현객체 정보를 출력(MyBatis에서 내부적으로 알아서 생성)
	}
	
//	@Test
	public void testCreate() {
		// 게시글 하나에 댓글 2개를 추가
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();	// 댓글 객체 생성
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer" + i);
			mapper.insert(vo);	// 댓글 추가
		});
	}
	
//	@Test
	public void testRead() {
		// 댓글 번호 : 위에서 추가한 1~10 중에 하나를 사용
		Long targetRno = 5L;
		ReplyVO vo = mapper.read(targetRno);
		log.info(vo);
	}
	
//	@Test
	public void testDelete() {
		Long targetRno = 5L;
		int result = mapper.delete(targetRno);
		log.info(result);	// 1: 삭제 성공
	}
	
//	@Test
	public void testUpdate() {
		Long targetRno = 10L;
		ReplyVO vo = mapper.read(targetRno);
		vo.setReply("Update Reply");
		int count = mapper.update(vo);
		log.info("변경된 댓글의 수 : " + count);
	}
	
//	@Test
	public void testList() {
		Criteria cri = new Criteria();	// 1 페이지, 10개
		List<ReplyVO> list = mapper.getListWithPaging(cri, bnoArr[1]);
		list.forEach(reply -> log.info(reply));
	}
	
	// 인덱스를 추가한 페이징 처리
	@Test
	public void testList2() {
		Criteria cri = new Criteria(1, 10);		// 충분한 수의 댓글을 추가하고 시험한다.
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 1781L);	// bno = 30
		replies.forEach(reply -> log.info(reply));
	}

}
