package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// 서비스 로직을 수행
// 데이터베이스 처리 : BoardMapper, 댓글(ReplyMapper) 연동
@Log4j
@Service	// 서비스로 동작한다. 스프링에게 알린다. -> 주입을 받기 위해서
@AllArgsConstructor		// 생성자를 사용해서 Mapper를 주입받기 위하여
public class BoardServiceImpl implements BoardService {
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) {
		// 필요한 기능을 구현한다.
		log.info("register..." + board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {	// 게시글 상세 보기
		log.info("get... " + bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify..." + board);
		return mapper.update(board) == 1;	// 1/0->true/false
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove..." + bno);
		return mapper.delete(bno) == 1;		// 1/0->true/false
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList()...");
//		return mapper.getList();
//	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getList With Criteria..." + cri);
//		return mapper.getListWithPaging(cri);
		return mapper.getListWithPaging2(cri);	// 12c 기능 이용
	}
	
	@Override
	public int getTotal(Criteria cri) {	// Criteria를 만족하는 게시글의 수를 반환
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}
}
