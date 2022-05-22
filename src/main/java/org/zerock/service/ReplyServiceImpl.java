package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// 게시글 작성한 것을 참조 : 복습을 하면서 정리를 합니다.

@Service	// 스프링에서 주입받는 대상
@Log4j
public class ReplyServiceImpl implements ReplyService {
	// mapper를 정의
	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_=@Autowired)		// 댓글을 추가/삭제할 때 게시글 테이블의 댓글 수를 갱신
	private BoardMapper boardMapper;

	@Override
	public int register(ReplyVO vo) {
		log.info("register..." + vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("get... " + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify... " + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("remove... " + rno);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("getList... " + bno);
		return mapper.getListWithPaging(cri, bno);
	}

	// MySQL DB와 Oracle DB가 동작이 다르다. (마지막 페이지를 가져오는 부분이)
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		return new ReplyPageDTO(mapper.getCountByBno(bno),
	 		mapper.getListWithPaging(cri, bno));
	}

}
