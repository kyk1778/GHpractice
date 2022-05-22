package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

// 화면정의서, 요구기능서 기반으로 추출, 인터페이스(서비스, 매퍼 간의 정합) 정의
// 어떤 기능이 필요 - 메소드를 정의
// 메소드 : 반환형, 메소드 이름, 매개변수
public interface BoardMapper {
//	@Select("select * from s_board where bno > 0")
	public List<BoardVO> getList();	// 게시글 목록 보기
	public void insert(BoardVO board);			// bno를 알필요가 없을 때
	public void insertSelectKey(BoardVO board);	// bno을 응답받고 싶을 때
	// 게시글 상세 보기
	public BoardVO read(Long bno);
	public int delete(Long bno);	// 반환형 : 삭제된 열의 갯수
	public int update(BoardVO board);	// 반환형: 변경된 열의 수,
		// 매개변수: 변경하고자 하는 게시글 정보
	// 페이징을 고려한 게시글 목록 보기 : 페이징
	public List<BoardVO> getListWithPaging(Criteria cri);	// 페이지 번호, 페이지당 게시글 수
	public List<BoardVO> getListWithPaging2(Criteria cri);	// 12c에 추가된 기능을 사용
	public int getTotalCount(Criteria cri);	// Criteria : 검색조건
}
