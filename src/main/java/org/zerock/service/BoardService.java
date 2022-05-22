package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

// 설계할 때 인터페이스 규격을 만든다.
public interface BoardService {
	// 계층별로 사용하는 용어가 차이가 있으므로 주의를 한다.
	// 서비스 레이어에서 사용하는 용어 : register
	// Persistence 계층에서는 insert 를 사용
	public void register(BoardVO board);
	public BoardVO get(Long bno);	// read
	public boolean modify(BoardVO board);	// update, int
	public boolean remove(Long bno);
//	public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri);
	public int getTotal(Criteria cri);	// Criteria를 만족하는 전체 게시글의 수를 반환
}
